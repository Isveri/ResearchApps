package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import pl.piomin.services.grpc.customer.adapter.CustomerAdapter;
import pl.piomin.services.grpc.customer.model.Customer;
import pl.piomin.services.grpc.customer.model.CustomerProto;
import pl.piomin.services.grpc.customer.model.CustomersServiceGrpc;
import pl.piomin.services.grpc.customer.repository.CustomerRepository;
import pl.piomin.services.grpc.customer.repository.CustomerRepositoryLocal;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class CustomersService extends CustomersServiceGrpc.CustomersServiceImplBase {

    private final CustomerRepository customerRepository;


    @Override
    public void findAll(Empty request, StreamObserver<CustomerProto.Customers> responseObserver) {
        var customerDTOList=customerRepository.findAll();
        List<CustomerProto.Customer> customerList = CustomerAdapter.toProtoList(customerDTOList);
        CustomerProto.Customers c = CustomerProto.Customers.newBuilder().addAllCustomers(customerList).build();
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }

    @Override
    public void addCustomer(CustomerProto.Customer request, StreamObserver<CustomerProto.Customer> responseObserver) {
        var customerDTO=customerRepository.save(new Customer(null,request.getPesel(),request.getName()));
        CustomerProto.Customer c = CustomerAdapter.toProto(customerDTO);
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }
    @Override
    public void updateCustomer(CustomerProto.Customer request, StreamObserver<CustomerProto.Customer> responseObserver){
        Customer customerTemp = customerRepository.findByPesel(request.getPesel()).get();
        customerTemp.setName(request.getName());
        customerRepository.save(customerTemp);

        CustomerProto.Customer c = CustomerAdapter.toProto(customerTemp);
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }
    @Override
    public void deleteCustomer(StringValue request,  StreamObserver<CustomerProto.Customer> responseObserver){
        if(customerRepository.existsCustomerByPesel(request.getValue())){
            String pesel = request.getValue();
            Customer deletedCustomer = customerRepository.findByPesel(pesel).get();
            customerRepository.deleteByPesel(pesel);
            CustomerProto.Customer c = CustomerAdapter.toProto(deletedCustomer);
            responseObserver.onNext(c);
            responseObserver.onCompleted();
        }
    }
}
