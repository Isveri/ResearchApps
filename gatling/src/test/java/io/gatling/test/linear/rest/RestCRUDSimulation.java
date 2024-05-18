package io.gatling.test.linear.rest;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class RestCRUDSimulation extends RestSimulation {

    @Override
    public void run() {
        steadyLoad5R300U60T(httpProtocol,this);
//        rampLoad(httpProtocol,this);
//        stressLoad1R1KU60T(httpProtocol,this);
//        testScenario(httpProtocol,this,1,2,6);
//        repeat1Constant30000duration60(httpProtocol, this);
//        repeat10Constant3000duration60(httpProtocol, this);
//        repeat100Constant300duration60(httpProtocol, this);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("Scenario 1 - auth and product")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(
                        http("productAll").get("/productAll")
                                .check(status().is(200))
                                .check(jsonPath("$").ofList())
                                .check(jsonPath("$[*].id").findAll().exists())
                                .check(jsonPath("$[*].name").findAll().exists())
                                .check(jsonPath("$[*].quantity").findAll().exists()))
                .exec(
                        http("addProduct").post("/addProduct")
                                .body(StringBody("{\"name\": \"newProduct${my_var}\",\"quantity\": \"1\"}"))
                                .check(status().is(200))
                )
                .exec(
                        http("updateProduct").post("/updateProduct")
                                .body(StringBody("{\"name\": \"newProduct${my_var}\",\"quantity\": \"2\"}"))
                                .check(status().is(200))
                )
                .exec(
                        http("deleteProduct").delete("/deleteProduct/newProduct${my_var}")
                                .check(status().is(200)));
    }

}
