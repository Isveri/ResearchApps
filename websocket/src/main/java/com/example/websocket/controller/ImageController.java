package com.example.websocket.controller;

import com.example.websocket.model.Image;
import com.example.websocket.repository.ImageRepository;
import com.example.websocket.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageRepository imageRepository;

    @MessageMapping("/uploadImage")
    @SendToUser("/topic/uploadImage")
    public String uploadImage(Image image) throws IOException {
        var imageToSave = Image.builder()
                .name(image.getName())
                .type(image.getType())
                .imageData(ImageUtils.compressImage(image.getImageData()))
                .build();
        imageRepository.save(imageToSave);
        return "image saved";
    }

    @MessageMapping("/downloadImage")
    @SendToUser("/topic/downloadImage")
    public byte[] downloadImage(Image image) {
        Optional<Image> dbImage = imageRepository.findByName(image.getName());
        imageRepository.deleteById(dbImage.get().getId());
        var imageReturn= dbImage.map(dbimage -> {
            return ImageUtils.decompressImage(dbimage.getImageData());
        }).orElse(null);
        return imageReturn;
    }
}
