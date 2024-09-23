package pl.piomin.services.rest.customer.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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
    private final MeterRegistry meterRegistry;

    @GetMapping("/productAll")
    List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        Timer timer = meterRegistry.timer("response.time.timer");
        if (Boolean.TRUE.equals(timer.record(() -> client.productPayment(product)))) {
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
        Timer timer = meterRegistry.timer("response.time.timer");
        Double amount = timer.record(() -> client.productReturn(name));
        if (Double.valueOf(123.0).equals(amount)) {
            repository.deleteByName(name);
        }
        return null;
    }
}
