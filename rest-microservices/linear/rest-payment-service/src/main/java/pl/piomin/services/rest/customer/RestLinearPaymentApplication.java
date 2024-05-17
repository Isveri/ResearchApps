package pl.piomin.services.rest.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import pl.piomin.services.rest.customer.model.Image;
import pl.piomin.services.rest.customer.utils.ImageUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootApplication
public class RestLinearPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestLinearPaymentApplication.class, args);
    }

}
