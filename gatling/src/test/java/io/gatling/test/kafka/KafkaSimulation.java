package io.gatling.test.kafka;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.ByteString;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.core.group.Groups;
import io.gatling.model.Customer;
import io.gatling.utils.JsonParser;
import io.github.amerousful.kafka.javaapi.KafkaMessageMatcher;
import io.github.amerousful.kafka.javaapi.KafkaProtocolBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.piomin.services.grpc.image.model.ImageProto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.*;

public class KafkaSimulation extends Simulation {

    AtomicInteger counter = new AtomicInteger(0);

    KafkaProtocolBuilder kafkaProtocol = kafka
            .broker(KafkaBroker("localhost", 29092))
            .acks("1")
            .producerIdenticalSerializer("org.apache.kafka.common.serialization.StringSerializer")
            .consumerIdenticalDeserializer("org.apache.kafka.common.serialization.JsonDeserializer")
            .addProducerProperty("retries", "3")
            .addConsumerProperty("heartbeat.interval.ms", "3000")
            .replyTimeout(10)
            .matchByKey()
            .replyConsumerName("gatling-test-consumer");

    public boolean checkAllCustomers(ConsumerRecord<String, String> record) {
        boolean correct = true;
        List<Customer> customerList = JsonParser.parseJsonToList(record.value());
        if (customerList.isEmpty()) {
            correct = false;
        }
        System.out.println("allCustomers: " + customerList);
        return correct;
    }

    public boolean checkAddCustomer(ConsumerRecord<String, String> record) {
        boolean correct = true;
        Customer customer = JsonParser.parseJsonToCustomer(record.value());
        if (customer == null) {
            correct = false;
        }
        System.out.println("addCustomer: " + customer);
        return correct;
    }

    ScenarioBuilder scn1 =
            scenario("Customer CRUD").forever().on(
                    exec(session -> session.set("my_var", counter.getAndIncrement()))
                            .exec(
                                    kafka("getAllCustomers")
                                            .requestReply()
                                            .topic("allCustomersRequestTopic")
                                            .payload("""
                                                    { }
                                                    """)
                                            .replyTopic("allCustomersReplyTopic")
                                            .key("key1")
                                            .check(simpleCheck(this::checkAllCustomers)),
                                    kafka("addCustomer")
                                            .requestReply()
                                            .topic("addCustomerRequestTopic")
                                            .payload("""
                                                    {"name": "newUser","pesel": "newUser${my_var}"}
                                                    """)
                                            .replyTopic("allCustomersReplyTopic")
                                            .key("key2")
                                            .check(simpleCheck(this::checkAddCustomer)),
                                    kafka("updateCustomer")
                                            .requestReply()
                                            .topic("updateCustomerRequestTopic")
                                            .payload("""
                                                    {"name": "newUserChanged","pesel": "newUser${my_var}"}
                                                    """)
                                            .replyTopic("allCustomersReplyTopic")
                                            .key("key3")
                                            .check(simpleCheck(this::checkAddCustomer)),
                                    kafka("deleteCustomer")
                                            .requestReply()
                                            .topic("deleteCustomerRequestTopic")
                                            .payload("newUser${my_var}")
                                            .replyTopic("allCustomersReplyTopic")
                                            .key("key4")
                                            .check(simpleCheck(this::checkAddCustomer))

                            ));
    ScenarioBuilder scn2 =
            scenario("Image upload/dowload")
                    .exec(
                            exec(session -> session.set("my_var", counter.getAndIncrement()))
                                    .exec(
                                            kafka("image upload")
                                                    .requestReply()
                                                    .topic("uploadImageRequestTopic")
                                                    .payload(session -> {
                                                        ByteString byteString;
                                                        byte[] imageBytes;
                                                        try {
                                                            imageBytes = Files.readAllBytes(Paths.get("src/test/image/testImage.jpg"));
                                                        } catch (IOException e) {
                                                            throw new RuntimeException(e);
                                                        }
                                                        JsonObject json = new JsonObject();
                                                        json.addProperty("name", "testImage" + session.get("my_var") + ".jpg");
                                                        json.addProperty("type", "image/jpeg");
                                                        json.addProperty("imageData", Base64.getEncoder().encodeToString(imageBytes));
                                                        String payload = new Gson().toJson(json);
                                                        System.out.println(payload);
                                                        return payload;
                                                    })
                                                    .replyTopic("uploadImageReplyTopic")
                                                    .key("key1")
                                                    .check()
                                    )
                    );

    {
        setUp(scn2.injectOpen(atOnceUsers(1))).maxDuration(Duration.ofSeconds(60))
                .protocols(kafkaProtocol);
    }

}
