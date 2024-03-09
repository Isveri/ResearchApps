package io.gatling.grpc.demo;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.grpc.GrpcProtocolBuilder;
import io.grpc.Status;
import pl.piomin.services.grpc.customer.model.CustomersServiceGrpc;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.grpc.GrpcDsl.*;
public class CustomerSimulation extends Simulation {

    GrpcProtocolBuilder baseGrpcProtocol = Configuration.baseGrpcProtocol("localhost", 9092);

    ScenarioBuilder unaryFindAll = scenario("Consumer unary")
            .forever().on(exec(grpc("findAll")
                    .unary(CustomersServiceGrpc.getFindAllMethod())
                    .send(Empty.newBuilder().build())
                    .check(
                            statusCode().is(Status.Code.OK)
                    )
            ));
    ScenarioBuilder unaryFindById = scenario("Find by id unary")
            .exec(grpc("findById")
                    .unary(CustomersServiceGrpc.getFindByIdMethod())
                    .send(Int32Value.of(1))
                    .check(
                            statusCode().is(Status.Code.OK)
                    )
            );
    {
        ScenarioBuilder  scn=unaryFindAll;
        setUp(scn.injectOpen(atOnceUsers(5)))
                .protocols(baseGrpcProtocol).maxDuration(Duration.ofMinutes(1));
    }

}