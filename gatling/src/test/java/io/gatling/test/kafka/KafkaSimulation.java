package io.gatling.test.kafka;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.model.Customer;
import io.gatling.utils.JsonParser;
import io.github.amerousful.kafka.javaapi.KafkaMessageMatcher;
import io.github.amerousful.kafka.javaapi.KafkaProtocolBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.*;

public class KafkaSimulation extends Simulation{

    AtomicInteger counter = new AtomicInteger(0);
    private static final KafkaMessageMatcher customMatcher =
            new KafkaMessageMatcher() {
                private String key;
                @NonNull
                @Override
                public String requestMatchId(@NonNull ProducerRecord<String, String> msg) {
                    key = msg.key();
                    return key;
                }

                @NonNull
                @Override
                public String responseMatchId(@NonNull ConsumerRecord<String, String> msg) {
                    return key;
                }
            };

    KafkaProtocolBuilder kafkaProtocol = kafka
            .broker(KafkaBroker("localhost", 29092))
            .acks("1")
            .producerIdenticalSerializer("org.apache.kafka.common.serialization.StringSerializer")
            .consumerIdenticalDeserializer("org.apache.kafka.common.serialization.JsonDeserializer")
            .addProducerProperty("retries", "3")
            .addConsumerProperty("heartbeat.interval.ms", "3000")
            .replyTimeout(10)
            .matchByKey()
            .matchByValue()
            .messageMatcher(customMatcher)
            .replyConsumerName("gatling-test-consumer");

    public boolean checkAllCustomers(ConsumerRecord<String, String> record) {
        boolean correct=true;
        List<Customer> customerList = JsonParser.parseJsonToList(record.value());
        if(customerList.isEmpty()){correct=false;}
        System.out.println("allCustomers: "+customerList);
        return correct;
    }
    public boolean checkAddCustomer(ConsumerRecord<String, String> record) {
        boolean correct=true;
        Customer customer = JsonParser.parseJsonToCustomer(record.value());
        if(customer==null){correct=false;}
        System.out.println("addCustomer: "+customer);
        return true;
    }

    ScenarioBuilder scn =
            scenario("Customer CRUD")
                    .exec(session -> session.set("my_var", counter.getAndIncrement()))
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
                    .exec(
                            kafka("addCustomer")
                                    .requestReply()
                                    .topic("addCustomerRequestTopic")
                                    .payload("""
                                            {"name": "newUser","pesel": "newUser${my_var}"}
                                            """)
                                    .replyTopic("addCustomerReplyTopic")
                                    .key("key2")
                                    .check(simpleCheck(this::checkAddCustomer))
                    );

    {
        setUp(scn.injectOpen(atOnceUsers(1))).maxDuration(Duration.ofSeconds(60))
                .protocols(kafkaProtocol);
    }

}
