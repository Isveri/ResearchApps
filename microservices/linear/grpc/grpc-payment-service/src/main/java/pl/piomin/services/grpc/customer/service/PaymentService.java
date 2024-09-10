package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.BoolValue;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.piomin.services.grpc.customer.repository.ProductRepository;
import pl.piomin.services.grpc.payment.model.PaymentServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class PaymentService extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final ProductRepository repository;

    @Override
    public void addProduct(StringValue productName, StreamObserver<BoolValue> responseObserver) {
        var auth = !repository.existsProductByName(String.valueOf(productName));
        BoolValue c = BoolValue.newBuilder().setValue(auth).build();
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProduct(StringValue productName, StreamObserver<DoubleValue> responseObserver) {
        var amount = 0.0;
        if (repository.existsProductByName(String.valueOf(productName))) {
            amount = 123.0;
        }
        DoubleValue c = DoubleValue.newBuilder().setValue(amount).build();
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }
}