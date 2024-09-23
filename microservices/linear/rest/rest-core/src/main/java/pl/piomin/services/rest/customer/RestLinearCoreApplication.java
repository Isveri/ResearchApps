package pl.piomin.services.rest.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestLinearCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestLinearCoreApplication.class, args);
    }

}
