package io.gatling.kafka.demo;

import io.gatling.core.json.Json;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.kafka.demo.model.Customer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import ru.tinkoff.gatling.kafka.javaapi.protocol.KafkaProtocolBuilder;
import ru.tinkoff.gatling.kafka.javaapi.protocol.KafkaProtocolBuilderNew;

import java.time.Duration;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static ru.tinkoff.gatling.kafka.javaapi.KafkaDsl.kafka;

public class TestSecondSimulation extends Simulation {
    public static final String IP_SERVER = System.getProperty("IP_SERVER", "");
    public static final String URL_REGISTRY = System.getProperty("URL_REGISTRY", "");
    public static final String USER_AUTH = System.getProperty("USER_AUTH", "");
    private final KafkaProtocolBuilder kafkaProtocol = kafka()
            .topic("allCustomersRequestTopic")
            .properties(
                    Map.of(
                            ProducerConfig.ACKS_CONFIG, "1",
                            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer",
                            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , "org.springframework.kafka.support.serializer.JsonSerializer")
            );
    private final KafkaProtocolBuilderNew kafkaProtocolRequestReply = kafka().requestReply()
            .producerSettings(
                    Map.of(
                            ProducerConfig.ACKS_CONFIG, "1",
                            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer",
                            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , "org.springframework.kafka.support.serializer.JsonSerializer"
                    )
            )
            .consumeSettings(
                    Map.of("bootstrap.servers", "localhost:9092",
                            "group.id","customer_group")
            ).timeout(Duration.ofSeconds(5));

    private final Headers headers = new RecordHeaders().add("test-header", "test_value".getBytes());

    private final ScenarioBuilder kafkaProducer = scenario("Kafka Producer")
//            .forever().on(
//            exec(kafka("Simple Message")
//                .send(new Customer(1L,"test","test"))
//            )
//            );
//            .exec(kafka("Simple Message")
//                .send(new Customer(1L,"test","test"))
//            );
            .exec(
                    kafka("getAllCust").requestReply()
                            .requestTopic("allCustomersRequestTopic")
                            .replyTopic("allCustomersReplyTopic")
                            .send("key", """
                                    { "id": 1, "pesel": "test", "name":"test"}
                                    """,headers,String.class, String.class)
            )
//            .exec(
//                    kafka("updateCust").requestReply()
//                    .requestTopic("updateCustomerTopic")
//                    .replyTopic("updateCustomerTopic")
//                    .send("key", """
//                                    { "id": 1, "pesel": "testgatlin", "name":"testgatlin"}
//                                    """,headers,String.class, String.class)
//            )
//            .exec(
//                    kafka("getCust").requestReply()
//                    .requestTopic("customerRequestTopic")
//                    .replyTopic("customerReplyTopic")
//                    .send("key", "1",headers,String.class, String.class))
    ;

    {
        setUp(kafkaProducer.injectOpen(atOnceUsers(1))
        ).protocols(kafkaProtocolRequestReply).maxDuration(Duration.ofSeconds(60));
    }
}
