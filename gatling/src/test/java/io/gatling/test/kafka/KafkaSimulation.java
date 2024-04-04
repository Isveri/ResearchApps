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


import static io.gatling.javaapi.core.CoreDsl.*;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.*;

public class KafkaSimulation extends Simulation{


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
            .messageMatcher(customMatcher);

    public boolean checkRecordValue(ConsumerRecord<String, String> record) {
        boolean correct=true;
        List<Customer> customerList = JsonParser.parseJsonToList(record.value());
        if(customerList.isEmpty()){correct=false;}
        System.out.println(customerList);
        return correct;
    }

    ScenarioBuilder scn =
            scenario("scenario")
                    .exec(
                            kafka("Kafka: request with reply")
                                    .requestReply()
                                    .topic("allCustomersRequestTopic")
                                    .payload("""
                                            { "id": "1", "pesel": "test", "name":"test"}
                                            """)
                                    .replyTopic("allCustomersReplyTopic")
                                    .key("key")
                                    .check(simpleCheck(this::checkRecordValue))
//                                    .checkIf("#{bool}")
//                                    .then(jsonPath("$..foo"))
//                                    .checkIf((message, session) -> true)
//                                    .then(jsonPath("$").is("hello"))
//                                    .check(header("header1").in("value1"))
//                                    .check(simpleCheck(this::checkRecordValue))
//                            ,
//                            kafka("update customer")
//                                    .send()
//                                    .topic("updateCustomerTopic")
//                                    .payload("""
//                                                  { "id": "1", "pesel": "testgatlin", "name":"testgatlin"}
//                                                  """)
//                                    .key("key"),
//                            kafka("get customer")
//                                    .requestReply()
//                                    .topic("customerRequestTopic")
//                                    .payload("1")
//                                    .replyTopic("customerReplyTopic")
//                                    .key("key")
//                                    .check(jsonPath("$.m").is("#{payload}_1"))
//                                    .checkIf("#{bool}")
//                                    .then(jsonPath("$..foo"))
//                                    .checkIf((message, session) -> true)
//                                    .then(jsonPath("$").is("hello"))
//                                    .check(header("header1").in("value1"))
//                                    .check(simpleCheck(this::checkRecordValue))

                    );
    {
        setUp(scn.injectOpen(atOnceUsers(1))).maxDuration(Duration.ofSeconds(60))
                .protocols(kafkaProtocol);
    }

}
