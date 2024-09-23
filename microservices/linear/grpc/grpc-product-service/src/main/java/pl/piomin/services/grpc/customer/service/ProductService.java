package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.Any;
import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import com.google.rpc.Code;
import com.google.rpc.ErrorInfo;
import com.google.rpc.Status;
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
import java.util.concurrent.atomic.AtomicReference;

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
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }

    @Override
    public void addProduct(ProductProto.Product request, StreamObserver<ProductProto.Product> responseObserver) {
        Timer timer = meterRegistry.timer("response.time.timer");
        var auth = new AtomicReference<>(false);
        timer.record(() -> auth.set(client.authorizeAddProduct(request.getName())));
        if (auth.get()) {
            var customerDTO = repository.save(new Product(null, request.getName(), (long) request.getQuantity()));
            ProductProto.Product c = ProductAdapter.toProto(customerDTO);
            responseObserver.onNext(c);
            responseObserver.onCompleted();
        } else {
            Status status = Status.newBuilder()
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
            responseObserver.onNext(c);
            responseObserver.onCompleted();
        } else {
            Status status = Status.newBuilder()
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
        AtomicReference<Double> value = new AtomicReference<>(1.0);
        Timer timer = meterRegistry.timer("response.time.timer");
        timer.record(() -> value.set(client.authorizeDeleteProduct(name)));
        if (Double.valueOf(0.0).equals(value.get()) && productToDelete.isPresent()) {
            repository.deleteByName(name);
            ProductProto.Product c = ProductAdapter.toProto(productToDelete.get());
            responseObserver.onNext(c);
            responseObserver.onCompleted();
        } else {
            Status status = Status.newBuilder()
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