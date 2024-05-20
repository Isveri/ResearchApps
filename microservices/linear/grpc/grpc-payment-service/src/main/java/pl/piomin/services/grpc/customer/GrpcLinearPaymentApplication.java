package pl.piomin.services.grpc.customer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.util.SelfSignedCertificate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.piomin.services.grpc.customer.service.PdfService;
import pl.piomin.services.grpc.customer.service.PaymentService;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;

@SpringBootApplication
public class GrpcLinearPaymentApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcLinearPaymentApplication.class, args);
    }

    @Bean
    public Server grpcServer(PaymentService paymentService, PdfService pdfService) throws IOException, CertificateException {
        SelfSignedCertificate ssc = new SelfSignedCertificate("localhost");
        Server server = ServerBuilder.forPort(9090)
                .addService(paymentService)
                .addService(pdfService)
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

}
