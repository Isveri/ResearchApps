package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.Any;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
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


    @Override
    public void findAll(Empty request, StreamObserver<ProductProto.Products> responseObserver) {

            responseObserver.onNext(productClient.findAll());
            responseObserver.onCompleted();

    }

    @Override
    public void addProduct(ProductProto.Product request, StreamObserver<ProductProto.Product> responseObserver) {

            responseObserver.onNext(productClient.addProduct(request));
            responseObserver.onCompleted();
    }
    @Override
    public void updateProduct(ProductProto.Product request, StreamObserver<ProductProto.Product> responseObserver){

            responseObserver.onNext(productClient.updateProduct(request));
            responseObserver.onCompleted();

    }
    @Override
    public void deleteProduct(StringValue request, StreamObserver<ProductProto.Product> responseObserver){
            responseObserver.onNext(productClient.deleteProduct(request));
            responseObserver.onCompleted();

    }
}