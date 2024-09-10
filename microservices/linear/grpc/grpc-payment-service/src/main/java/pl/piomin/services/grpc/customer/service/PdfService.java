package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.piomin.services.grpc.customer.model.Image;
import pl.piomin.services.grpc.customer.repository.ImageRepository;
import pl.piomin.services.grpc.customer.utils.ImageUtils;
import pl.piomin.services.grpc.pdf.model.PdfProto;
import pl.piomin.services.grpc.pdf.model.PdfServiceGrpc;

import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@GrpcService
@RequiredArgsConstructor
public class PdfService extends PdfServiceGrpc.PdfServiceImplBase {

    private final ImageRepository imageRepository;

    @Override
    public void uploadPdf(PdfProto.PdfData pdfData, StreamObserver<PdfProto.UploadPdfResponse> responseObserver) {
        Image pfdToSave;
        try {
            pfdToSave = Image.builder()
                    .name(pdfData.getName())
                    .type(pdfData.getType())
                    .imageData(ImageUtils.compressImage(pdfData.getData().toByteArray()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageRepository.save(pfdToSave);
        String c = "file uploaded successfully : " + pdfData.getName();
        responseObserver.onNext(PdfProto.UploadPdfResponse.newBuilder().setMessage(c).build());
        responseObserver.onCompleted();
    }

    @Override
    public void downloadPdf(PdfProto.DownloadPdfRequest pdfNameRequest,
                            StreamObserver<PdfProto.DownloadPdfResponse> responseObserver) {
        Optional<Image> dbpdf = imageRepository.findByName(pdfNameRequest.getImageName());
        imageRepository.deleteById(dbpdf.get().getId());
        var imageReturn = dbpdf.map(pdf -> {
            try {
                return ImageUtils.decompressImage(pdf.getImageData());
            } catch (DataFormatException | IOException e) {
                throw new RuntimeException(e);
            }
        }).orElse(null);
        responseObserver.onNext(PdfProto.DownloadPdfResponse.newBuilder().setImageData(
                ByteString.copyFrom(imageReturn)
        ).build());
        responseObserver.onCompleted();
    }
}
