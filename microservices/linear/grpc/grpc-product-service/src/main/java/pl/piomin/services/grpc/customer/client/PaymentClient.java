package pl.piomin.services.grpc.customer.client;

import com.google.protobuf.StringValue;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.piomin.services.grpc.payment.model.PaymentServiceGrpc;
import pl.piomin.services.grpc.pdf.model.PdfProto;
import pl.piomin.services.grpc.pdf.model.PdfServiceGrpc;
import pl.piomin.services.grpc.product.model.ProductProto;


@Service
public class PaymentClient {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentClient.class);

    @GrpcClient("payment-service")
    PaymentServiceGrpc.PaymentServiceBlockingStub paymentStub;

    @GrpcClient("payment-service")
    PdfServiceGrpc.PdfServiceBlockingStub pdfStub;

    public Boolean authorizeAddProduct(String productName){
        try {
            return paymentStub.addProduct(StringValue.of(productName)).getValue();
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public Double authorizeDeleteProduct(String productName){
        try {
            return paymentStub.deleteProduct(StringValue.of(productName)).getValue();
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public PdfProto.UploadPdfResponse uploadPdf(PdfProto.PdfData pdf){
        try {
            return pdfStub.uploadPdf(pdf);
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public PdfProto.DownloadPdfResponse downloadPdf(PdfProto.DownloadPdfRequest name){
        try {
            return pdfStub.downloadPdf(name);
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
}
