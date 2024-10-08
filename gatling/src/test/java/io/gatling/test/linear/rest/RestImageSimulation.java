package io.gatling.test.linear.rest;

import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class RestImageSimulation extends RestSimulation {
    @Override
    public void run() {
        rampScenario(httpProtocol, this, 1, 100, 60, 2);
//        concurrentScenario(httpProtocol,this,1,50,60);
//        concurrentScenario(httpProtocol,this,2,100,60);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("Scenario 2 - image upload and download")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(
                        http("pdfUpload").post("/pdf")
                                .header("Content-Type", "multipart/form-data")
                                .bodyPart(RawFileBodyPart("pdf", "src/test/image/testImage.jpg")
                                        .contentType("image/jpeg").fileName("testImage${my_var}.jpg"))
                                .asMultipartForm()
                                .check(status().is(200))
                                .check(bodyString().not("file upload failed"))
                )
                .exec(
                        http("pdfDownload").get("/pdf/testImage${my_var}.jpg")
                                .check(status().is(200))
                                .check(bodyBytes().is(RawFileBody("src/test/image/testImage.jpg"))));
    }
}
