package io.gatling.test.kafka;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.gatling.javaapi.core.ScenarioBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.github.amerousful.kafka.javaapi.KafkaDsl.kafka;

public class KafkaImageSimulation extends KafkaSimulation {

    @Override
    public void run() {
//        testScenario(kafkaProtocol, this, 1, 1, 10);
//        runScenario(kafkaProtocol, this, 1, 300, 60);
//        repeat1Constant30000duration60(kafkaProtocol, this);
//        repeat10Constant3000duration60(kafkaProtocol, this);
        repeat100Constant300duration60(kafkaProtocol, this);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return
                scenario("Image upload/dowload").
                        exec(session -> session.set("my_var", counter.getAndIncrement()))
                        .exec(kafka("image upload")
                                .requestReply()
                                .topic("uploadImageRequestTopic")
                                .payload(session -> {
                                    byte[] imageBytes;
                                    try {
                                        imageBytes = Files.readAllBytes(Paths.get("src/test/image/testImage.jpg"));
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    JsonObject json = new JsonObject();
                                    json.addProperty("name", "testImage" + session.get("my_var") + ".jpg");
                                    json.addProperty("type", "image/jpeg");
                                    json.addProperty("imageData", Base64.getEncoder().encodeToString(imageBytes));
                                    return new Gson().toJson(json);
                                })
                                .replyTopic("uploadImageReplyTopic")
                                .key("key-#{my_var}-1")
                                .check()
                        )
                        .exec(kafka("image download")
                                .requestReply()
                                .topic("downloadImageRequestTopic")
                                .payload("testImage${my_var}.jpg")
                                .replyTopic("uploadImageReplyTopic")
                                .key("key-#{my_var}-2")
                                .check()
                        );
    }
}
