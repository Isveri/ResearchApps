package pl.piomin.services.grpc.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piomin.services.grpc.customer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginAndPassword(String login, String password);
}
