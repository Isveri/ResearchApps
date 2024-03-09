//package io.gatling.kafka.demo;
//
//import edu.umd.cs.findbugs.annotations.NonNull;
//import io.gatling.javaapi.core.Simulation;
//import io.gatling.javaapi.core.ScenarioBuilder;
////import io.github.amerousful.kafka.javaapi.KafkaMessageMatcher;
////import io.github.amerousful.kafka.javaapi.KafkaProtocolBuilder;
////import io.github.amerousful.kafka.protocol.SaslMechanism;
//import io.github.amerousful.kafka.javaapi.KafkaMessageMatcher;
//import io.github.amerousful.kafka.javaapi.KafkaProtocolBuilder;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.time.Duration;
//import java.util.Collections;
//
//import static io.gatling.javaapi.core.CoreDsl.*;
//import static io.github.amerousful.kafka.javaapi.KafkaDsl.KafkaBroker;
//import static io.github.amerousful.kafka.javaapi.KafkaDsl.*;
//
//public class TestSimulation extends Simulation{
//
//    private static final KafkaMessageMatcher customMatcher =
//            new KafkaMessageMatcher() {
//                @NonNull
//                @Override
//                public String requestMatchId(@NonNull ProducerRecord<String, String> msg) {
//                    return msg.key();
//                }
//
//                @NonNull
//                @Override
//                public String responseMatchId(@NonNull ConsumerRecord<String, String> msg) {
//                    return msg.key();
//                }
//            };
//
//    KafkaProtocolBuilder kafkaProtocol = kafka
//            .broker(KafkaBroker("localhost", 9092))
//            .acks("1")
//            .producerKeySerializer("org.apache.kafka.common.serialization.StringSerializer")
//            .producerValueSerializer("org.springframework.kafka.support.serializer.JsonSerializer")
//            .consumerKeyDeserializer("org.apache.kafka.common.serialization.StringDeserializer")
//            .consumerValueDeserializer("org.springframework.kafka.support.serializer.JsonDeserializer")
//            .replyTimeout(5)
//            //.matchByKey()
//            ;
//
//    //#simple
//    public boolean checkRecordValue(ConsumerRecord<String, String> record) {
//        return record.value().equals("myValue");
//    }
//
//    ScenarioBuilder scn =
//            scenario("scenario")
//                    .exec(
//                            kafka("all customers")
//                                    .requestReply()
//                                    .topic("allCustomersRequestTopic")
//                                    .payload("""
////                                    { "id": "1", "pesel": "test", "name":"test"}
////                                    """)
//                                    .replyTopic("allCustomersReplyTopic")
//                                    .key("key")
////                                    .check(jsonPath("$.m").is("#{payload}_1"))
////                                    .checkIf("#{bool}")
////                                    .then(jsonPath("$..foo"))
////                                    .checkIf((message, session) -> true)
////                                    .then(jsonPath("$").is("hello"))
////                                    .check(header("header1").in("value1"))
////                                    .check(simpleCheck(this::checkRecordValue))
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
////                                    .check(jsonPath("$.m").is("#{payload}_1"))
////                                    .checkIf("#{bool}")
////                                    .then(jsonPath("$..foo"))
////                                    .checkIf((message, session) -> true)
////                                    .then(jsonPath("$").is("hello"))
////                                    .check(header("header1").in("value1"))
////                                    .check(simpleCheck(this::checkRecordValue))
//
//                    );
//    {
//        setUp(scn.injectOpen(atOnceUsers(1)))
//                .protocols(kafkaProtocol);
//    }
//
//}
