package pl.piomin.services.rest.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import pl.piomin.services.rest.customer.config.FeignSupportConfig;

@FeignClient(value = "image", url = "http://localhost:8087", configuration = FeignSupportConfig.class)
public interface ProductServiceImageClient {

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(@RequestPart("image") MultipartFile file);

    @GetMapping(value = "/image/{fileName}")
    ResponseEntity<byte[]> downloadImage(@PathVariable String fileName);
}
