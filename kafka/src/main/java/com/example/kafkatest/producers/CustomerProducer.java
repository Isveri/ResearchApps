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

    public void allCustomersReply(List<Customer> customerList) {
        kafkaTemplate.send("allCustomersReplyTopic", customerList);
    }
    public void addCustomersReply(Customer addedCustomer) {
        System.out.println("add customer producer");
        kafkaTemplate.send("addCustomerReplyTopic", addedCustomer);
    }
    public void updateCustomersReply(Customer updatedCustomer) {
        kafkaTemplate.send("updateCustomerReplyTopic", updatedCustomer);
    }
    public void deleteCustomersReply(Customer deletedCustomer) {
        kafkaTemplate.send("deleteCustomerReplyTopic", deletedCustomer);
    }
}
