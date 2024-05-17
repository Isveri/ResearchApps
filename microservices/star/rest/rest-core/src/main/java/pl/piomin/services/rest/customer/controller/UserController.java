package pl.piomin.services.rest.customer.controller;


import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.piomin.services.rest.customer.model.User;
import pl.piomin.services.rest.customer.service.AuthServiceClient;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthServiceClient client;

    @PostMapping("/userLogin")
    ResponseEntity<?> loginUser(@RequestBody User user) {
        System.out.println("core login user");
        if(!client.validateUser(user)){
            return new ResponseEntity<>("authorization failed", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("ok",HttpStatus.OK);

    }
}