package pl.piomin.services.rest.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.model.User;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("___clearDatabase___");
    }
}
