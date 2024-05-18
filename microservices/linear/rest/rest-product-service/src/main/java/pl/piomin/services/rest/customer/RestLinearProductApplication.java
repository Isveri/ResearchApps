package pl.piomin.services.rest.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RestLinearProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestLinearProductApplication.class, args);
    }

}
