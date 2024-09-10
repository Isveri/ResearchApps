package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.piomin.services.grpc.customer.model.Image;
import pl.piomin.services.grpc.customer.repository.ImageRepository;
import pl.piomin.services.grpc.customer.utils.ImageUtils;
import pl.piomin.services.grpc.image.model.ImageProto;
import pl.piomin.services.grpc.image.model.ImageServiceGrpc;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@GrpcService
@RequiredArgsConstructor
public class ImageService extends ImageServiceGrpc.ImageServiceImplBase {

    private final ImageRepository imageRepository;

    @Override
    public void uploadImage(ImageProto.ImageData imageData, StreamObserver<ImageProto.UploadImageResponse> responseObserver) {
        Image imageToSave;
        try {
            imageToSave = Image.builder()
                    .name(imageData.getName())
                    .type(imageData.getType())
                    .imageData(ImageUtils.compressImage(imageData.getData().toByteArray()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageRepository.save(imageToSave);
        String c = "file uploaded successfully : " + imageData.getName();
        responseObserver.onNext(ImageProto.UploadImageResponse.newBuilder().setMessage(c).build());
        responseObserver.onCompleted();
    }

    @Override
    public void downloadImage(ImageProto.DownloadImageRequest imageNameRequest,
                              StreamObserver<ImageProto.DownloadImageResponse> responseObserver) {
        Optional<Image> dbImage = imageRepository.findByName(imageNameRequest.getImageName());
        imageRepository.deleteById(dbImage.get().getId());
        var imageReturn = dbImage.map(image -> {
            try {
                return ImageUtils.decompressImage(image.getImageData());
            } catch (DataFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }).orElse(null);
        responseObserver.onNext(ImageProto.DownloadImageResponse.newBuilder().setImageData(
                ByteString.copyFrom(imageReturn)
        ).build());
        responseObserver.onCompleted();
    }
}
