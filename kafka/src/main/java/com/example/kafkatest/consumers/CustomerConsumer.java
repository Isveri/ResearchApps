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
    public void handleAllCustomersRequest() {
        executorService.submit(()->{
            List<Customer> reply = customerRepository.findAll();
            System.out.println("all customers listener");
            producer.allCustomersReply(reply);
        });
//        List<Customer> reply = customerRepository.findAll();
//        System.out.println("all customers listener");
//        producer.allCustomersReply(reply);
    }

    @KafkaListener(topics = "addCustomerRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory")
    public void handleAddCustomerRequest(Customer customer){
        List<Customer> reply = customerRepository.findAll();
        Customer temp = customerRepository.save(customer);
        System.out.println("add customer listener: "+customer );
        temp.setName("newName");
        Customer tempUpdated = customerRepository.save(temp);
        //String pesel = tempUpdated.getPesel();
//        if(customerRepository.existsCustomerByPesel(pesel)){
//            Customer deletedCustomer = customerRepository.findByPesel(pesel).get();
//            customerRepository.deleteByPesel(pesel);
//            producer.deleteCustomersReply(deletedCustomer);
//        }
        producer.addCustomersReply(temp);

    }
    @KafkaListener(topics = "updateCustomerRequestTopic",containerFactory = "kafkaListenerContainerCustomerFactory")
    public void handleUpdateCustomerRequest(Customer customer){
        System.out.println("customer pesel: "+customer.getPesel());
        Optional<Customer> cust = customerRepository.findByPesel(customer.getPesel());
        if (cust.isPresent()){
            Customer customerToUpdate = cust.get();
            customerToUpdate.setName(customer.getName());
            customerRepository.save(customerToUpdate);
            producer.updateCustomersReply(customerToUpdate);
        }


    }
    @KafkaListener(topics = "deleteCustomerRequestTopic",containerFactory = "kafkaListenerContainerMessageFactory")
    public void handleDeleteCustomerRequest(String pesel){
        if(customerRepository.existsCustomerByPesel(pesel)){
            Customer deletedCustomer = customerRepository.findByPesel(pesel).get();
            customerRepository.deleteByPesel(pesel);
            producer.deleteCustomersReply(deletedCustomer);
        }

    }
}
