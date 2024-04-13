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
        return new NewTopic("allCustomersRequestTopic", 1, (short) 10);
    }

    //topic:topic microserwisu do gatlinga ktory ma w message liste customerow
    @Bean
    public NewTopic allCustomersReplyTopic() {
        return new NewTopic("allCustomersReplyTopic", 1, (short) 10);
    }

    //topic: topic gatlinga do listenera microserwisu z danymi customera do dodania
    @Bean
    public NewTopic addCustomerRequestTopic(){return new NewTopic("addCustomerRequestTopic",1,(short) 10);}

    //topic: topic microserwisu do gatlinga ktory w message ma dodanego customera
    @Bean
    public NewTopic addCustomerReplyTopic(){return new NewTopic("addCustomerReplyTopic",1,(short) 10);}

    //topic:topic gatlinga do listenera microserwisu z messagem z danymi do updatu customera
    @Bean
    public NewTopic updateCustomerRequestTopic() {
        return new NewTopic("updateCustomerRequestTopic", 1, (short) 10);
    }

    //topic: topic microserwisu do gatlinga ktory w message ma zaktualizowanego customera
    @Bean
    public NewTopic updateCustomerReplyTopic() {
        return new NewTopic("updateCustomerReplyTopic", 1, (short) 10);
    }

    //topic: topic gatlinga do listenera microserwisu z messagem z danymi do usuniecia customera
    @Bean
    public NewTopic deleteCustomerRequestTopic() {
        return new NewTopic("deleteCustomerRequestTopic", 1, (short) 10);
    }

    //topic: topic microserwisu do gatlinga ktory w message ma usunietego customera
    @Bean
    public NewTopic deleteCustomerReplyTopic() {
        return new NewTopic("deleteCustomerReplyTopic", 1, (short) 10);
    }

    //topic: topic gatlinga do listenera microserwisu ze zdjeciem do dodania
    @Bean
    public NewTopic uploadImageRequestTopic() {
        return new NewTopic("uploadImageRequestTopic", 1, (short) 10);
    }

    //topic: topic microserwisu do gatlinga ktory z potwierdzeniem dodania zdjecia
    @Bean
    public NewTopic uploadImageReplyTopic() {
        return new NewTopic("uploadImageReplyTopic", 1, (short) 10);
    }

    //topic: topic gatlinga do listenera microserwisu z nazwą zdjęcia do pobrania
    @Bean
    public NewTopic dowloadImageRequestTopic() {
        return new NewTopic("downloadImageRequestTopic", 1, (short) 10);
    }

    //topic: topic microserwisu do gatlinga ktory z pobieranym zdjeciem
    @Bean
    public NewTopic dowloadImageReplyTopic() {
        return new NewTopic("downloadImageReplyTopic", 1, (short) 10);
    }
}
