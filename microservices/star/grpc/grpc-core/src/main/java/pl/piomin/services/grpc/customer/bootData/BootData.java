package pl.piomin.services.grpc.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


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
