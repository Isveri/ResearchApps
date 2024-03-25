package pl.piomin.services.rest.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.piomin.services.rest.customer.model.Customer;
import pl.piomin.services.rest.customer.repository.CustomerRepositoryLocal;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CustomerRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerRestApplication.class, args);
    }

    @Bean
    CustomerRepositoryLocal repository() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L,"12345","Adam Kowalski"));
        customers.add(new Customer(2L,"12346","Anna Malinowska"));
        customers.add(new Customer(3L,"12347","Paweł Michalski"));
        customers.add(new Customer(4L,"12348","Karolina Lewandowska"));
        return new CustomerRepositoryLocal(customers);
    }
}
