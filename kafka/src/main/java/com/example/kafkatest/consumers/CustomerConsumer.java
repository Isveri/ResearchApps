package com.example.kafkatest.consumers;

import com.example.kafkatest.config.KafkaProducerConfig;
import com.example.kafkatest.model.Customer;
import com.example.kafkatest.producers.CustomerProducer;
import com.example.kafkatest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerConsumer {
    @Autowired
    CustomerRepository repository;
    @Autowired
    CustomerProducer producer;

    @KafkaListener(topics = "allCustomersRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory")
    //@SendTo("allCustomersReplyTopic")
    public List<Customer> handleAllCustomersRequest() {
        List<Customer> reply = repository.findAll();
        System.out.println("all customers listener");
        producer.allCustomersReply(reply);
        return reply;
    }

    @KafkaListener(topics = "updateCustomerTopic", containerFactory = "kafkaListenerContainerCustomerFactory")
    public void handleUpdateCustomerRequest(Customer customer) {
        repository.updateCustomer(customer.getId(),customer.getName(),customer.getPesel());
    }

    @KafkaListener(topics = "customerRequestTopic", containerFactory = "kafkaListenerContainerLongFactory")
    @SendTo("customerReplyTopic")

    public Customer handleCustomerRequest(Long customerId) {
        return repository.findById(customerId);
    }
}
