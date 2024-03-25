package io.gatling.test.rest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class RestSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8082")
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
                    http("customerAll").get("/customerAll")
                            .check(status().is(200))
                            .check(jsonPath("$").ofList())
                            .check(jsonPath("$[*].id").findAll().exists())
                            .check(jsonPath("$[*].pesel").findAll().exists())
                            .check(jsonPath("$[*].name").findAll().exists())
                    ,

                    http("updateCustomer").post("/updateCustomer/1")
                        .body(StringBody("{\"name\": \"test\",\"pesel\": \"test\"}"))
                            .check(status().is(200))
                    ,
                    http("addCustomer").post("/addCustomer")
                                    .body(StringBody("{\"name\": \"newUser\",\"pesel\": \"newUser\"}"))
                            .check(status().is(200))

                    ,

                    http("deleteCustomer").delete("/deleteCustomer/newUser")
                            .check(status().is(200))
            );
    {

        //constantUsersPerSec(10).during(Duration.ofMinutes(2)
        setUp(
                scn.injectOpen(
                        //rampUsersPerSec(5).to(10).during(Duration.ofSeconds(120))
                        //rampUsersPerSec(50).to(200).during(Duration.ofSeconds(90))
                        //constantUsersPerSec(200).during(Duration.ofMinutes(2))
                        atOnceUsers(2)
                        )
        ).protocols(httpProtocol).maxDuration(Duration.ofSeconds(5));
    }

}
