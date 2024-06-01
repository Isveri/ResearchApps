package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.Any;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.piomin.services.grpc.customer.client.ProductClient;
import pl.piomin.services.grpc.product.model.ProductProto;
import pl.piomin.services.grpc.product.model.ProductServiceGrpc;


import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductClient productClient;
    private final MeterRegistry meterRegistry;

    @Override
    public void findAll(Empty request, StreamObserver<ProductProto.Products> responseObserver) {

        Timer timer = meterRegistry.timer("response.time.timer");
        responseObserver.onNext(timer.record(productClient::findAll));
            responseObserver.onCompleted();

    }

    @Override
    public void addProduct(ProductProto.Product request, StreamObserver<ProductProto.Product> responseObserver) {
        Timer timer = meterRegistry.timer("response.time.timer");
        responseObserver.onNext(timer.record(() -> productClient.addProduct(request)));
            responseObserver.onCompleted();
    }
    @Override
    public void updateProduct(ProductProto.Product request, StreamObserver<ProductProto.Product> responseObserver){
        Timer timer = meterRegistry.timer("response.time.timer");
        responseObserver.onNext(timer.record(() -> productClient.updateProduct(request)));
            responseObserver.onCompleted();

    }
    @Override
    public void deleteProduct(StringValue request, StreamObserver<ProductProto.Product> responseObserver){
        Timer timer = meterRegistry.timer("response.time.timer");
        responseObserver.onNext(timer.record(() -> productClient.deleteProduct(request)));
            responseObserver.onCompleted();

    }
}