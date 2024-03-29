package pl.piomin.services.grpc.customer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.SelfSignedCertificate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.piomin.services.grpc.customer.model.CustomerProto;
import pl.piomin.services.grpc.customer.repository.CustomerRepositoryLocal;
import pl.piomin.services.grpc.customer.service.CustomersService;
import pl.piomin.services.grpc.customer.service.ImageService;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CustomerGrpcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerGrpcApplication.class, args);

    }

    @Bean
    public Server grpcServer(CustomersService customersService, ImageService imageService) throws IOException, CertificateException {
        SelfSignedCertificate ssc = new SelfSignedCertificate("localhost");
        Server server = ServerBuilder.forPort(9092)
                .addService(customersService)
                .addService(imageService)
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
    CustomerRepositoryLocal repository() {
        List<CustomerProto.Customer> customers = new ArrayList<>();
        customers.add(CustomerProto.Customer.newBuilder().setId(1).setPesel("12345").setName("Adam Kowalski")
                .build());
        customers.add(CustomerProto.Customer.newBuilder().setId(2).setPesel("12346").setName("Anna Malinowska")
                .build());
        customers.add(CustomerProto.Customer.newBuilder().setId(3).setPesel("12347").setName("Paweł Michalski")
                .build());
        customers.add(CustomerProto.Customer.newBuilder().setId(4).setPesel("12348").setName("Karolina Lewandowska")
                .build());
        return new CustomerRepositoryLocal(customers);
    }
}
