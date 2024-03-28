package pl.piomin.services.grpc.customer.model;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: customer.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CustomersServiceGrpc {

  private CustomersServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "model.CustomersService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customers> getFindAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FindAll",
      requestType = com.google.protobuf.Empty.class,
      responseType = pl.piomin.services.grpc.customer.model.CustomerProto.Customers.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customers> getFindAllMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, pl.piomin.services.grpc.customer.model.CustomerProto.Customers> getFindAllMethod;
    if ((getFindAllMethod = CustomersServiceGrpc.getFindAllMethod) == null) {
      synchronized (CustomersServiceGrpc.class) {
        if ((getFindAllMethod = CustomersServiceGrpc.getFindAllMethod) == null) {
          CustomersServiceGrpc.getFindAllMethod = getFindAllMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, pl.piomin.services.grpc.customer.model.CustomerProto.Customers>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FindAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.customer.model.CustomerProto.Customers.getDefaultInstance()))
              .setSchemaDescriptor(new CustomersServiceMethodDescriptorSupplier("FindAll"))
              .build();
        }
      }
    }
    return getFindAllMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pl.piomin.services.grpc.customer.model.CustomerProto.Customer,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getAddCustomerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddCustomer",
      requestType = pl.piomin.services.grpc.customer.model.CustomerProto.Customer.class,
      responseType = pl.piomin.services.grpc.customer.model.CustomerProto.Customer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pl.piomin.services.grpc.customer.model.CustomerProto.Customer,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getAddCustomerMethod() {
    io.grpc.MethodDescriptor<pl.piomin.services.grpc.customer.model.CustomerProto.Customer, pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getAddCustomerMethod;
    if ((getAddCustomerMethod = CustomersServiceGrpc.getAddCustomerMethod) == null) {
      synchronized (CustomersServiceGrpc.class) {
        if ((getAddCustomerMethod = CustomersServiceGrpc.getAddCustomerMethod) == null) {
          CustomersServiceGrpc.getAddCustomerMethod = getAddCustomerMethod =
              io.grpc.MethodDescriptor.<pl.piomin.services.grpc.customer.model.CustomerProto.Customer, pl.piomin.services.grpc.customer.model.CustomerProto.Customer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddCustomer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.customer.model.CustomerProto.Customer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.customer.model.CustomerProto.Customer.getDefaultInstance()))
              .setSchemaDescriptor(new CustomersServiceMethodDescriptorSupplier("AddCustomer"))
              .build();
        }
      }
    }
    return getAddCustomerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pl.piomin.services.grpc.customer.model.CustomerProto.Customer,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getUpdateCustomerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateCustomer",
      requestType = pl.piomin.services.grpc.customer.model.CustomerProto.Customer.class,
      responseType = pl.piomin.services.grpc.customer.model.CustomerProto.Customer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pl.piomin.services.grpc.customer.model.CustomerProto.Customer,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getUpdateCustomerMethod() {
    io.grpc.MethodDescriptor<pl.piomin.services.grpc.customer.model.CustomerProto.Customer, pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getUpdateCustomerMethod;
    if ((getUpdateCustomerMethod = CustomersServiceGrpc.getUpdateCustomerMethod) == null) {
      synchronized (CustomersServiceGrpc.class) {
        if ((getUpdateCustomerMethod = CustomersServiceGrpc.getUpdateCustomerMethod) == null) {
          CustomersServiceGrpc.getUpdateCustomerMethod = getUpdateCustomerMethod =
              io.grpc.MethodDescriptor.<pl.piomin.services.grpc.customer.model.CustomerProto.Customer, pl.piomin.services.grpc.customer.model.CustomerProto.Customer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateCustomer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.customer.model.CustomerProto.Customer.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.customer.model.CustomerProto.Customer.getDefaultInstance()))
              .setSchemaDescriptor(new CustomersServiceMethodDescriptorSupplier("UpdateCustomer"))
              .build();
        }
      }
    }
    return getUpdateCustomerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getDeleteCustomerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteCustomer",
      requestType = com.google.protobuf.StringValue.class,
      responseType = pl.piomin.services.grpc.customer.model.CustomerProto.Customer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getDeleteCustomerMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.StringValue, pl.piomin.services.grpc.customer.model.CustomerProto.Customer> getDeleteCustomerMethod;
    if ((getDeleteCustomerMethod = CustomersServiceGrpc.getDeleteCustomerMethod) == null) {
      synchronized (CustomersServiceGrpc.class) {
        if ((getDeleteCustomerMethod = CustomersServiceGrpc.getDeleteCustomerMethod) == null) {
          CustomersServiceGrpc.getDeleteCustomerMethod = getDeleteCustomerMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.StringValue, pl.piomin.services.grpc.customer.model.CustomerProto.Customer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteCustomer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.customer.model.CustomerProto.Customer.getDefaultInstance()))
              .setSchemaDescriptor(new CustomersServiceMethodDescriptorSupplier("DeleteCustomer"))
              .build();
        }
      }
    }
    return getDeleteCustomerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CustomersServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomersServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomersServiceStub>() {
        @java.lang.Override
        public CustomersServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomersServiceStub(channel, callOptions);
        }
      };
    return CustomersServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CustomersServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomersServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomersServiceBlockingStub>() {
        @java.lang.Override
        public CustomersServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomersServiceBlockingStub(channel, callOptions);
        }
      };
    return CustomersServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CustomersServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomersServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomersServiceFutureStub>() {
        @java.lang.Override
        public CustomersServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomersServiceFutureStub(channel, callOptions);
        }
      };
    return CustomersServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void findAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customers> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFindAllMethod(), responseObserver);
    }

    /**
     */
    default void addCustomer(pl.piomin.services.grpc.customer.model.CustomerProto.Customer request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddCustomerMethod(), responseObserver);
    }

    /**
     */
    default void updateCustomer(pl.piomin.services.grpc.customer.model.CustomerProto.Customer request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateCustomerMethod(), responseObserver);
    }

    /**
     */
    default void deleteCustomer(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteCustomerMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CustomersService.
   */
  public static abstract class CustomersServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CustomersServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CustomersService.
   */
  public static final class CustomersServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CustomersServiceStub> {
    private CustomersServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CustomersServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomersServiceStub(channel, callOptions);
    }

    /**
     */
    public void findAll(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customers> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFindAllMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addCustomer(pl.piomin.services.grpc.customer.model.CustomerProto.Customer request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddCustomerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateCustomer(pl.piomin.services.grpc.customer.model.CustomerProto.Customer request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateCustomerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteCustomer(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteCustomerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CustomersService.
   */
  public static final class CustomersServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CustomersServiceBlockingStub> {
    private CustomersServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CustomersServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomersServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pl.piomin.services.grpc.customer.model.CustomerProto.Customers findAll(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFindAllMethod(), getCallOptions(), request);
    }

    /**
     */
    public pl.piomin.services.grpc.customer.model.CustomerProto.Customer addCustomer(pl.piomin.services.grpc.customer.model.CustomerProto.Customer request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddCustomerMethod(), getCallOptions(), request);
    }

    /**
     */
    public pl.piomin.services.grpc.customer.model.CustomerProto.Customer updateCustomer(pl.piomin.services.grpc.customer.model.CustomerProto.Customer request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateCustomerMethod(), getCallOptions(), request);
    }

    /**
     */
    public pl.piomin.services.grpc.customer.model.CustomerProto.Customer deleteCustomer(com.google.protobuf.StringValue request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteCustomerMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CustomersService.
   */
  public static final class CustomersServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CustomersServiceFutureStub> {
    private CustomersServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CustomersServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomersServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.customer.model.CustomerProto.Customers> findAll(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFindAllMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> addCustomer(
        pl.piomin.services.grpc.customer.model.CustomerProto.Customer request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddCustomerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> updateCustomer(
        pl.piomin.services.grpc.customer.model.CustomerProto.Customer request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateCustomerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.customer.model.CustomerProto.Customer> deleteCustomer(
        com.google.protobuf.StringValue request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteCustomerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIND_ALL = 0;
  private static final int METHODID_ADD_CUSTOMER = 1;
  private static final int METHODID_UPDATE_CUSTOMER = 2;
  private static final int METHODID_DELETE_CUSTOMER = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIND_ALL:
          serviceImpl.findAll((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customers>) responseObserver);
          break;
        case METHODID_ADD_CUSTOMER:
          serviceImpl.addCustomer((pl.piomin.services.grpc.customer.model.CustomerProto.Customer) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer>) responseObserver);
          break;
        case METHODID_UPDATE_CUSTOMER:
          serviceImpl.updateCustomer((pl.piomin.services.grpc.customer.model.CustomerProto.Customer) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer>) responseObserver);
          break;
        case METHODID_DELETE_CUSTOMER:
          serviceImpl.deleteCustomer((com.google.protobuf.StringValue) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.customer.model.CustomerProto.Customer>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getFindAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              pl.piomin.services.grpc.customer.model.CustomerProto.Customers>(
                service, METHODID_FIND_ALL)))
        .addMethod(
          getAddCustomerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              pl.piomin.services.grpc.customer.model.CustomerProto.Customer,
              pl.piomin.services.grpc.customer.model.CustomerProto.Customer>(
                service, METHODID_ADD_CUSTOMER)))
        .addMethod(
          getUpdateCustomerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              pl.piomin.services.grpc.customer.model.CustomerProto.Customer,
              pl.piomin.services.grpc.customer.model.CustomerProto.Customer>(
                service, METHODID_UPDATE_CUSTOMER)))
        .addMethod(
          getDeleteCustomerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.StringValue,
              pl.piomin.services.grpc.customer.model.CustomerProto.Customer>(
                service, METHODID_DELETE_CUSTOMER)))
        .build();
  }

  private static abstract class CustomersServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CustomersServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pl.piomin.services.grpc.customer.model.CustomerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CustomersService");
    }
  }

  private static final class CustomersServiceFileDescriptorSupplier
      extends CustomersServiceBaseDescriptorSupplier {
    CustomersServiceFileDescriptorSupplier() {}
  }

  private static final class CustomersServiceMethodDescriptorSupplier
      extends CustomersServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CustomersServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CustomersServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CustomersServiceFileDescriptorSupplier())
              .addMethod(getFindAllMethod())
              .addMethod(getAddCustomerMethod())
              .addMethod(getUpdateCustomerMethod())
              .addMethod(getDeleteCustomerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
