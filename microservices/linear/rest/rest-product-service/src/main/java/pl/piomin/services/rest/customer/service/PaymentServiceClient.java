package pl.piomin.services.rest.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.config.FeignSupportConfig;
import pl.piomin.services.rest.customer.model.Product;

@FeignClient(value = "PaymentService", url = "${payment_service.url}", configuration = FeignSupportConfig.class)
public interface PaymentServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/productPayment", consumes = "application/json")
    Boolean productPayment(@RequestBody Product product);

    @RequestMapping(method = RequestMethod.POST, value = "/productReturn/{prodName}", consumes = "application/json")
    Double productReturn(@PathVariable String prodName);

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/{prodName}")
    ResponseEntity<byte[]> getProductPdf(@PathVariable String prodName);

    @PostMapping(value = "/pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadPdf(@RequestPart("pdf") MultipartFile file);
}
