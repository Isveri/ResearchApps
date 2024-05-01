package io.gatling.test.webSocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.ByteString;
import io.gatling.javaapi.core.ScenarioBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.ws;

public class WebSocketImageSimulation extends WebSocketSimulation {
    @Override
    public void run() {
        repeat1Constant30000duration60(wsHttpProtocol, this);
//        repeat10Constant3000duration60(wsHttpProtocol, this);
//        repeat100Constant300duration60(wsHttpProtocol, this);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("Image upload/dowload")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(session -> session.set("randomid", java.util.UUID.randomUUID().toString()))
                .exec(ws("Open websocket").wsName("Connection-${randomid}-${my_var}")
                        .connect("/ws"))

                .exec(
                        ws("Subscribe uploadImage").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SUBSCRIBE\n" +
                                        "id:sub-0\n" +
                                        "destination:/user/topic/uploadImage\n" +
                                        "\n" +
                                        "\u0000")
                )
                .exec(
                        ws("Send message uploadImage").wsName("Connection-${randomid}-${my_var}")
                                .sendText(session -> {
                                    ByteString byteString;
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
                                    String payload = new Gson().toJson(json);
                                    return "SEND\n" +
                                            "destination:/app/uploadImage\n" +
                                            "\n" +
                                            payload + "\u0000";
                                })
                                .await(1).on(
                                        ws.checkTextMessage("checkUpload")
                                                .check()
                                )
                )
                .exec(
                        ws("Unsub uploadImage").wsName("Connection-${randomid}-${my_var}")
                                .sendText("UNSUBSCRIBE\n" +
                                        "id:sub-0\n" +
                                        "\n" +
                                        "\u0000")
                )


                .exec(
                        ws("Subscribe downloadImage").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SUBSCRIBE\n" +
                                        "id:sub-4\n" +
                                        "destination:/user/topic/downloadImage\n" +
                                        "\n" +
                                        "\u0000")
                )
                .exec(
                        ws("Send message downloadImage").wsName("Connection-${randomid}-${my_var}")
                                .sendText(session -> "SEND\n" +
                                        "destination:/app/downloadImage\n" +
                                        "\n" +
                                        "{\"name\":\"testImage" + session.get("my_var") + ".jpg\"}" + "\u0000")
                                .await(1).on(
                                        ws.checkTextMessage("checkDownload")
                                                .check()
                                )
                )
                .exec(
                        ws("Unsub downloadImage").wsName("Connection-${randomid}-${my_var}")
                                .sendText("UNSUBSCRIBE\n" +
                                        "id:sub-1\n" +
                                        "\n" +
                                        "\u0000")
                )

                .exec(
                        ws("Close WS").wsName("Connection-${randomid}-${my_var}")
                                .close());
    }
}
