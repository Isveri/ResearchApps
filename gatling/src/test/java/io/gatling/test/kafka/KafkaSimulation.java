package io.gatling.test.kafka;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.github.amerousful.kafka.javaapi.KafkaMessageMatcher;
import io.github.amerousful.kafka.javaapi.KafkaProtocolBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.KafkaBroker;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.kafka;

public class KafkaSimulation extends Simulation{

    private static final KafkaMessageMatcher customMatcher =
            new KafkaMessageMatcher() {
                @NonNull
                @Override
                public String requestMatchId(@NonNull ProducerRecord<String, String> msg) {
                    return msg.key();
                }

                @NonNull
                @Override
                public String responseMatchId(@NonNull ConsumerRecord<String, String> msg) {
                    return msg.key();
                }
            };

    KafkaProtocolBuilder kafkaProtocol = kafka
            .broker(KafkaBroker("localhost", 29092))
            .acks("1")
            .addConsumerProperty("heartbeat.interval.ms", "3000")
            .producerIdenticalSerializer("org.apache.kafka.common.serialization.StringSerializer")
            .consumerIdenticalDeserializer("org.apache.kafka.common.serialization.StringDeserializer")
            .replyTimeout(5)
            .matchByKey()
            .matchByValue()
            .messageMatcher(customMatcher)
            .replyConsumerName("gatling-test-consumer");;

    public boolean checkRecordValue(ConsumerRecord<String, String> record) {
        return record.value().equals("myValue");
    }

    ScenarioBuilder scn =
            scenario("scenario")
                    .exec(
                            kafka("all customers")
                                    .requestReply()
                                    .topic("allCustomersRequestTopic")
                                    .payload("""
                                    { "id": "1", "pesel": "test", "name":"test"}
                                    """)
                                    .replyTopic("allCustomersReplyTopic")
                                    .key("key")
                                    .check(jsonPath("$.m").is("#{payload}_1"))
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
        setUp(scn.injectOpen(atOnceUsers(1)))
                .protocols(kafkaProtocol);
    }

}
