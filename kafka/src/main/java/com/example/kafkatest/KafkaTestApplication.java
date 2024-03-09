package com.example.kafkatest;

import com.example.kafkatest.model.Customer;
import com.example.kafkatest.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class KafkaTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaTestApplication.class, args);
	}
	@Bean
	CustomerRepository repository() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L,"12345","Adam Kowalski"));
		customers.add(new Customer(2L,"12346","Anna Malinowska"));
		customers.add(new Customer(3L,"12347","Paweł Michalski"));
		customers.add(new Customer(4L,"12348","Karolina Lewandowska"));
		return new CustomerRepository(customers);
	}
}
