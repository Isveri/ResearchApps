package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.*;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import pl.piomin.services.grpc.customer.model.Product;
import pl.piomin.services.grpc.customer.repository.ProductRepository;
import pl.piomin.services.grpc.payment.model.PaymentServiceGrpc;


import java.util.List;
import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class PaymentService extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final ProductRepository repository;

    @Override
    public void addProduct(StringValue productName, StreamObserver<BoolValue> responseObserver){
        Boolean auth = !repository.existsProductByName(String.valueOf(productName));
        BoolValue c = BoolValue.newBuilder().setValue(auth).build();
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProduct(StringValue productName, StreamObserver<DoubleValue> responseObserver){
        Double amount = 0.0;
        if (repository.existsProductByName(String.valueOf(productName))) {
            amount = 123.0;
        }
        DoubleValue c = DoubleValue.newBuilder().setValue(amount).build();
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }
}