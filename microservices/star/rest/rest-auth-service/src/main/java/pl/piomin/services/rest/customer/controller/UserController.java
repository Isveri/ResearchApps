package pl.piomin.services.rest.customer.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.piomin.services.rest.customer.model.User;
import pl.piomin.services.rest.customer.repository.UserRepository;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserRepository repository;

    @PostMapping("/userLogin")
    boolean loginUser(@RequestBody User user){
        System.out.println("auth service login user");
        return repository.existsByLoginAndPassword(user.getLogin(), user.getPassword());
    }
}
