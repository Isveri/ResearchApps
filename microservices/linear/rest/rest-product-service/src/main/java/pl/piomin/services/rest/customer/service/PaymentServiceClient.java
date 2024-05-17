package pl.piomin.services.rest.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.piomin.services.rest.customer.model.Product;

@FeignClient(value = "PaymentService", url = "http://localhost:8083")
public interface PaymentServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/productPayment", consumes = "application/json")
    Boolean productPayment(@RequestBody Product product);

    @RequestMapping(method = RequestMethod.POST, value = "/productReturn/{prodName}", consumes = "application/json")
    Double productReturn(@PathVariable String prodName);

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/{prodName}")
    ResponseEntity<?> getProductPdf(@PathVariable String prodName);
}
