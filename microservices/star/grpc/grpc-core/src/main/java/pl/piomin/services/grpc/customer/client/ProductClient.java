package pl.piomin.services.grpc.customer.client;

import com.google.protobuf.Empty;
import com.google.protobuf.StringValue;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.piomin.services.grpc.image.model.ImageProto;
import pl.piomin.services.grpc.image.model.ImageServiceGrpc;
import pl.piomin.services.grpc.product.model.ProductProto;
import pl.piomin.services.grpc.product.model.ProductServiceGrpc;

@Service
public class ProductClient {
    private static final Logger LOG = LoggerFactory.getLogger(ProductClient.class);
    @GrpcClient("product-service")
    ProductServiceGrpc.ProductServiceBlockingStub prodStub;

    @GrpcClient("product-service")
    ImageServiceGrpc.ImageServiceBlockingStub imageStub;

    public ProductProto.Products findAll(){
        try {
            return prodStub.findAll(Empty.newBuilder().build());
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public ProductProto.Product addProduct(ProductProto.Product product){
        try {
            return prodStub.addProduct(product);
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public ProductProto.Product updateProduct(ProductProto.Product product){
        try {
            return prodStub.updateProduct(product);
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public ProductProto.Product deleteProduct(StringValue name){
        try {
            return prodStub.deleteProduct(name);
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public ImageProto.UploadImageResponse uploadImage(ImageProto.ImageData image){
        try {
            return imageStub.uploadImage(image);
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
    public ImageProto.DownloadImageResponse downloadImage(ImageProto.DownloadImageRequest name){
        try {
            return imageStub.downloadImage(name);
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
}

