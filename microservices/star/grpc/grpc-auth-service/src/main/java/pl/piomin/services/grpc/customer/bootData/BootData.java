package pl.piomin.services.grpc.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piomin.services.grpc.customer.model.User;
import pl.piomin.services.grpc.customer.repository.UserRepository;


@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.save(new User(1L, "login", "password"));
        System.out.println("___add initial user___");
    }
}
