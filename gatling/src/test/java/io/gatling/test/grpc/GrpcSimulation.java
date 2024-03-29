package io.gatling.test.grpc;

import com.google.protobuf.*;
import io.gatling.grpc.check.GrpcCheckScope;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.grpc.GrpcProtocolBuilder;
import io.grpc.Status;

import pl.piomin.services.grpc.customer.model.CustomerProto;
import pl.piomin.services.grpc.customer.model.CustomersServiceGrpc;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.grpc.GrpcDsl.*;
public class GrpcSimulation extends Simulation {

    AtomicInteger counter = new AtomicInteger(0);
    GrpcProtocolBuilder baseGrpcProtocol = grpc.forAddress("localhost", 9092).useInsecureTrustManager();

    ScenarioBuilder scn1 = scenario("Consumer unary")
            .forever().on(
                    exec(session -> session.set("my_var", counter.getAndIncrement())),
                    grpc("findAll")
                    .unary(CustomersServiceGrpc.getFindAllMethod())
                    .send(Empty.newBuilder().build())
                    .check(statusCode().is(Status.Code.OK)
                            //,response(CustomerProto.Customers::getCustomersList).is()
                    )
                    ,
                    grpc("addCustomer")
                            .unary(CustomersServiceGrpc.getAddCustomerMethod())
                            .send(session -> CustomerProto.Customer.newBuilder()
                                     .setName("newUser")
                                     .setPesel("newUser"+session.get("my_var"))
                                     .build()
                            )
                            .check(statusCode().is(Status.Code.OK))
                    ,
                    grpc("updateCustomer")
                            .unary(CustomersServiceGrpc.getUpdateCustomerMethod())
                            .send(session -> CustomerProto.Customer.newBuilder()
                                    .setName("newUserChanged")
                                    .setPesel("newUser"+session.get("my_var"))
                                    .build()
                            )
                            .check(statusCode().is(Status.Code.OK))
                    ,
                    grpc("deleteCustomer")
                            .unary(CustomersServiceGrpc.getDeleteCustomerMethod())
                            .send(session->StringValue.newBuilder().setValue("newUser"+session.get("my_var")).build())
                            .check(statusCode().is(Status.Code.OK))
            );
            // rozwiazanie w docsach Gatling zwiazane z sprawdzaniem odpowiedzi zapytania nie dziala, nie znajduje klasy ResponseMessage

    {
        setUp(scn1.injectOpen(atOnceUsers(5)))
                .protocols(baseGrpcProtocol).maxDuration(Duration.ofSeconds(120));
    }

}