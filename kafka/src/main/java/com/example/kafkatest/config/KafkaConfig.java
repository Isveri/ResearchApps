package com.example.kafkatest.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    //topic:topic gatlinga do listenera microserwisu po liste customerow
    @Bean
    public NewTopic allCustomersRequestTopic() {
        return new NewTopic("allCustomersRequestTopic", 10, (short) 10);
    }
    //topic:topic microserwisu do gatlinga ktory ma w message liste customerow
    @Bean
    public NewTopic allCustomersReplyTopic() {
        return new NewTopic("allCustomersReplyTopic", 10, (short) 10);
    }
    //topic:topic gatlinga do listenera microserwisu z messagem z danymi do updatu customera
    @Bean
    public NewTopic updateCustomerTopic() {
        return new NewTopic("updateCustomerTopic", 10, (short) 10);
    }
    //topic:topic gatlinga do listenera microserwisu po konkretnego customera
    @Bean
    public NewTopic customerRequestTopic() {
        return new NewTopic("customerRequestTopic", 1, (short) 1);
    }
    //topic:topic microserwisu do gatlinga ktory ma w message dane konkretnego customera
    @Bean
    public NewTopic customerReplyTopic() {
        return new NewTopic("customerReplyTopic", 1, (short) 1);
    }
}
