package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.repository.ProductRepository;
import pl.piomin.services.rest.customer.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository repository;

    @GetMapping("/productAll")
    List<Product> findAll() {
        return repository.findAll();
    }

    @Transactional
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product) {
        if (repository.existsProductByName(product.getName())) {
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

    @Transactional
    @DeleteMapping("/deleteProduct/{name}")
    public Product deleteProduct(@PathVariable String name) {
        if (repository.existsProductByName(name)) {
            Product temp = repository.findByName(name).get();
            repository.deleteByName(name);
            return temp;
        }
        return null;
    }
}
