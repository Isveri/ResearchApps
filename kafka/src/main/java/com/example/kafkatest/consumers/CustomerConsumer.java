package com.example.kafkatest.consumers;

import com.example.kafkatest.model.Customer;
import com.example.kafkatest.producers.CustomerProducer;
import com.example.kafkatest.repository.CustomerRepository;
import com.example.kafkatest.repository.CustomerRepositoryLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class CustomerConsumer {

    private final CustomerRepository customerRepository;
    @Autowired
    CustomerProducer producer;

    ExecutorService executorService = Executors.newFixedThreadPool(30);

    @KafkaListener(topics = "allCustomersRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory",
            concurrency = "1")
    //@SendTo("allCustomersReplyTopic")
    public void handleAllCustomersRequest(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
//        executorService.submit(()->{
//            List<Customer> reply = customerRepository.findAll();
//            //System.out.println("all customers listener");
//            //System.out.println("Received message with key: " + key);
//            producer.allCustomersReply(reply, key);
//        });
        List<Customer> reply = customerRepository.findAll();
//        System.out.println("all customers listener");
        producer.allCustomersReply(reply,key);
    }

    @KafkaListener(topics = "addCustomerRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory")
    public void handleAddCustomerRequest(Customer customer, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key){

        List<Customer> reply = customerRepository.findAll();
        Customer temp = customerRepository.save(customer);
        //System.out.println("add customer listener: "+customer );
        temp.setName("newName");
        Customer tempUpdated = customerRepository.save(temp);
        //String pesel = tempUpdated.getPesel();
//        if(customerRepository.existsCustomerByPesel(pesel)){
//            Customer deletedCustomer = customerRepository.findByPesel(pesel).get();
//            customerRepository.deleteByPesel(pesel);
//            producer.deleteCustomersReply(deletedCustomer);
//        }
        producer.addCustomersReply(temp, key);

    }
    @KafkaListener(topics = "updateCustomerRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory")
    public void handleUpdateCustomerRequest(Customer customer, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key){
//        System.out.println("customer pesel: "+customer.getPesel());
        Optional<Customer> cust = customerRepository.findByPesel(customer.getPesel());
        if (cust.isPresent()){
            Customer customerToUpdate = cust.get();
            customerToUpdate.setName(customer.getName());
            customerRepository.save(customerToUpdate);
            producer.updateCustomersReply(customerToUpdate, key);
        }


    }
    @KafkaListener(topics = "deleteCustomerRequestTopic",containerFactory = "kafkaListenerContainerMessageFactory")
    public void handleDeleteCustomerRequest(String pesel, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key){
        if(customerRepository.existsCustomerByPesel(pesel)){
            Customer deletedCustomer = customerRepository.findByPesel(pesel).get();
            customerRepository.deleteByPesel(pesel);
            producer.deleteCustomersReply(deletedCustomer, key);
        }

    }
}
