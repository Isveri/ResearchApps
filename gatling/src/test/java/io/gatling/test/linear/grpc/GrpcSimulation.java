package io.gatling.test.linear.grpc;


import com.github.phisgr.gatling.kt.grpc.StaticGrpcProtocol;
import io.gatling.javaapi.core.Simulation;
import io.gatling.test.config.CustomRunner;
import io.gatling.test.config.SimulationConfigurator;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.atomic.AtomicInteger;

import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.grpc;

public abstract class GrpcSimulation extends Simulation implements SimulationConfigurator, CustomRunner {

    AtomicInteger counter = new AtomicInteger(0);
    StaticGrpcProtocol grpcConf = grpc(ManagedChannelBuilder.forAddress("localhost", 9068).usePlaintext());

    {
        run();
    }
}