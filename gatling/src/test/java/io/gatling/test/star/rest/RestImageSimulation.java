package io.gatling.test.star.rest;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class RestImageSimulation extends RestSimulation {
    @Override
    public void run() {
//        runScenario(httpProtocol, this, 10, 300, 60);
//        runScenario(httpProtocol, this, 5, 300, 60);
        rampScenario(httpProtocol, this, 1, 1200, 60);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("Scenario 2 - image upload and download")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(
                        http("imageUpload").post("/image")
                                .header("Content-Type", "multipart/form-data")
                                .bodyPart(RawFileBodyPart("image", "src/test/image/testImage.jpg")
                                        .contentType("image/jpeg").fileName("testImage${my_var}.jpg"))
                                .asMultipartForm()
                                .check(status().is(200))
                                .check(bodyString().not("file upload failed"))
                )
                .exec(
                        http("imageDownload").get("/image/testImage${my_var}.jpg")
                                .check(status().is(200))
                                .check(bodyBytes().is(RawFileBody("src/test/image/testImage.jpg"))));
    }
}
