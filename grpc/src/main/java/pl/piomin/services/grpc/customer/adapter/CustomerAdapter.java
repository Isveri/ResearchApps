package pl.piomin.services.grpc.customer.adapter;

import pl.piomin.services.grpc.customer.model.Customer;
import pl.piomin.services.grpc.customer.model.CustomerProto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerAdapter {
    public static CustomerProto.Customer toProto(Customer customer) {
        return CustomerProto.Customer.newBuilder()
                .setId(Math.toIntExact(customer.getId()))
                .setPesel(customer.getPesel())
                .setName(customer.getName())
                .build();
    }

    public static List<CustomerProto.Customer> toProtoList(List<Customer> customers) {
        return customers.stream()
                .map(CustomerAdapter::toProto)
                .collect(Collectors.toList());
    }
}
