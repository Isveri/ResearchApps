package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.service.PaymentServiceClient;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final PaymentServiceClient client;

    @GetMapping("/pdf/{prodName}")
    public ResponseEntity<?> getProductPdf(@PathVariable String prodName) {
        return client.getProductPdf(prodName);
    }

    @PostMapping
    public ResponseEntity<?> uploadPdf(@RequestParam("pdf") MultipartFile file){return client.uploadPdf(file);}
}
