package pl.piomin.services.grpc.customer.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.piomin.services.grpc.customer.client.PaymentClient;
import pl.piomin.services.grpc.pdf.model.PdfProto;
import pl.piomin.services.grpc.pdf.model.PdfServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class PdfService extends PdfServiceGrpc.PdfServiceImplBase {

    private final PaymentClient paymentClient;
    @Override
    public void uploadPdf(PdfProto.PdfData pdfData, StreamObserver<PdfProto.UploadPdfResponse> responseObserver){
            responseObserver.onNext(paymentClient.uploadPdf(pdfData));
            responseObserver.onCompleted();
    }
    @Override
    public void downloadPdf(PdfProto.DownloadPdfRequest pdfNameRequest,
                              StreamObserver<PdfProto.DownloadPdfResponse> responseObserver) {
            responseObserver.onNext(paymentClient.downloadPdf(pdfNameRequest));
            responseObserver.onCompleted();
    }
}
