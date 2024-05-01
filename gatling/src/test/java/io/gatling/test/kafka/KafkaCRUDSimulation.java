package io.gatling.test.kafka;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.kafka;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.simpleCheck;

public class KafkaCRUDSimulation extends KafkaSimulation {

    @Override
    public void run() {
        repeat1Constant30000duration60(kafkaProtocol, this);
//        repeat10Constant3000duration60(kafkaProtocol, this);
//        repeat100Constant300duration60(kafkaProtocol, this);
    }

    // TODO dodać obsługę scenariusza dla wielu akcji jednocześnie na backend tj CREATE, READ, UPDATE, DELETE / podzielić to na 4 sam juz nwm
    @Override
    public ScenarioBuilder createScenario() {
        return scenario("Customer CRUD")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(kafka("getAllCustomers")
                        .requestReply()
                        .topic("allCustomersRequestTopic")
                        .payload("""
                                { }
                                """)
                        .replyTopic("allCustomersReplyTopic")
                        .key("key1")
                        .check(simpleCheck(this::checkAllCustomers))
                );
    }
}
