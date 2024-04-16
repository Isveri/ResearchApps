package io.gatling.test.grpc;

//import com.github.phisgr.gatling.grpc.protocol.StaticGrpcProtocol;
//import com.google.protobuf.*;
////import io.gatling.grpc.check.GrpcCheckScope;
//import io.gatling.javaapi.core.ScenarioBuilder;
//import io.gatling.javaapi.core.Simulation;
//
////import io.gatling.javaapi.grpc.GrpcProtocolBuilder;
//import io.grpc.Status;
//
//
//import lombok.val;
//import pl.piomin.services.grpc.customer.model.CustomerProto;
//import pl.piomin.services.grpc.customer.model.CustomersServiceGrpc;
//import pl.piomin.services.grpc.image.model.ImageProto;
//import pl.piomin.services.grpc.image.model.ImageServiceGrpc;
//
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.concurrent.atomic.AtomicInteger;
//
//
////import static com.github.phisgr.gatling.grpc.Predef.grpc;
//
//import static com.github.phisgr.gatling.grpc.Predef.managedChannelBuilder;
//import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.grpc;
//import static io.gatling.javaapi.core.CoreDsl.*;
////import static io.gatling.javaapi.grpc.GrpcDsl.*;
//import static io.gatling.javaapi.http.HttpDsl.*;


import com.github.phisgr.gatling.kt.grpc.StaticGrpcProtocol;
import com.google.protobuf.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import pl.piomin.services.grpc.customer.model.CustomerProto;
import pl.piomin.services.grpc.customer.model.CustomersServiceGrpc;
import pl.piomin.services.grpc.image.model.ImageProto;
import pl.piomin.services.grpc.image.model.ImageServiceGrpc;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class GrpcSimulation extends Simulation {

    AtomicInteger counter = new AtomicInteger(0);
    //GrpcProtocolBuilder baseGrpcProtocol = grpc.forAddress("localhost", 9092).useInsecureTrustManager();
    StaticGrpcProtocol grpcConf = grpc(ManagedChannelBuilder.forAddress("localhost", 9094).usePlaintext());


    ScenarioBuilder scn1 = scenario("new grpc plugin customer")
            .forever().on(
                exec(session -> session.set("my_var", counter.getAndIncrement()))
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
                                        .setPesel("newUser"+session.get("my_var"))
                                        .build()
                                )
                                .check(statusCode().is(Status.Code.OK))
                )
                .exec(
                        grpc("updateCustomer")
                                .rpc(CustomersServiceGrpc.getUpdateCustomerMethod())
                                .payload(session -> CustomerProto.Customer.newBuilder()
                                        .setName("newUserChanged")
                                        .setPesel("newUser"+session.get("my_var"))
                                        .build()
                                )
                                .check(statusCode().is(Status.Code.OK))
                )
                .exec(
                        grpc("deleteCustomer")
                                .rpc(CustomersServiceGrpc.getDeleteCustomerMethod())
                                .payload(session-> StringValue.newBuilder().setValue("newUser"+session.get("my_var")).build())
                                .check(statusCode().is(Status.Code.OK))
                )
            );

    ScenarioBuilder scn2 = scenario("new grpc plugin image")
            .forever().on(
                    exec(session -> session.set("my_var", counter.getAndIncrement()))
                     .exec(
                             grpc("imageUpload")
                             .rpc(ImageServiceGrpc.getUploadImageMethod())
                             .payload(session -> {
                                 ByteString byteString;
                                 try {
                                     BufferedImage image = ImageIO.read(new File("src/test/image/testImage.jpg"));
                                     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                                     ImageIO.write(image, "jpeg", outputStream);
                                     byte[] imageBytes = outputStream.toByteArray();

                                     // Konwertuj tablicę bajtów na ByteString
                                     byteString = ByteString.copyFrom(imageBytes);
                                 } catch (IOException e) {
                                     throw new RuntimeException(e);
                                 }
                                 return ImageProto.ImageData.newBuilder()
                                         .setName("testImage" + session.get("my_var") + ".jpg")
                                         .setType("image/jpeg")
                                         .setData(byteString)
                                         .build();
                             })
                             .check(statusCode().is(Status.Code.OK)))

                     .exec(
                             grpc("imageDowload")
                                     .rpc(ImageServiceGrpc.getDownloadImageMethod())
                                     .payload(session ->
                                             ImageProto.DownloadImageRequest.newBuilder()
                                                     .setImageName("testImage" + session.get("my_var") + ".jpg")
                                                     .build()
                                     )
                                     .check(statusCode().is(Status.Code.OK))
                     )
            );

    {
        setUp(scn2.injectOpen(
                //atOnceUsers(50)
                constantUsersPerSec(30).during(Duration.ofMinutes(1))
        ))
                .protocols(grpcConf).maxDuration(Duration.ofSeconds(60));
    }

}