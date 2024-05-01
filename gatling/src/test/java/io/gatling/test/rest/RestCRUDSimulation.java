package io.gatling.test.rest;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class RestCRUDSimulation extends RestSimulation {

    @Override
    public void run() {
        repeat1Constant30000duration60(httpProtocol, this);
//        repeat10Constant3000duration60(httpProtocol, this);
//        repeat100Constant300duration60(httpProtocol, this);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("CRUD consumer")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(
                        http("customerAll").get("/customerAll")
                                .check(status().is(200))
                                .check(jsonPath("$").ofList())
                                .check(jsonPath("$[*].id").findAll().exists())
                                .check(jsonPath("$[*].pesel").findAll().exists())
                                .check(jsonPath("$[*].name").findAll().exists()));
//                .exec(
//                        http("addCustomer").post("/addCustomer")
//                                .body(StringBody("{\"name\": \"newUser\",\"pesel\": \"newUser${my_var}\"}"))
//                                .check(status().is(200))
//                )
//                .exec(
//                        http("updateCustomer").post("/updateCustomer/newUser${my_var}")
//                                .body(StringBody("{\"name\": \"newUserChanged\",\"pesel\": \"test\"}"))
//                                .check(status().is(200))
//                )
//                .exec(
//                        http("deleteCustomer").delete("/deleteCustomer/newUser${my_var}")
//                                .check(status().is(200)));
    }

}
