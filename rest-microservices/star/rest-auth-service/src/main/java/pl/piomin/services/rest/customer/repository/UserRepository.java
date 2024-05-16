package pl.piomin.services.rest.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piomin.services.rest.customer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginAndPassword(String login, String password);
}
