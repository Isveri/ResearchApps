package io.gatling.test.config;

import io.gatling.javaapi.core.OpenInjectionStep;
import io.gatling.javaapi.core.ProtocolBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;

public interface SimulationConfigurator {

    default void runScenario(ProtocolBuilder protocol, Simulation simulation, int repeat, int consUsers, int duration) {
        var scenario = createScenario();
        scenario.repeat(repeat);
        simulation.setUp(scenario.injectOpen(
                        constantUsersPerSec(consUsers).during(Duration.ofSeconds(duration))
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

    ScenarioBuilder createScenario();
}
