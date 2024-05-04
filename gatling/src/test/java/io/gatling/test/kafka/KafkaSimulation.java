package io.gatling.test.kafka;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.gatling.javaapi.core.Simulation;
import io.gatling.model.Customer;
import io.gatling.test.config.CustomRunner;
import io.gatling.test.config.SimulationConfigurator;
import io.gatling.utils.JsonParser;
import io.github.amerousful.kafka.javaapi.KafkaMessageMatcher;
import io.github.amerousful.kafka.javaapi.KafkaProtocolBuilder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.github.amerousful.kafka.javaapi.KafkaDsl.KafkaBroker;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.kafka;

public abstract class KafkaSimulation extends Simulation implements SimulationConfigurator, CustomRunner {
    AtomicInteger counter = new AtomicInteger(0);

    KafkaProtocolBuilder kafkaProtocol = kafka
            .broker(KafkaBroker("localhost", 29092))
            .acks("1")
            .producerIdenticalSerializer("org.apache.kafka.common.serialization.StringSerializer")
            .consumerIdenticalDeserializer("org.apache.kafka.common.serialization.JsonDeserializer")
            .addProducerProperty("retries", "3")
            .addProducerProperty("batch.size", "128")
            .addProducerProperty("linger.time", "20")
            .addProducerProperty("compression.type", "snappy")
            .addProducerProperty("acks", "1")
            .addProducerProperty("max.in.flight.requests.per.connection", "10")
            .addConsumerProperty("batch.size", "128")
            .addConsumerProperty("heartbeat.interval.ms", "3000")
            .addConsumerProperty("fetch.min.bytes", "1")
            .addConsumerProperty("max.poll.records", "1000")
            .replyTimeout(10)
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


    {
        run();
    }
}
