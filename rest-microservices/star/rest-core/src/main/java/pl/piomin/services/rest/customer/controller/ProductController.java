package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Product;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    //TODO dolozyc uzycie interfrjsu feignClient

    @GetMapping("/productAll")
    List<Product> findAll(){

        return null;
    }

    @Transactional
    @PostMapping("/addProduct")
    void addProduct(@RequestBody Product product){

    }

    @PostMapping("/updateCustomer")
    void updateProduct(@RequestBody Product product){

    }

    @DeleteMapping("/deleteCustomer/{name}")
    void deleteProduct(@PathVariable String name){

    }
}
