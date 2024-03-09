//package pl.piomin.services.rest.customer.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import pl.piomin.services.rest.customer.repository.CustomerRepository;
//
//import java.util.List;
//
//
//public class CustomersService {
//
//    @Autowired
//    CustomerRepository repository;
//
//    public void findById(Int32Value request, StreamObserver<CustomerProto.Customer> responseObserver) {
//        CustomerProto.Customer c = repository.findById(request.getValue());
//        CustomerProto.Accounts a = accountClient.getAccountsByCustomerId(c.getId());
//        List<CustomerProto.Account> l = a.getAccountList();
//        c = CustomerProto.Customer.newBuilder(c).addAllAccounts(l).build();
//        responseObserver.onNext(c);
//        responseObserver.onCompleted();
//    }
//
//    public void findByPesel(StringValue request, StreamObserver<CustomerProto.Customer> responseObserver) {
//        CustomerProto.Customer c = repository.findByPesel(request.getValue());
//        responseObserver.onNext(c);
//        responseObserver.onCompleted();
//    }
//
//    public void findAll(Empty request, StreamObserver<CustomerProto.Customers> responseObserver) {
//        List<CustomerProto.Customer> customerList = repository.findAll();
//        CustomerProto.Customers c = CustomerProto.Customers.newBuilder().addAllCustomers(customerList).build();
//        responseObserver.onNext(c);
//        responseObserver.onCompleted();
//    }
//
//    public void addCustomer(CustomerProto.Customer request, StreamObserver<CustomerProto.Customer> responseObserver) {
//        CustomerProto.Customer c = repository.add(request.getType(), request.getName(), request.getPesel());
//        responseObserver.onNext(c);
//        responseObserver.onCompleted();
//    }
//}
