package pl.piomin.services.grpc.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.piomin.services.grpc.customer.model.Product;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    boolean existsProductByName(String name);
}
