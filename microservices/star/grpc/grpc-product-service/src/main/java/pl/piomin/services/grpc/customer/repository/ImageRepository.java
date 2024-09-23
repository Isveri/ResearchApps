package pl.piomin.services.grpc.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piomin.services.grpc.customer.model.Image;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByName(String name);

    boolean existsByName(String name);
}
