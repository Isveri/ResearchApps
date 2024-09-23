package pl.piomin.services.rest.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.piomin.services.rest.customer.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    boolean existsProductByName(String name);
}
