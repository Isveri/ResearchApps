package pl.piomin.services.grpc.customer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.SelfSignedCertificate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import pl.piomin.services.grpc.customer.model.CustomerProto;
import pl.piomin.services.grpc.customer.repository.CustomerRepository;
import pl.piomin.services.grpc.customer.service.CustomersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomerApplication {

    public static void main(String[] args) throws InterruptedException, IOException {
        SpringApplication.run(CustomerApplication.class, args);

    }

    @Bean
    public Server grpcServer(CustomersService customersService) throws InterruptedException, IOException, CertificateException {
        SelfSignedCertificate ssc = new SelfSignedCertificate("localhost");
        Server server = ServerBuilder.forPort(9092)
                .addService(customersService)
                .useTransportSecurity(
                        new FileInputStream(ssc.certificate()),
                        new FileInputStream(ssc.privateKey())
                )
                .build();
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received shutdown request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        }));
        return server;
    }


    @Bean
    CustomerRepository repository() {
        List<CustomerProto.Customer> customers = new ArrayList<>();
        customers.add(CustomerProto.Customer.newBuilder().setId(1).setPesel("12345").setName("Adam Kowalski")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        customers.add(CustomerProto.Customer.newBuilder().setId(2).setPesel("12346").setName("Anna Malinowska")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        customers.add(CustomerProto.Customer.newBuilder().setId(3).setPesel("12347").setName("Paweł Michalski")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        customers.add(CustomerProto.Customer.newBuilder().setId(4).setPesel("12348").setName("Karolina Lewandowska")
                .setType(CustomerProto.Customer.CustomerType.INDIVIDUAL).build());
        return new CustomerRepository(customers);
    }
}
