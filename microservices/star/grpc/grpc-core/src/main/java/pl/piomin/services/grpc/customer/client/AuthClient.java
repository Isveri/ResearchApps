package pl.piomin.services.grpc.customer.client;

import com.google.protobuf.Empty;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.piomin.services.grpc.user.model.UserServiceGrpc;


@Service
public class AuthClient {
    private static final Logger LOG = LoggerFactory.getLogger(AuthClient.class);
    @GrpcClient("auth-service")
    UserServiceGrpc.UserServiceBlockingStub stub;

    public Boolean loginUser(){
        try {
            return stub.loginUser(Empty.newBuilder().build()).getValue();
        } catch (final StatusRuntimeException e) {
            LOG.error("Error in communication", e);
            return null;
        }
    }
}
