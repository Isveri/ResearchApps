package pl.piomin.services.rest.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piomin.services.rest.customer.model.User;
import pl.piomin.services.rest.customer.repository.UserRepository;


@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.save(new User(1L, "login", "haslo"));
        System.out.println("___add initial user___");
    }
}
