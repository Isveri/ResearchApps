package io.gatling.test.webSocket;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.ws;

public class WebSocketCRUDSimulation extends WebSocketSimulation {
    @Override
    public void run() {
        repeat1Constant30000duration60(wsHttpProtocol, this);
//        repeat10Constant3000duration60(wsHttpProtocol, this);
//        repeat100Constant300duration60(wsHttpProtocol, this);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("Customer CRUD")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(session -> session.set("randomid", java.util.UUID.randomUUID().toString()))
                .exec(
                        ws("Open websocket").wsName("Connection-${randomid}-${my_var}")
                                .connect("/ws"))

                .exec(
                        ws("Subscribe customerAll").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SUBSCRIBE\n" +
                                        "id:sub-0\n" +
                                        "destination:/user/topic/customerAll\n" +
                                        "\n" +
                                        "\u0000")
                )
                .exec(
                        ws("Send message customerAll").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SEND\n" +
                                        "destination:/app/customerAll\n" +
                                        "\n" +
                                        "{}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                )
                .exec(
                        ws("Unsub customerAll").wsName("Connection-${randomid}-${my_var}")
                                .sendText("UNSUBSCRIBE\n" +
                                        "id:sub-0\n" +
                                        "\n" +
                                        "\u0000")
                )

                .exec(
                        ws("Subscribe addCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SUBSCRIBE\n" +
                                        "id:sub-1\n" +
                                        "destination:/user/topic/addCustomer\n" +
                                        "\n" +
                                        "\u0000")
                )
                .exec(
                        ws("Send message addCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SEND\n" +
                                        "destination:/app/addCustomer\n" +
                                        "\n" +
                                        "{\"name\": \"newUser\",\"pesel\": \"newUser${my_var}\"}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                )
                .exec(
                        ws("Unsub addCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("UNSUBSCRIBE\n" +
                                        "id:sub-1\n" +
                                        "\n" +
                                        "\u0000")
                )

                .exec(
                        ws("Subscribe updateCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SUBSCRIBE\n" +
                                        "id:sub-2\n" +
                                        "destination:/user/topic/updateCustomer\n" +
                                        "\n" +
                                        "\u0000")
                )
                .exec(
                        ws("Send message updateCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SEND\n" +
                                        "destination:/app/updateCustomer\n" +
                                        "\n" +
                                        "{\"name\": \"newUserChanged\",\"pesel\": \"newUser${my_var}\"}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                )
                .exec(
                        ws("Unsub updateCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("UNSUBSCRIBE\n" +
                                        "id:sub-2\n" +
                                        "\n" +
                                        "\u0000")
                )

                .exec(
                        ws("Subscribe deleteCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SUBSCRIBE\n" +
                                        "id:sub-3\n" +
                                        "destination:/user/topic/deleteCustomer\n" +
                                        "\n" +
                                        "\u0000")
                )
                .exec(
                        ws("Send message deleteCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("SEND\n" +
                                        "destination:/app/deleteCustomer\n" +
                                        "\n" +
                                        "{\"name\": \"\",\"pesel\": \"newUser${my_var}\"}\u0000")
//                                    .await(5).on(
//                                            ws.checkTextMessage("checkCustomerAll")
//                                                    .matching(jsonPath("$.destination").is("/topic/customerAll"))
//                                    )
                )
                .exec(
                        ws("Unsub deleteCustomer").wsName("Connection-${randomid}-${my_var}")
                                .sendText("UNSUBSCRIBE\n" +
                                        "id:sub-3\n" +
                                        "\n" +
                                        "\u0000")
                )

                .exec(
                        ws("Close WS").wsName("Connection-${randomid}-${my_var}")
                                .close());
    }
}
