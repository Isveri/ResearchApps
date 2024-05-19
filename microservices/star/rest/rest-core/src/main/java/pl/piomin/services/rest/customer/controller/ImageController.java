package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.service.AuthServiceClient;
import pl.piomin.services.rest.customer.service.ProductServiceClient;
import pl.piomin.services.rest.customer.service.ProductServiceImageClient;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final AuthServiceClient authServiceClient;
    private final ProductServiceImageClient productServiceImageClient;

    @PostMapping
    public String uploadImage(@RequestParam("image") MultipartFile file) {
        if (authServiceClient.validateUser()) {
            return productServiceImageClient.uploadImage(file);
        }
        return null;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
        if (authServiceClient.validateUser()) {
            return productServiceImageClient.downloadImage(fileName);
        }
        return null;
    }
}
