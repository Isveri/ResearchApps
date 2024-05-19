package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.repository.ProductRepository;
import pl.piomin.services.rest.customer.service.PaymentServiceClient;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository repository;
    private final PaymentServiceClient client;

    @GetMapping("/productAll")
    List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        if (client.productPayment(product)) {
            return repository.save(product);
        }
        return null;
    }

    @PostMapping("/updateProduct")
    Product updateProduct(@RequestBody Product product) {
        Optional<Product> productTemp = repository.findByName(product.getName());
        if (productTemp.isPresent()) {
            Product prd = productTemp.get();
            prd.setQuantity(product.getQuantity());
            return repository.save(prd);
        }
        return null;
    }

    @DeleteMapping("/deleteProduct/{name}")
    Product deleteProduct(@PathVariable String name) {
        Double amount = client.productReturn(name);
        if (amount == 123.0) {
            repository.deleteByName(name);
        }
        return null;
    }
}
