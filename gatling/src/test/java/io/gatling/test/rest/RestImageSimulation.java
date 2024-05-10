package io.gatling.test.rest;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class RestImageSimulation extends RestSimulation {
    @Override
    public void run() {
//        repeat1Constant30000duration60(httpProtocol, this);
//        repeat10Constant3000duration60(httpProtocol, this);
        repeat100Constant300duration60(httpProtocol, this);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("CRUD image")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(
                        http("imageUpload").post("/image")
                                .header("Content-Type", "multipart/form-data")
                                .bodyPart(RawFileBodyPart("image", "src/test/image/testImage.jpg")
                                        .contentType("image/jpeg").fileName("testImage${my_var}.jpg"))
                                .asMultipartForm()
                                .check(status().is(200))
                )
                .exec(
                        http("imageDowload").get("/image/testImage${my_var}.jpg")
                                .check(status().is(200))
                                .check(bodyBytes().is(RawFileBody("src/test/image/testImage.jpg"))));
    }
}
