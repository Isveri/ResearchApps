package io.gatling.test.kafka;

import io.gatling.javaapi.core.Simulation;
import io.gatling.model.Customer;
import io.gatling.test.config.CustomRunner;
import io.gatling.test.config.SimulationConfigurator;
import io.gatling.utils.JsonParser;
import io.github.amerousful.kafka.javaapi.KafkaProtocolBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.amerousful.kafka.javaapi.KafkaDsl.KafkaBroker;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.kafka;

public abstract class KafkaSimulation extends Simulation implements SimulationConfigurator, CustomRunner {

    {
        run();
    }

    AtomicInteger counter = new AtomicInteger(0);

    KafkaProtocolBuilder kafkaProtocol = kafka
            .broker(KafkaBroker("localhost", 29092))
            .acks("1")
            .producerIdenticalSerializer("org.apache.kafka.common.serialization.StringSerializer")
            .consumerIdenticalDeserializer("org.apache.kafka.common.serialization.JsonDeserializer")
            .addProducerProperty("retries", "3")
            .addProducerProperty("batch.size", "32")
            .addProducerProperty("linger.time", "20")
            .addProducerProperty("compression.type", "snappy")
            .addProducerProperty("acks", "1")
            .addProducerProperty("max.in.flight.requests.per.connection", "10")

            .addConsumerProperty("heartbeat.interval.ms", "3000")
            .addConsumerProperty("fetch.min.bytes", "1")
            .addConsumerProperty("max.poll.records", "1000")
            .replyTimeout(4)
            .matchByKey()
            .replyConsumerName("gatling-test-consumer");

    protected boolean checkAllCustomers(ConsumerRecord<String, ?> record) {
        boolean correct = true;
        List<Customer> customerList = JsonParser.parseJsonToList((String) record.value());
        if (customerList.isEmpty()) {
            correct = false;
        }
        return correct;
    }

    protected boolean checkAddCustomer(ConsumerRecord<String, ?> record) {
        boolean correct = true;
        Customer customer = JsonParser.parseJsonToCustomer((String) record.value());
        if (customer == null) {
            correct = false;
        }
        return correct;
    }
}
