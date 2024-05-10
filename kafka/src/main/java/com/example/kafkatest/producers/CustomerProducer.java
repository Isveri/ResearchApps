package com.example.kafkatest.producers;

import com.example.kafkatest.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void allCustomersReply(List<Customer> customerList, String key) {
        kafkaTemplate.send("allCustomersReplyTopic", key, customerList);
    }
    public void addCustomersReply(Customer addedCustomer, String key) {
//        System.out.println("add customer producer");
        kafkaTemplate.send("allCustomersReplyTopic", key, addedCustomer);
    }
    public void updateCustomersReply(Customer updatedCustomer, String key) {
        kafkaTemplate.send("allCustomersReplyTopic", key, updatedCustomer);
    }
    public void deleteCustomersReply(Customer deletedCustomer, String key) {
        kafkaTemplate.send("allCustomersReplyTopic", key, deletedCustomer);
    }
}
