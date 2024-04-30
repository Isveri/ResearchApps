package io.gatling.test.webSocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.ByteString;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static scala.Predef.Map;


public class WebSocketSimulation extends Simulation {

    AtomicInteger counter = new AtomicInteger(0);
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8085")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Gatling2")
            .wsBaseUrl("ws://localhost:8085")
            .wsMaxReconnects(5);

//    String serverId = RandomUtils.nextInt(100, 1000).toString;
//    String sessionId = RandomStringUtils.randomAlphanumeric(8);
//    String transport = "websocket";

    ScenarioBuilder scn1 = scenario("Customer CRUD").repeat(1).on(
            exec(session -> session.set("my_var", counter.getAndIncrement()))
                    .exec(session -> session.set("randomid", java.util.UUID.randomUUID().toString()))
                    .exec(ws("Open websocket").wsName("Connection-${randomid}-${my_var}")
                        .connect("/ws"))

                    .exec(ws("Subscribe customerAll").wsName("Connection-${randomid}-${my_var}")
                            .sendText("SUBSCRIBE\n" +
                                      "id:sub-0\n" +
                                      "destination:/user/topic/customerAll\n" +
                                      "\n" +
                                      "\u0000")
                    )
                    .exec(ws("Send message customerAll").wsName("Connection-${randomid}-${my_var}")
                                    .sendText("SEND\n" +
                                            "destination:/app/customerAll\n" +
                                            "\n" +
                                            "{}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                    )
                    .exec(ws("Unsub customerAll").wsName("Connection-${randomid}-${my_var}")
                            .sendText("UNSUBSCRIBE\n" +
                                    "id:sub-0\n" +
                                    "\n" +
                                    "\u0000")
                    )

                    .exec(ws("Subscribe addCustomer").wsName("Connection-${randomid}-${my_var}")
                            .sendText("SUBSCRIBE\n" +
                                    "id:sub-1\n" +
                                    "destination:/user/topic/addCustomer\n" +
                                    "\n" +
                                    "\u0000")
                    )
                    .exec(ws("Send message addCustomer").wsName("Connection-${randomid}-${my_var}")
                                    .sendText("SEND\n" +
                                            "destination:/app/addCustomer\n" +
                                            "\n" +
                                            "{\"name\": \"newUser\",\"pesel\": \"newUser${my_var}\"}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                    )
                    .exec(ws("Unsub addCustomer").wsName("Connection-${randomid}-${my_var}")
                            .sendText("UNSUBSCRIBE\n" +
                                    "id:sub-1\n" +
                                    "\n" +
                                    "\u0000")
                    )

                    .exec(ws("Subscribe updateCustomer").wsName("Connection-${randomid}-${my_var}")
                            .sendText("SUBSCRIBE\n" +
                                    "id:sub-2\n" +
                                    "destination:/user/topic/updateCustomer\n" +
                                    "\n" +
                                    "\u0000")
                    )
                    .exec(ws("Send message updateCustomer").wsName("Connection-${randomid}-${my_var}")
                                    .sendText("SEND\n" +
                                            "destination:/app/updateCustomer\n" +
                                            "\n" +
                                            "{\"name\": \"newUserChanged\",\"pesel\": \"newUser${my_var}\"}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                    )
                    .exec(ws("Unsub updateCustomer").wsName("Connection-${randomid}-${my_var}")
                            .sendText("UNSUBSCRIBE\n" +
                                    "id:sub-2\n" +
                                    "\n" +
                                    "\u0000")
                    )

                    .exec(ws("Subscribe deleteCustomer").wsName("Connection-${randomid}-${my_var}")
                            .sendText("SUBSCRIBE\n" +
                                    "id:sub-3\n" +
                                    "destination:/user/topic/deleteCustomer\n" +
                                    "\n" +
                                    "\u0000")
                    )
                    .exec(ws("Send message deleteCustomer").wsName("Connection-${randomid}-${my_var}")
                                    .sendText("SEND\n" +
                                            "destination:/app/deleteCustomer\n" +
                                            "\n" +
                                            "{\"name\": \"\",\"pesel\": \"newUser${my_var}\"}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                    )
                    .exec(ws("Unsub deleteCustomer").wsName("Connection-${randomid}-${my_var}")
                            .sendText("UNSUBSCRIBE\n" +
                                    "id:sub-3\n" +
                                    "\n" +
                                    "\u0000")
                    )

                    .exec(ws("Close WS").wsName("Connection-${randomid}-${my_var}")
                                    .close())
            );
    ScenarioBuilder scn2 = scenario("Image upload/dowload").repeat(10).on(
            exec(session -> session.set("my_var", counter.getAndIncrement()))
                    .exec(session -> session.set("randomid", java.util.UUID.randomUUID().toString()))
                    .exec(ws("Open websocket").wsName("Connection-${randomid}-${my_var}")
                            .connect("/ws"))

                    .exec(ws("Subscribe uploadImage").wsName("Connection-${randomid}-${my_var}")
                            .sendText("SUBSCRIBE\n" +
                                    "id:sub-0\n" +
                                    "destination:/user/topic/uploadImage\n" +
                                    "\n" +
                                    "\u0000")
                    )
                    .exec(ws("Send message uploadImage").wsName("Connection-${randomid}-${my_var}")
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
                                            payload+"\u0000";
                                    })
                                    .await(1).on(
                                            ws.checkTextMessage("checkUpload")
                                                    .check()
                                    )
                    )
                    .exec(ws("Unsub uploadImage").wsName("Connection-${randomid}-${my_var}")
                            .sendText("UNSUBSCRIBE\n" +
                                    "id:sub-0\n" +
                                    "\n" +
                                    "\u0000")
                    )


                    .exec(ws("Subscribe downloadImage").wsName("Connection-${randomid}-${my_var}")
                            .sendText("SUBSCRIBE\n" +
                                    "id:sub-4\n" +
                                    "destination:/user/topic/downloadImage\n" +
                                    "\n" +
                                    "\u0000")
                    )
                    .exec(ws("Send message downloadImage").wsName("Connection-${randomid}-${my_var}")
                                    .sendText(session -> {

                                        return "SEND\n" +
                                                "destination:/app/downloadImage\n" +
                                                "\n" +
                                                "{\"name\":\"testImage"+session.get("my_var")+".jpg\"}"+"\u0000";
                                    })
                                    .await(1).on(
                                            ws.checkTextMessage("checkDownload")
                                                    .check()
                                    )
                    )
                    .exec(ws("Unsub downloadImage").wsName("Connection-${randomid}-${my_var}")
                            .sendText("UNSUBSCRIBE\n" +
                                    "id:sub-1\n" +
                                    "\n" +
                                    "\u0000")
                    )

                    .exec(ws("Close WS").wsName("Connection-${randomid}-${my_var}")
                            .close())
    );
    {
        setUp(
                scn2.injectOpen(
                        constantUsersPerSec(5).during(Duration.ofMinutes(1))
                        //atOnceUsers(1)
                )
        ).protocols(httpProtocol).maxDuration(Duration.ofSeconds(60));
    }

}
