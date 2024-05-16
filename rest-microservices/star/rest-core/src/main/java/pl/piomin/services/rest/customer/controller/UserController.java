package pl.piomin.services.rest.customer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.services.rest.customer.model.User;
import pl.piomin.services.rest.customer.service.AuthServiceClient;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthServiceClient client;

    @PostMapping("/userLogin")
    void loginUser(@RequestBody User user) {
        //TODO przekazanie usera do authService w celu weryfikacji

        System.out.println("core login user");
        System.out.println("correct"+client.validateUser(user));
    }
}