package pl.piomin.services.rest.customer.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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
    private final MeterRegistry meterRegistry;

    @GetMapping("/productAll")
    List<Product> findAll() {
        return client.findAll();
    }

    @Transactional
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        Timer timer = meterRegistry.timer("response.time.timer");
        return timer.record(() -> client.addProduct(product));
    }

    @PostMapping("/updateProduct")
    Product updateProduct(@RequestBody Product product) {
        Timer timer = meterRegistry.timer("response.time.timer");
        return timer.record(() -> client.updateProduct(product));
    }

    @DeleteMapping("/deleteProduct/{name}")
    Product deleteProduct(@PathVariable String name) {
        Timer timer = meterRegistry.timer("response.time.timer");
        return timer.record(() -> client.deleteProduct(name));
    }
}
