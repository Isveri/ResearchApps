package pl.piomin.services.grpc.customer.adapter;

import pl.piomin.services.grpc.customer.model.Product;
import pl.piomin.services.grpc.product.model.ProductProto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductAdapter {
    public static ProductProto.Product toProto(Product product) {
        return ProductProto.Product.newBuilder()
                .setId(Math.toIntExact(product.getId()))
                .setName(product.getName())
                .setQuantity(Math.toIntExact(product.getQuantity()))
                .build();
    }

    public static List<ProductProto.Product> toProtoList(List<Product> products) {
        return products.stream()
                .map(ProductAdapter::toProto)
                .collect(Collectors.toList());
    }
}
