package io.gatling.test.star.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.grpc.Status;
import pl.piomin.services.grpc.customer.model.CustomerProto;
import pl.piomin.services.grpc.customer.model.CustomersServiceGrpc;

import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.grpc;
import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.statusCode;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class GrpcCRUDSimulation extends GrpcSimulation {


    @Override
    public void run() {
//        steadyLoad5R300U60T(grpcConf,this);
        rampLoad(grpcConf,this);
//        testScenario(grpcConf,this,100,450,60);
//        repeat1Constant30000duration60(grpcConf, this);
//        repeat10Constant3000duration60(grpcConf, this);
//        repeat100Constant300duration60(grpcConf, this);
    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("new grpc plugin customer")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(
                        grpc("findAll")
                                .rpc(CustomersServiceGrpc.getFindAllMethod())
                                .payload(Empty.newBuilder().build())
                                .check(statusCode().is(Status.Code.OK))

                )
                .exec(
                        grpc("addCustomer")
                                .rpc(CustomersServiceGrpc.getAddCustomerMethod())
                                .payload(session -> CustomerProto.Customer.newBuilder()
                                        .setName("newUser")
                                        .setPesel("newUser" + session.get("my_var"))
                                        .build()
                                )
                                .check(statusCode().is(Status.Code.OK))
                )
                .exec(
                        grpc("updateCustomer")
                                .rpc(CustomersServiceGrpc.getUpdateCustomerMethod())
                                .payload(session -> CustomerProto.Customer.newBuilder()
                                        .setName("newUserChanged")
                                        .setPesel("newUser" + session.get("my_var"))
                                        .build()
                                )
                                .check(statusCode().is(Status.Code.OK))
                )
                .exec(
                        grpc("deleteCustomer")
                                .rpc(CustomersServiceGrpc.getDeleteCustomerMethod())
                                .payload(session -> StringValue.newBuilder().setValue("newUser" + session.get("my_var")).build())
                                .check(statusCode().is(Status.Code.OK)));
    }
}
