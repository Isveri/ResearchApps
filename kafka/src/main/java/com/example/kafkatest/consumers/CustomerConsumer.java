package com.example.kafkatest.consumers;

import com.example.kafkatest.model.Customer;
import com.example.kafkatest.producers.CustomerProducer;
import com.example.kafkatest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerConsumer {
    @Autowired
    CustomerRepository repository;

    @Autowired
    CustomerProducer customerProducer;

    @KafkaListener(topics = "allCustomersRequestTopic", groupId = "customer_group", containerFactory = "kafkaListenerContainerCustomerFactory")
//    @SendTo("allCustomersReplyTopic")
    public List<Customer> handleAllCustomersRequest() {
        List<Customer> reply = repository.findAll();
        //customerProducer.sendAllCustomers(reply);
        System.out.println("all customers listener");
        return reply;
    }

    @KafkaListener(topics = "updateCustomerTopic", groupId = "customer_group", containerFactory = "kafkaListenerContainerCustomerFactory")
    public void handleUpdateCustomerRequest(Customer customer) {
        repository.updateCustomer(customer.getId(),customer.getName(),customer.getPesel());
    }

    @KafkaListener(topics = "customerRequestTopic", groupId = "customer_group", containerFactory = "kafkaListenerContainerLongFactory")
    public void handleCustomerRequest(Long customerId) {
        Customer reply = repository.findById(customerId);
        customerProducer.sendCustomer(reply);
    }
}
