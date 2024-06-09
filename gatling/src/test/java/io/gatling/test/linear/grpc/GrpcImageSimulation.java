package io.gatling.test.linear.grpc;

import com.google.protobuf.ByteString;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.grpc.Status;
import pl.piomin.services.grpc.image.model.ImageProto;
import pl.piomin.services.grpc.image.model.ImageServiceGrpc;
import pl.piomin.services.grpc.pdf.model.PdfProto;
import pl.piomin.services.grpc.pdf.model.PdfServiceGrpc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.grpc;
import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.statusCode;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class GrpcImageSimulation extends GrpcSimulation {
    @Override
    public void run() {
//        rampScenario(grpcConf, this, 1, 1200, 60);
//        concurrentScenario(grpcConf,this,1,50,60);
        concurrentScenario(grpcConf,this,2,100,60);

    }

    @Override
    public ScenarioBuilder createScenario() {
        return scenario("new grpc plugin image")
                .exec(session -> session.set("my_var", counter.getAndIncrement()))
                .exec(
                        grpc("pdfUpload")
                                .rpc(PdfServiceGrpc.getUploadPdfMethod())
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
                                    return PdfProto.PdfData.newBuilder()
                                            .setName("testImage" + session.get("my_var") + ".jpg")
                                            .setType("image/jpeg")
                                            .setData(byteString)
                                            .build();
                                })
                                .check(statusCode().is(Status.Code.OK)))

                .exec(
                        grpc("pdfDowload")
                                .rpc(PdfServiceGrpc.getDownloadPdfMethod())
                                .payload(session ->
                                        PdfProto.DownloadPdfRequest.newBuilder()
                                                .setImageName("testImage" + session.get("my_var") + ".jpg")
                                                .build()
                                )
                                .check(statusCode().is(Status.Code.OK))
                );
    }
}
