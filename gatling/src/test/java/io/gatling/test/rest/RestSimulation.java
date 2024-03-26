package io.gatling.test.rest;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import lombok.val;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static scala.Predef.Map;

public class RestSimulation extends Simulation {
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8082")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    ScenarioBuilder scn1 = scenario("CRUD consumer")
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
    ScenarioBuilder scn2 = scenario("CRUD image")
            .forever().on(
                    http("imageUpload").post("/image")
                            .header("Content-Type", "multipart/form-data")
                            .bodyPart(RawFileBodyPart("image", "src/test/image/testImage.jpg")
                                    .contentType("image/jpeg").fileName("testImage.jpg"))
                            .asMultipartForm()
                            .check(status().is(200)),
                    http("imageDowload").get("/image/testImage.jpg")
                            .check(status().is(200))
                            .check(bodyBytes().is(RawFileBody("src/test/image/testImage.jpg")))
            );
    {

        //constantUsersPerSec(10).during(Duration.ofMinutes(2)
        setUp(
                scn1.injectOpen(
                        //rampUsersPerSec(5).to(10).during(Duration.ofSeconds(120))
                        //rampUsersPerSec(50).to(200).during(Duration.ofSeconds(90))
                        //constantUsersPerSec(200).during(Duration.ofMinutes(2))
                        atOnceUsers(1)
                        )
        ).protocols(httpProtocol).maxDuration(Duration.ofSeconds(60));
    }

}
