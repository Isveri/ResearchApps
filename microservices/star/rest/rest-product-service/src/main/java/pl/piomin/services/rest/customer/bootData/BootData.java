package pl.piomin.services.rest.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    private final ProductRepository repository;

    @Override
    public void run(String... args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Drabina", 1L));
        products.add(new Product(2L, "Siekiera", 1L));
        products.add(new Product(3L, "Fotel", 1L));
        repository.saveAll(products);
        System.out.println("_____________add initial products_________");
    }
}
