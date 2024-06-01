package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.Any;
import com.google.protobuf.ByteString;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.piomin.services.grpc.customer.client.AuthClient;
import pl.piomin.services.grpc.customer.client.ProductClient;
import pl.piomin.services.grpc.image.model.ImageProto;
import pl.piomin.services.grpc.image.model.ImageServiceGrpc;

import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class ImageService extends ImageServiceGrpc.ImageServiceImplBase {

    private final AuthClient authClient;
    private final ProductClient productClient;
    private final MeterRegistry meterRegistry;

    @Override
    public void uploadImage(ImageProto.ImageData imageData, StreamObserver<ImageProto.UploadImageResponse> responseObserver) {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authClient::loginUser))) {
            responseObserver.onNext(timer.record(() -> productClient.uploadImage(imageData)));
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(Code.PERMISSION_DENIED_VALUE)
                    .setMessage("Permission denied")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("Invalid user data")
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void downloadImage(ImageProto.DownloadImageRequest imageNameRequest,
                              StreamObserver<ImageProto.DownloadImageResponse> responseObserver) {
        Timer timer = meterRegistry.timer("response.time.timer");
        Timer timer2 = meterRegistry.timer("response.time.timer2");
        if (Boolean.TRUE.equals(timer2.record(authClient::loginUser))) {
            responseObserver.onNext(timer.record(() -> productClient.downloadImage(imageNameRequest)));
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(Code.PERMISSION_DENIED_VALUE)
                    .setMessage("Permission denied")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("Invalid user data")
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }
}
