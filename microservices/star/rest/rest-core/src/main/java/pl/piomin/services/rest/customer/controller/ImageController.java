package pl.piomin.services.rest.customer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.service.AuthServiceClient;
import pl.piomin.services.rest.customer.service.ProductServiceClient;


@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final AuthServiceClient authServiceClient;
    private final ProductServiceClient productServiceClient;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        if(authServiceClient.validateUser()){
            return productServiceClient.uploadImage(file);
        }
        return null;
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        if(authServiceClient.validateUser()) {
           return productServiceClient.downloadImage(fileName);
        }
        return null;
    }
}
