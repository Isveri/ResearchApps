package pl.piomin.services.rest.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.config.FeignSupportConfig;
import pl.piomin.services.rest.customer.model.Product;

import java.util.List;

@FeignClient(value = "ProductService", url = "http://localhost:8083", configuration = FeignSupportConfig.class)
public interface ProductServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/productAll")
    List<Product> findAll();

    @RequestMapping(method = RequestMethod.POST, value = "/addProduct", consumes = "application/json")
    Product addProduct(@RequestBody Product product);

    @RequestMapping(method = RequestMethod.POST, value = "/updateProduct", consumes = "application/json")
    Product updateProduct(@RequestBody Product product);

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteProduct/{name}")
    Product deleteProduct(@PathVariable String name);

    @RequestMapping(method = RequestMethod.GET, value = "/pdf/{prodName}")
    ResponseEntity<byte[]> getProductPdf(@PathVariable String prodName);

    @PostMapping(value = "/pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<String> uploadPdf(@RequestPart("pdf") MultipartFile file);
}

