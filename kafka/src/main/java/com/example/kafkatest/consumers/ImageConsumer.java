package com.example.kafkatest.consumers;

import com.example.kafkatest.model.Customer;
import com.example.kafkatest.model.Image;
import com.example.kafkatest.producers.CustomerProducer;
import com.example.kafkatest.producers.ImageProducer;
import com.example.kafkatest.repository.CustomerRepository;
import com.example.kafkatest.repository.ImageRepository;

import com.example.kafkatest.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageConsumer {
    private final ImageRepository imageRepository;

    @Autowired
    ImageProducer producer;

    @KafkaListener(topics = "uploadImageRequestTopic",containerFactory = "kafkaListenerContainerImageFactory")
    public void handleUploadImageRequest(Image image, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        var imageToSave = Image.builder()
                .name(image.getName())
                .type(image.getType())
                .imageData(ImageUtils.compressImage(image.getImageData()))
                .build();
        imageRepository.save(imageToSave);
        //TODO kod testowy, po sprawdzeniu limitu requestow usunac
        //imageRepository.deleteById(imageToSave.getId());
        //
        producer.uploadImageReply(image.getName(),key);
    }
    @KafkaListener(topics = "downloadImageRequestTopic",containerFactory = "kafkaListenerContainerMessageFactory")
    public void handleDowloadImageRequest(String imageName, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        Optional<Image> dbImage = imageRepository.findByName(imageName);
        imageRepository.deleteById(dbImage.get().getId());
        var imageReturn= dbImage.map(image -> {
            return ImageUtils.decompressImage(image.getImageData());
        }).orElse(null);
        producer.dowloadImageReply(imageReturn,key);
    }
}
