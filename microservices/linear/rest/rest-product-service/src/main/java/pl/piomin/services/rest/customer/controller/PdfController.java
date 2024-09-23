package pl.piomin.services.rest.customer.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.service.PaymentServiceClient;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final PaymentServiceClient client;
    private final MeterRegistry meterRegistry;

    @GetMapping("/pdf/{prodName}")
    public ResponseEntity<byte[]> getProductPdf(@PathVariable String prodName) {
        Timer timer = meterRegistry.timer("response.time.timer");
        return timer.record(() -> client.getProductPdf(prodName));
    }

    @PostMapping("/pdf")
    public ResponseEntity<String> uploadPdf(@RequestParam("pdf") MultipartFile file) {
        Timer timer = meterRegistry.timer("response.time.timer");
        return timer.record(() -> client.uploadPdf(file));
    }
}
