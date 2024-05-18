package pl.piomin.services.rest.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.model.Product;

@FeignClient(value = "PaymentService", url = "http://localhost:8084")
public interface PaymentServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/productPayment", consumes = "application/json")
    Boolean productPayment(@RequestBody Product product);

    @RequestMapping(method = RequestMethod.POST, value = "/productReturn/{prodName}", consumes = "application/json")
    Double productReturn(@PathVariable String prodName);

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/{prodName}")
    ResponseEntity<?> getProductPdf(@PathVariable String prodName);

    @PostMapping
    ResponseEntity<?> uploadPdf(@RequestParam("pdf") MultipartFile file);
}
