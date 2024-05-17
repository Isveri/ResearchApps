package pl.piomin.services.rest.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.Product;
import pl.piomin.services.rest.customer.model.User;

import java.util.List;

@FeignClient(value = "ProductService", url = "http://localhost:8084")
public interface ProductServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/productAll")
    List<Product> findAll();

    @RequestMapping(method = RequestMethod.POST, value = "/addProduct", consumes = "application/json")
    Product addProduct(@RequestBody Product product);

    @RequestMapping(method = RequestMethod.POST, value = "/updateProduct", consumes = "application/json")
    Product updateProduct(@RequestBody Product product);

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteProduct/{name}")
    Product deleteProduct(@PathVariable String name);
}