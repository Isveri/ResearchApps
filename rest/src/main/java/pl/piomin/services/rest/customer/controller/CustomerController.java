package pl.piomin.services.rest.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Customer;
import pl.piomin.services.rest.customer.repository.CustomerRepository;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repository;

    @GetMapping("/customerById/{id}")
    Customer findById(@PathVariable Long id){
        return repository.findById(id);
    }

    @GetMapping("/customerByPesel/{pesel}")
    Customer findByPesel(@PathVariable String pesel){
        return repository.findByPesel(pesel);
    }

    @GetMapping("/customerAll")
    List<Customer> findAll(){
        return repository.findAll();
    }

    @PostMapping("/addCustomer")
    void addCustomer(@RequestBody Customer customer){
        repository.add(customer.getName(), customer.getPesel());
    }

    @PostMapping("/updateCustomer/{id}")
    void updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        repository.updateCustomer(id,customer.getName(),customer.getPesel());
    }
}
