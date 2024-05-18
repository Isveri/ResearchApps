package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.repository.ProductRepository;

import java.nio.DoubleBuffer;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final ProductRepository repository;

    @Transactional
    @PostMapping("/productPayment")
    public Boolean addProduct(@RequestBody Product product){
        return !repository.existsProductByName(product.getName());
    }

    @PostMapping("/productReturn/{prodName}")
    Double deleteProduct(@PathVariable String prodName){
        Double amount=0.0;
        if(repository.existsProductByName(prodName)){
            amount=123.0;
        }
        return amount;
    }
}
