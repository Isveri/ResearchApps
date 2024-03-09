package com.example.kafkatest.producers;

import com.example.kafkatest.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProducer {

    private static final String TOPIC_allCustomers = "allCustomersReplyTopic";
    private static final String TOPIC_customer = "customerReplyTopic";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendAllCustomers(List<Customer> customerList) {
        kafkaTemplate.send(TOPIC_allCustomers, customerList);
        System.out.println("all customers provider tset");
        kafkaTemplate.flush();
    }
    public void sendCustomer(Customer customer) {
        kafkaTemplate.send(TOPIC_customer, customer);
    }
}
