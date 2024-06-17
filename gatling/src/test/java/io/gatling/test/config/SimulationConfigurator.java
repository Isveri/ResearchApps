package io.gatling.test.config;

import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

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
    default void rampScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int maxUsers, int duration) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectOpen(
                        rampUsersPerSec(100).to(maxUsers).during(Duration.ofSeconds(duration))
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }
    default void rampCRUDScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int maxUsers, int duration) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectOpen(
                        rampUsersPerSec(100).to(maxUsers).during(Duration.ofSeconds(duration))
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }
    default void rampImageScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int maxUsers, int duration) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectClosed(
                        rampConcurrentUsers(20).to(maxUsers).during(Duration.ofSeconds(duration))
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }
    default void testScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int users, int duration){
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectOpen(
                        nothingFor(5),
                        constantUsersPerSec(users).during(Duration.ofSeconds(duration))
//                    stressPeakUsers(users).during(Duration.ofSeconds(duration))
//                OpenInjectionStep.atOnceUsers(users)
                )).maxDuration(Duration.ofSeconds(duration))
                .protocols(protocol);
    }

    default void repeat1Constant30000duration60(ProtocolBuilder protocol, Simulation simulation) {
        runScenario(protocol, simulation, 1, 30000, 60);
    }

    default void repeat10Constant3000duration60(ProtocolBuilder protocol, Simulation simulation) {
        runScenario(protocol, simulation, 10, 3000, 60);
    }

    default void repeat100Constant300duration60(ProtocolBuilder protocol, Simulation simulation) {
        runScenario(protocol, simulation, 100, 300, 60);
    }
    default void steadyLoad5R300U60T(ProtocolBuilder protocol, Simulation simulation){
        runScenario(protocol,simulation,5,300,60);
    }
    default void steadyLoad3R100U60T(ProtocolBuilder protocol, Simulation simulation){
        runScenario(protocol,simulation,3,100,60);
    }
    default void stressLoad1R1KU60T(ProtocolBuilder protocol, Simulation simulation){
        stressScenario(protocol,simulation,1,10000,60);
    }
    default void rampLoad(ProtocolBuilder protocol, Simulation simulation){
        rampScenario(protocol,simulation,1,600,60);
    }

    ScenarioBuilder createScenario();
}
