package pl.piomin.services.rest.customer.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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
    private final MeterRegistry meterRegistry;

    @GetMapping("/productAll")
    List<Product> findAll() {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authClient::validateUser))) {
            return timer.record(client::findAll);
        }
        return new ArrayList<>();
    }

    @Transactional
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authClient::validateUser))) {
            return timer.record(() -> client.addProduct(product));
        }
        return null;
    }

    @PostMapping("/updateProduct")
    Product updateProduct(@RequestBody Product product) {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authClient::validateUser))) {
            return timer.record(() -> client.updateProduct(product));
        }
        return null;
    }

    @DeleteMapping("/deleteProduct/{name}")
    Product deleteProduct(@PathVariable String name) {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authClient::validateUser))) {
            return timer.record(() -> client.deleteProduct(name));
        }
        return null;
    }
}
