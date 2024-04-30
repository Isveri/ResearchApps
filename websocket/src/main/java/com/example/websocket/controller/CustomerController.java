package com.example.websocket.controller;

import com.example.websocket.model.Customer;
import com.example.websocket.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;


    @MessageMapping("/customerAll")
    @SendToUser("/topic/customerAll")
    List<Customer> findAll(){
        System.out.println("customerAll");
        return customerRepository.findAll();
    }


    @MessageMapping("/addCustomer")
    @SendToUser("topic/addCustomer")
    Customer addCustomer(Customer customer){
        Customer savedCustomer=customerRepository.save(customer);
        return savedCustomer;
    }

    @MessageMapping("/updateCustomer")
    @SendToUser("topic/updateCustomer")
    Customer updateCustomer(Customer customer){
        Optional<Customer> cust = customerRepository.findByPesel(customer.getPesel());
        if (cust.isPresent()){
            Customer customerToUpdate = cust.get();
            customerToUpdate.setName(customer.getName());
            Customer updatedCustomer = customerRepository.save(customerToUpdate);
            return updatedCustomer;
        }
        return null;
    }

    @MessageMapping("/deleteCustomer")
    @SendToUser("topic/deleteCustomer")
    Customer deleteCustomer(Customer customer){
        if(customerRepository.existsCustomerByPesel(customer.getPesel())){
            Customer deletedCustomer = customerRepository.findByPesel(customer.getPesel()).get();
            customerRepository.deleteByPesel(customer.getPesel());
            return deletedCustomer;
        }
        return null;
    }
}
