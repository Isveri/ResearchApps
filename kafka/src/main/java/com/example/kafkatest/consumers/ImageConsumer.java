package com.example.kafkatest.consumers;

import com.example.kafkatest.model.Customer;
import com.example.kafkatest.model.Image;
import com.example.kafkatest.producers.CustomerProducer;
import com.example.kafkatest.producers.ImageProducer;
import com.example.kafkatest.repository.CustomerRepository;
import com.example.kafkatest.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageConsumer {
    private final ImageRepository imageRepository;

    @Autowired
    ImageProducer producer;

    @KafkaListener(topics = "uploadImageRequestTopic",containerFactory = "kafkaListenerContainerImageFactory")
    public void handleUploadImageRequest(Image image) {
        imageRepository.save(image);
        producer.uploadImageReply(image.getName());
    }
    @KafkaListener(topics = "uploadImageRequestTopic",containerFactory = "kafkaListenerContainerMessageFactory")
    public void handleDowloadImageRequest(String imageName) {
        Image image= imageRepository.findByName(imageName).get();
        producer.dowloadImageReply(image.getImageData());
    }
}
