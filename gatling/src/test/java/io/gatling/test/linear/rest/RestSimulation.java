package io.gatling.test.linear.rest;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.test.config.CustomRunner;
import io.gatling.test.config.SimulationConfigurator;

import java.util.concurrent.atomic.AtomicInteger;

import static io.gatling.javaapi.http.HttpDsl.http;

public abstract class RestSimulation extends Simulation implements SimulationConfigurator, CustomRunner {

    AtomicInteger counter = new AtomicInteger(0);
    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8082")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    {
        run();
    }
}
