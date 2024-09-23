package pl.piomin.services.rest.customer.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.service.AuthServiceClient;
import pl.piomin.services.rest.customer.service.ProductServiceImageClient;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final AuthServiceClient authServiceClient;
    private final ProductServiceImageClient productServiceImageClient;
    private final MeterRegistry meterRegistry;

    @PostMapping
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authServiceClient::validateUser))) {
            return timer.record(() -> productServiceImageClient.uploadImage(file));
        }
        return null;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authServiceClient::validateUser))) {
            return timer.record(() -> productServiceImageClient.downloadImage(fileName));
        }
        return null;
    }
}
