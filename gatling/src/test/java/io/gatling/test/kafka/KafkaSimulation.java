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
            .addProducerProperty("batch.size","32")
            .addProducerProperty("linger.time","20")
            .addProducerProperty("compression.type", "snappy")
            .addProducerProperty("acks","1")
            .addProducerProperty("max.in.flight.requests.per.connection","10")

            .addConsumerProperty("heartbeat.interval.ms", "3000")
            .addConsumerProperty("fetch.min.bytes","1")
            .addConsumerProperty("max.poll.records","1000")
            .replyTimeout(4)
            .matchByKey()
            .replyConsumerName("gatling-test-consumer");

    public boolean checkAllCustomers(ConsumerRecord<String, ?> record) {
        boolean correct = true;
        List<Customer> customerList = JsonParser.parseJsonToList((String) record.value());
        if (customerList.isEmpty()) {
            correct = false;
        }
        //System.out.println("allCustomers: " + customerList);
        return correct;
    }

    public boolean checkAddCustomer(ConsumerRecord<String, ?> record) {
        boolean correct = true;
        Customer customer = JsonParser.parseJsonToCustomer((String) record.value());
        if (customer == null) {
            correct = false;
        }
        //System.out.println("addCustomer: " + customer.getName()+" "+customer.getPesel());
        return correct;
    }

    ScenarioBuilder scn1 =
            scenario("Customer CRUD").repeat(100000).on(
                    exec(session -> session.set("my_var", counter.getAndIncrement()))
//                            .exec(
//                                    kafka("getAllCustomers send")
//                                            .send()
//                                            .topic("allCustomersRequestTopic")
//                                            .payload("""
//                                                    { }
//                                                    """)
//                                            .key("key1")
//                            )
//                            .exec(
//                                    kafka("getAllCustomers get")
//                                            .onlyConsume()
//                                            .readTopic("allCustomersReplyTopic")
//                                            .payloadForTracking("""
//                                                                { }
//                                                                """)
//                                            .keyForTracking("key1")
//                                            .check(simpleCheck(this::checkAllCustomers))
//                            )
//                            .exec(
//                                    kafka("addCustomer send")
//                                            .send()
//                                            .topic("addCustomerRequestTopic")
//                                            .payload("""
//                                                    {"name": "newUser","pesel": "newUser${my_var}"}
//                                                    """)
//                                            .key("key2")
//                            )
//                            .exec(
//                                    kafka("addCustomer get")
//                                            .onlyConsume()
//                                            .readTopic("allCustomersReplyTopic")
//                                            .payloadForTracking("")
//                                            .keyForTracking("key2")
//                                            .check(simpleCheck(this::checkAddCustomer))
//                            )
//                            .exec(
//                                    kafka("updateCustomer send")
//                                            .send()
//                                            .topic("updateCustomerRequestTopic")
//                                            .payload("""
//                                                    {"name": "newUserChanged","pesel": "newUser${my_var}"}
//                                                    """)
//                                            .key("key3")
//                            )
//                            .exec(
//                                    kafka("updateCustomer get")
//                                            .onlyConsume()
//                                            .readTopic("allCustomersReplyTopic")
//                                            .payloadForTracking("")
//                                            .keyForTracking("key3")
//                                            .check(simpleCheck(this::checkAddCustomer))
//                            )
//                            .exec(
//                                    kafka("deleteCustomer send")
//                                            .send()
//                                            .topic("deleteCustomerRequestTopic")
//                                            .payload("newUser${my_var}")
//                                            .key("key4")
//                            )
//                            .exec(
//                                    kafka("deleteCustomer get")
//                                            .onlyConsume()
//                                            .readTopic("allCustomersReplyTopic")
//                                            .payloadForTracking("")
//                                            .keyForTracking("key4")
//                                            .check(simpleCheck(this::checkAddCustomer))
//                            )


                            .exec(
                                    kafka("getAllCustomers")
                                            .requestReply()
                                            .topic("allCustomersRequestTopic")
                                            .payload("""
                                                    { }
                                                    """)
                                            .replyTopic("allCustomersReplyTopic")
                                            .key("key1")
                                            .check(simpleCheck(this::checkAllCustomers))
                            )
//                            .exec(
//                                    kafka("addCustomer")
//                                            .requestReply()
//                                            .topic("addCustomerRequestTopic")
//                                            .payload("""
//                                                    {"name": "newUser","pesel": "newUser${my_var}"}
//                                                    """)
//                                            .replyTopic("allCustomersReplyTopic")
//                                            .key("key2")
//                                            .check(simpleCheck(this::checkAddCustomer))
//                            )
//                            .exec(
//                                    kafka("updateCustomer")
//                                            .requestReply()
//                                            .topic("updateCustomerRequestTopic")
//                                            .payload("""
//                                                    {"name": "newUserChanged","pesel": "newUser${my_var}"}
//                                                    """)
//                                            .replyTopic("allCustomersReplyTopic")
//                                            .key("key3")
//                                            .check(simpleCheck(this::checkAddCustomer))
//                            )
//                            .exec(
//                                    kafka("deleteCustomer")
//                                            .requestReply()
//                                            .topic("deleteCustomerRequestTopic")
//                                            .payload("newUser${my_var}")
//                                            .replyTopic("allCustomersReplyTopic")
//                                            .key("key4")
//                                            .check(simpleCheck(this::checkAddCustomer))
//                            )
            );
    ScenarioBuilder scn2 =
            scenario("Image upload/dowload").forever().on(
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
                                                //System.out.println(payload);
                                                return payload;
                                            })
                                            .replyTopic("uploadImageReplyTopic")
                                            .key("key1")
                                            .check()
                            )
                            .exec(
                                    kafka("image download")
                                            .requestReply()
                                            .topic("downloadImageRequestTopic")
                                            .payload("testImage${my_var}.jpg")
                                            .replyTopic("uploadImageReplyTopic")
                                            .key("key2")
                                            .check()
                            )
                    );

    {
        setUp(scn1.injectOpen(
                constantUsersPerSec(30).during(Duration.ofMinutes(1))
                //atOnceUsers(200)
        )).maxDuration(Duration.ofSeconds(60))
                .protocols(kafkaProtocol);
//        setUp(scn1.injectOpen(
//                nothingFor(5),
//                rampUsers(20).during(50)
//        )).maxDuration(Duration.ofSeconds(60))
//                .protocols(kafkaProtocol);
    }

}
