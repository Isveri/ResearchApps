package pl.piomin.services.rest.customer.bootData;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piomin.services.rest.customer.model.Customer;
import pl.piomin.services.rest.customer.repository.CustomerRepository;
import pl.piomin.services.rest.customer.repository.CustomerRepositoryLocal;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Component
public class BootData implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L,"12345","Adam Kowalski"));
        customers.add(new Customer(2L,"12346","Anna Malinowska"));
        customers.add(new Customer(3L,"12347","Paweł Michalski"));
        customers.add(new Customer(4L,"12348","Karolina Lewandowska"));
        customers.add(new Customer(5L,"12349","Karolina Lewandowska"));
        customerRepository.saveAll(customers);
        System.out.println("_____________test_________");
    }
}