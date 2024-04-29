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
    List<Customer> findAll(final Principal principal){
        return customerRepository.findAll();
    }

    @Transactional
    @PostMapping("/addCustomer")
    void addCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    @PostMapping("/updateCustomer/{pesel}")
    void updateCustomer(@PathVariable String pesel, @RequestBody Customer customer){
        Customer customerTemp = customerRepository.findByPesel(pesel).get();
        customerTemp.setName(customer.getName());
        customerRepository.save(customerTemp);
    }

    @DeleteMapping("/deleteCustomer/{pesel}")
    void deleteCustomer(@PathVariable String pesel){
        if(customerRepository.existsCustomerByPesel(pesel)){
            customerRepository.deleteByPesel(pesel);
        }
    }
}
