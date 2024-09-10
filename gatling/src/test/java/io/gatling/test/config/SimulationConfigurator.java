package io.gatling.test.config;

import io.gatling.javaapi.core.ProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static io.gatling.javaapi.core.CoreDsl.*;

public interface SimulationConfigurator {

    AtomicInteger counter = new AtomicInteger(0);

    default void runScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int consUsers, int duration) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectOpen(
                        constantUsersPerSec(consUsers).during(Duration.ofSeconds(duration))
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }

    default void concurrentScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int consUsers, int duration) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectClosed(
                        constantConcurrentUsers(consUsers).during(Duration.ofSeconds(duration))
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }

    default void stressScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int maxUsers, int duration) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectOpen(
                        stressPeakUsers(maxUsers).during(Duration.ofSeconds(duration))
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }

    default void rampScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int maxUsers, int duration, int v) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectOpen(
                        rampUsersPerSec(v).to(maxUsers).during(Duration.ofSeconds(duration))
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }

    ScenarioBuilder createScenario();
}
