package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.service.ProductServiceClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceClient client;

    @GetMapping("/productAll")
    List<Product> findAll(){
        return client.findAll();
    }

    @Transactional
    @PostMapping("/addProduct")
    Product addProduct(@RequestBody Product product){
        return client.addProduct(product);
    }

    @PostMapping("/updateProduct")
    Product updateProduct(@RequestBody Product product){
        return client.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{name}")
    Product deleteProduct(@PathVariable String name){
        return client.deleteProduct(name);
    }
}
