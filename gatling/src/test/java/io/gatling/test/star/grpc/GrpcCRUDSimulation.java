package io.gatling.test.star.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.grpc.Status;
import pl.piomin.services.grpc.product.model.ProductProto;
import pl.piomin.services.grpc.product.model.ProductServiceGrpc;

import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.grpc;
import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.statusCode;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class GrpcCRUDSimulation extends GrpcSimulation {


    @Override
    public void run() {
        steadyLoad3R100U60T(grpcConf,this);
//        steadyLoad5R300U60T(grpcConf,this);
//        rampLoad(grpcConf,this);
//        testScenario(grpcConf,this,1,1,6);
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
                                .rpc(ProductServiceGrpc.getFindAllMethod())
                                .payload(Empty.newBuilder().build())
                                .check(statusCode().is(Status.Code.OK))

                )
                .exec(
                        grpc("addProduct")
                                .rpc(ProductServiceGrpc.getAddProductMethod())
                                .payload(session -> ProductProto.Product.newBuilder()
                                        .setName("newProduct"+ session.get("my_var"))
                                        .setQuantity(1)
                                        .build()
                                )
                                .check(statusCode().is(Status.Code.OK))
                )
                .exec(
                        grpc("updateProduct")
                                .rpc(ProductServiceGrpc.getUpdateProductMethod())
                                .payload(session -> ProductProto.Product.newBuilder()
                                        .setName("newProduct"+ session.get("my_var"))
                                        .setQuantity(2)
                                        .build()
                                )
                                .check(statusCode().is(Status.Code.OK))
                )
                .exec(
                        grpc("deleteProduct")
                                .rpc(ProductServiceGrpc.getDeleteProductMethod())
                                .payload(session -> StringValue.newBuilder().setValue("newProduct"+ session.get("my_var")).build())
                                .check(statusCode().is(Status.Code.OK)));
    }
}
