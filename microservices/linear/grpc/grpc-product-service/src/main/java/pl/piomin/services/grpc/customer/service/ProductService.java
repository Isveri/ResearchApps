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
import pl.piomin.services.grpc.customer.adapter.ProductAdapter;
import pl.piomin.services.grpc.customer.client.PaymentClient;
import pl.piomin.services.grpc.customer.model.Product;
import pl.piomin.services.grpc.customer.repository.ProductRepository;
import pl.piomin.services.grpc.product.model.ProductProto;
import pl.piomin.services.grpc.product.model.ProductServiceGrpc;

import java.util.List;
import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class ProductService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductRepository repository;
    private final PaymentClient client;
    private final MeterRegistry meterRegistry;


    @Override
    public void findAll(Empty request, StreamObserver<ProductProto.Products> responseObserver) {
        var customerDTOList = repository.findAll();
        List<ProductProto.Product> customerList = ProductAdapter.toProtoList(customerDTOList);
        ProductProto.Products c = ProductProto.Products.newBuilder().addAllProducts(customerList).build();
        Timer timer = meterRegistry.timer("response.time.timer");
        responseObserver.onNext(timer.record(() -> c));
        responseObserver.onCompleted();
    }

    @Override
    public void addProduct(ProductProto.Product request, StreamObserver<ProductProto.Product> responseObserver) {
        if (client.authorizeAddProduct(request.getName())) {
            var customerDTO = repository.save(new Product(null, request.getName(), (long) request.getQuantity()));
            ProductProto.Product c = ProductAdapter.toProto(customerDTO);
            Timer timer = meterRegistry.timer("response.time.timer");
            responseObserver.onNext(timer.record(() -> c));
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(Code.ALREADY_EXISTS_VALUE)
                    .setMessage("Product name already exists")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("Invalid product data")
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void updateProduct(ProductProto.Product request, StreamObserver<ProductProto.Product> responseObserver) {
        Optional<Product> productTemp = repository.findByName(request.getName());
        if (productTemp.isPresent()) {
            Product prd = productTemp.get();
            prd.setQuantity((long) request.getQuantity());
            ProductProto.Product c = ProductAdapter.toProto(repository.save(prd));
            Timer timer = meterRegistry.timer("response.time.timer");
            responseObserver.onNext(timer.record(() -> c));
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(Code.NOT_FOUND_VALUE)
                    .setMessage("Product no found")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("Product name not present")
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }

    @Override
    public void deleteProduct(StringValue request, StreamObserver<ProductProto.Product> responseObserver) {
        String name = request.getValue();
        Optional<Product> productToDelete = repository.findByName(name);
        if (client.authorizeAddProduct(request.getValue()) && productToDelete.isPresent()) { // TODO z jakiegos powodu tu jest authorizeAddProduct a nie delete
            repository.deleteByName(name);
            ProductProto.Product c = ProductAdapter.toProto(productToDelete.get());
            Timer timer = meterRegistry.timer("response.time.timer");
            responseObserver.onNext(timer.record(() -> c));
            responseObserver.onCompleted();
        } else {
            com.google.rpc.Status status = com.google.rpc.Status.newBuilder()
                    .setCode(Code.NOT_FOUND_VALUE)
                    .setMessage("Product name not exists")
                    .addDetails(Any.pack(ErrorInfo.newBuilder()
                            .setReason("Invalid product data")
                            .build()))
                    .build();
            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        }
    }
}