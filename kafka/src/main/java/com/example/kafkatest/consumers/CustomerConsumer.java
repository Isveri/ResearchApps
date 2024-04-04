package com.example.kafkatest.consumers;

import com.example.kafkatest.model.Customer;
import com.example.kafkatest.producers.CustomerProducer;
import com.example.kafkatest.repository.CustomerRepository;
import com.example.kafkatest.repository.CustomerRepositoryLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerConsumer {

    private final CustomerRepository customerRepository;
    @Autowired
    CustomerProducer producer;

    @KafkaListener(topics = "allCustomersRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory")
    //@SendTo("allCustomersReplyTopic")
    public void handleAllCustomersRequest() {
        List<Customer> reply = customerRepository.findAll();
        System.out.println("all customers listener");
        producer.allCustomersReply(reply);
    }

    @KafkaListener(topics = "addCustomerRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory")
    public void handleAddCustomerRequest(String message){

    }
}
