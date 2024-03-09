package io.gatling.rest.demo;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

public class CustomerSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8081")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    ScenarioBuilder scn = scenario("Consumer all")
//            .exec(http("customerAll").get("/customerAll"))
//            .pause(1)
//            .exec(http("updateCustomer").post("/updateCustomer/1")
//                    .body(StringBody("{\"name\": \"test\",\"pesel\": \"test\"}")))
//            .pause(1)
//            .exec(http("customerById").get("/customerById/1"));
            .forever().on(
                    http("customerAll").get("/customerAll"),
                    pause(1),
                    http("updateCustomer").post("/updateCustomer/1")
                        .body(StringBody("{\"name\": \"test\",\"pesel\": \"test\"}")),
                    pause(1),
                    http("customerById").get("/customerById/1")
            );
    {

        //constantUsersPerSec(10).during(Duration.ofMinutes(2)
        setUp(
                scn.injectOpen(
                        //rampUsersPerSec(5).to(10).during(Duration.ofSeconds(120))
                        //rampUsersPerSec(50).to(200).during(Duration.ofSeconds(90))
                        //constantUsersPerSec(200).during(Duration.ofMinutes(2))
                        atOnceUsers(5)
                        )
        ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(2));
    }

}
