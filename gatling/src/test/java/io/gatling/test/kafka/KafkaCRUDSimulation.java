package io.gatling.test.kafka;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.kafka;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.simpleCheck;

public class KafkaCRUDSimulation extends KafkaSimulation {

    @Override
    public void run() {
        testScenario(kafkaProtocol,this,100,450,60);
//        runScenario(kafkaProtocol, this, 1, 300, 60);
//        repeat1Constant30000duration60(kafkaProtocol, this);
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
                        .key("key-#{my_var}-1")
                        .check(simpleCheck(this::checkAllCustomers))
                )
                .exec(kafka("addCustomer")
                        .requestReply()
                        .topic("addCustomerRequestTopic")
                        .payload("""
                                {"name": "newUser","pesel": "newUser${my_var}"}
                                """)
                        .replyTopic("allCustomersReplyTopic")
                        .key("key-#{my_var}-2")
                        .check(simpleCheck(this::checkAddCustomer))
                )
                .exec(kafka("updateCustomer")
                        .requestReply()
                        .topic("updateCustomerRequestTopic")
                        .payload("""
                                {"name": "newUserChanged","pesel": "newUser${my_var}"}
                                """)
                        .replyTopic("allCustomersReplyTopic")
                        .key("key-#{my_var}-3")
                        .check(simpleCheck(this::checkAddCustomer))
                )
                .exec(kafka("deleteCustomer")
                        .requestReply()
                        .topic("deleteCustomerRequestTopic")
                        .payload("newUser${my_var}")
                        .replyTopic("allCustomersReplyTopic")
                        .key("key-#{my_var}-4")
                        .check(simpleCheck(this::checkAddCustomer))
                )
                ;
    }
}
