package com.example.kafkatest.producers;

import com.example.kafkatest.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaMessageTemplate;
    @Autowired
    private KafkaTemplate<String, byte[]> kafkaImageTemplate;

    public void uploadImageReply(String imageName) {
        kafkaMessageTemplate.send("uploadImageReplyTopic","key1","Saved image: "+imageName);
    }
    public void dowloadImageReply(byte[] image){
        kafkaImageTemplate.send("uploadImageReplyTopic","key2", image);
    }
}
