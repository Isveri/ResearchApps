package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.service.ProductServiceClient;

@RestController
@RequiredArgsConstructor
public class PdfController {

    private final ProductServiceClient client;


    @GetMapping("/pdf/{prodName}")
    public ResponseEntity<?> getProductPdf(@PathVariable String prodName) {
        return client.getProductPdf(prodName);
    }
}
