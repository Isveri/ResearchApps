package pl.piomin.services.rest.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("___clearDatabase___");
    }
}
