package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.service.AuthServiceClient;
import pl.piomin.services.rest.customer.service.ProductServiceClient;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceClient client;
    private final AuthServiceClient authClient;

    @GetMapping("/productAll")
    List<Product> findAll() {
        if (authClient.validateUser()) {
            return client.findAll();
        }
        return new ArrayList<>();
    }

    @Transactional
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        if (authClient.validateUser()) {
            return client.addProduct(product);
        }
        return null;
    }

    @PostMapping("/updateProduct")
    Product updateProduct(@RequestBody Product product) {
        if (authClient.validateUser()) {
            return client.updateProduct(product);
        }
        return null;
    }

    @DeleteMapping("/deleteProduct/{name}")
    Product deleteProduct(@PathVariable String name) {
        if (authClient.validateUser()) {
            return client.deleteProduct(name);
        }
        return null;
    }
}
