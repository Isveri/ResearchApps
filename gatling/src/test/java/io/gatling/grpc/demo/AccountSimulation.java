package io.gatling.grpc.demo;

import com.google.protobuf.Empty;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.grpc.GrpcProtocolBuilder;
import io.grpc.Status;
import pl.piomin.services.grpc.customer.model.AccountsServiceGrpc;
import pl.piomin.services.grpc.customer.model.CustomersServiceGrpc;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.holdFor;
import static io.gatling.javaapi.grpc.GrpcDsl.grpc;
import static io.gatling.javaapi.grpc.GrpcDsl.statusCode;

//public class AccountSimulation extends Simulation {
//    GrpcProtocolBuilder baseGrpcProtocol = Configuration.baseGrpcProtocol("localhost",9091);
//
//    ScenarioBuilder unary = scenario("Account unarry")
//            .exec(grpc("findAll")
//                    .unary(AccountsServiceGrpc.getFindAllMethod())
//                    .send(Empty.newBuilder().build())
//                    .check(
//                            statusCode().is(Status.Code.OK)
//                    )
//            );
//    {
//        ScenarioBuilder  scn=unary;
//        setUp(scn.injectOpen(atOnceUsers(2))
//                .throttle(reachRps(4).in(10),holdFor(Duration.ofMinutes(1))))
//                .protocols(baseGrpcProtocol);
//    }
//
//}
