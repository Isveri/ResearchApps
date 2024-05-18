package pl.piomin.services.rest.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.piomin.services.rest.customer.model.User;

@FeignClient(value = "authService", url = "http://localhost:8086")
public interface AuthServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/userLogin", consumes = "application/json")
    boolean validateUser();
}
