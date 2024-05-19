package pl.piomin.services.rest.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.piomin.services.rest.customer.model.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    void deleteByName(String name);

    @Transactional
    boolean existsProductByName(String name);

    Optional<Product> findByName(String name);
}
