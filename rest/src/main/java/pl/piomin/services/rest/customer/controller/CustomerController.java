package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Customer;
import pl.piomin.services.rest.customer.repository.CustomerRepository;
import pl.piomin.services.rest.customer.repository.CustomerRepositoryLocal;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/customerById/{id}")
    Optional<Customer> findById(@PathVariable Long id){
        return customerRepository.findById(id);
    }

//    @GetMapping("/customerByPesel/{pesel}")
//    Customer findByPesel(@PathVariable String pesel){
//        return repository.findByPesel(pesel);
//    }

    @GetMapping("/customerAll")
    List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Transactional
    @PostMapping("/addCustomer")
    void addCustomer(@RequestBody Customer customer){
        customerRepository.save(customer);
    }

    @PostMapping("/updateCustomer/{id}")
    void updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        customer.setId(id);
        customerRepository.save(customer);
    }

    @DeleteMapping("/deleteCustomer/{pesel}")
    void deleteCustomer(@PathVariable String pesel){
        if(customerRepository.existsCustomerByPesel(pesel)){
            customerRepository.deleteByPesel(pesel);
        }
    }
}
