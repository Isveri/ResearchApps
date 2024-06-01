package pl.piomin.services.rest.customer.controller;


import feign.Response;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
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
    private final MeterRegistry meterRegistry;

    @PostMapping("/userLogin")
    ResponseEntity<?> loginUser() {
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        System.out.println("core login user");
        if (!Boolean.TRUE.equals(timer2.record(client::validateUser))) {
            return new ResponseEntity<>("authorization failed", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("ok", HttpStatus.OK);

    }
}