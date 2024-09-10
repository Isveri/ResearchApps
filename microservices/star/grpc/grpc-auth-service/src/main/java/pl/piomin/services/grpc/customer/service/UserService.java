package pl.piomin.services.grpc.customer.service;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.piomin.services.grpc.customer.repository.UserRepository;
import pl.piomin.services.grpc.user.model.UserServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    private final UserRepository repository;

    @Override
    public void loginUser(Empty request, StreamObserver<BoolValue> responseObserver) {
        var auth = repository.existsByLoginAndPassword("login", "password");
        BoolValue c = BoolValue.newBuilder().setValue(auth).build();
        responseObserver.onNext(c);
        responseObserver.onCompleted();
    }
}
