package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.repository.ProductRepository;

import java.nio.DoubleBuffer;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final ProductRepository repository;

    @Transactional
    @PostMapping("/productPayment")
    Boolean addProduct(@RequestBody Product product){
        return !repository.existsProductByName(product.getName());
    }

    @PostMapping("/productReturn")
    Double deleteProduct(@RequestBody Product product){
        Double amount=0.0;
        if(repository.existsProductByName(product.getName())){
            amount=123.0;
        }
        return amount;
    }
}
