package io.gatling.test.webSocket;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.test.config.CustomRunner;
import io.gatling.test.config.SimulationConfigurator;

import java.util.concurrent.atomic.AtomicInteger;

import static io.gatling.javaapi.http.HttpDsl.http;


public abstract class WebSocketSimulation extends Simulation implements SimulationConfigurator, CustomRunner {

    {
        run();
    }

    AtomicInteger counter = new AtomicInteger(0);
    HttpProtocolBuilder wsHttpProtocol = http.baseUrl("http://localhost:8085")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Gatling2")
            .wsBaseUrl("ws://localhost:8085")
            .wsMaxReconnects(5);

}
