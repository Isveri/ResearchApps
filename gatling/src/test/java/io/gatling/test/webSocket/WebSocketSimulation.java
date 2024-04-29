package io.gatling.test.webSocket;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.val;

import java.time.Duration;
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
            .userAgentHeader("Gatling")
            .wsBaseUrl("ws://localhost:8085");

//    String serverId = RandomUtils.nextInt(100, 1000).toString;
//    String sessionId = RandomStringUtils.randomAlphanumeric(8);
//    String transport = "websocket";

    ScenarioBuilder scn1 = scenario("WebSocket").repeat(1).on(
            exec(session -> session.set("my_var", counter.getAndIncrement()))
                    .exec(session -> session.set("randomid", java.util.UUID.randomUUID().toString()))
                    .exec(ws("Open websocket").wsName("Connection-${randomid}-${my_var}")
              .connect("/ws")
                    .onConnected(
                            exec(ws("Subscribe").wsName("Connection-${randomid}-${my_var}")
                              .sendText("SUBSCRIBE\n" +
                                      "id:sub-0\n" +
                                      "destination:/topic/customerAll\n" +
                                      "\n" +
                                      "\u0000")
                            )
                            .exec(ws("Send message").wsName("Connection-${randomid}-${my_var}")
                                    .sendText("SEND\n" +
                                            "destination:/app/customerAll\n" +
                                            "name: user${my_var}"+
                                            "\n" +
                                            "{}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    //.matching(jsonPath("$.destination").is("/topic/customerAll"))
////                                                    .check(regex("MESSAGE\n"+
////                                                            "destination:/topic/customerAll\n"+
////                                                            "content-type:application/json; charset=UTF-8\n"+
////                                                            "subscription:sub-0\n"+
////                                                            "message-id:[\\w\\d-]*\n" +
////                                                            "\n"+
////                                                            "{\"content\":[.*]}\u0000")
////                                                    )
//                                                    .check()
////                                                    .check(jsonPath("$").ofList(),
////                                                            jsonPath("$[*].id").findAll().exists(),
////                                                            jsonPath("$[*].pesel").findAll().exists(),
////                                                            jsonPath("$[*].name").findAll().exists())
//                                                    //.check()
//                                    )
                                )
                           //         .exec(session -> session.get("response"))
                            .exec(ws("Close WS").wsName("Connection-${randomid}-${my_var}")
                                    .close())
                    )
            )
            );
    {
        setUp(
                scn1.injectOpen(
                        //rampUsersPerSec(5).to(10).during(Duration.ofSeconds(120))
                        //rampUsersPerSec(50).to(200).during(Duration.ofSeconds(90))
                        //constantUsersPerSec(30).during(Duration.ofMinutes(1))
                        atOnceUsers(1)
                )
        ).protocols(httpProtocol).maxDuration(Duration.ofSeconds(60));
    }

}
