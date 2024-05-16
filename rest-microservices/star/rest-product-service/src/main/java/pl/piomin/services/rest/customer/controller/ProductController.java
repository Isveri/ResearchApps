package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.repository.ProductRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository repository;

    @GetMapping("/customerAll")
    List<Product> findAll(){
        return repository.findAll();
    }

    @Transactional
    @PostMapping("/addProduct")
    void addProduct(@RequestBody Product product){
        repository.save(product);
    }

    @PostMapping("/updateCustomer")
    void updateProduct(@RequestBody Product product){
        //TODO do ustalenia co bedzie updatowane
    }

    @DeleteMapping("/deleteCustomer/{name}")
    void deleteProduct(@PathVariable String name){
        if(repository.existsProductByName(name)){
            repository.deleteByName(name);
        }
    }
}
