package pl.piomin.services.grpc.pdf.model;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: pdf.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PdfServiceGrpc {

  private PdfServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "model.PdfService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pl.piomin.services.grpc.pdf.model.PdfProto.PdfData,
      pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse> getUploadPdfMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadPdf",
      requestType = pl.piomin.services.grpc.pdf.model.PdfProto.PdfData.class,
      responseType = pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pl.piomin.services.grpc.pdf.model.PdfProto.PdfData,
      pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse> getUploadPdfMethod() {
    io.grpc.MethodDescriptor<pl.piomin.services.grpc.pdf.model.PdfProto.PdfData, pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse> getUploadPdfMethod;
    if ((getUploadPdfMethod = PdfServiceGrpc.getUploadPdfMethod) == null) {
      synchronized (PdfServiceGrpc.class) {
        if ((getUploadPdfMethod = PdfServiceGrpc.getUploadPdfMethod) == null) {
          PdfServiceGrpc.getUploadPdfMethod = getUploadPdfMethod =
              io.grpc.MethodDescriptor.<pl.piomin.services.grpc.pdf.model.PdfProto.PdfData, pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadPdf"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.pdf.model.PdfProto.PdfData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PdfServiceMethodDescriptorSupplier("UploadPdf"))
              .build();
        }
      }
    }
    return getUploadPdfMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest,
      pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse> getDownloadPdfMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DownloadPdf",
      requestType = pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest.class,
      responseType = pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest,
      pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse> getDownloadPdfMethod() {
    io.grpc.MethodDescriptor<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest, pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse> getDownloadPdfMethod;
    if ((getDownloadPdfMethod = PdfServiceGrpc.getDownloadPdfMethod) == null) {
      synchronized (PdfServiceGrpc.class) {
        if ((getDownloadPdfMethod = PdfServiceGrpc.getDownloadPdfMethod) == null) {
          PdfServiceGrpc.getDownloadPdfMethod = getDownloadPdfMethod =
              io.grpc.MethodDescriptor.<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest, pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DownloadPdf"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PdfServiceMethodDescriptorSupplier("DownloadPdf"))
              .build();
        }
      }
    }
    return getDownloadPdfMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PdfServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PdfServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PdfServiceStub>() {
        @java.lang.Override
        public PdfServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PdfServiceStub(channel, callOptions);
        }
      };
    return PdfServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PdfServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PdfServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PdfServiceBlockingStub>() {
        @java.lang.Override
        public PdfServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PdfServiceBlockingStub(channel, callOptions);
        }
      };
    return PdfServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PdfServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PdfServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PdfServiceFutureStub>() {
        @java.lang.Override
        public PdfServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PdfServiceFutureStub(channel, callOptions);
        }
      };
    return PdfServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void uploadPdf(pl.piomin.services.grpc.pdf.model.PdfProto.PdfData request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadPdfMethod(), responseObserver);
    }

    /**
     */
    default void downloadPdf(pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDownloadPdfMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PdfService.
   */
  public static abstract class PdfServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PdfServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PdfService.
   */
  public static final class PdfServiceStub
      extends io.grpc.stub.AbstractAsyncStub<PdfServiceStub> {
    private PdfServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PdfServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PdfServiceStub(channel, callOptions);
    }

    /**
     */
    public void uploadPdf(pl.piomin.services.grpc.pdf.model.PdfProto.PdfData request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadPdfMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void downloadPdf(pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDownloadPdfMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PdfService.
   */
  public static final class PdfServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PdfServiceBlockingStub> {
    private PdfServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PdfServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PdfServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse uploadPdf(pl.piomin.services.grpc.pdf.model.PdfProto.PdfData request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadPdfMethod(), getCallOptions(), request);
    }

    /**
     */
    public pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse downloadPdf(pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDownloadPdfMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PdfService.
   */
  public static final class PdfServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<PdfServiceFutureStub> {
    private PdfServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PdfServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PdfServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse> uploadPdf(
        pl.piomin.services.grpc.pdf.model.PdfProto.PdfData request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadPdfMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse> downloadPdf(
        pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDownloadPdfMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD_PDF = 0;
  private static final int METHODID_DOWNLOAD_PDF = 1;

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
        case METHODID_UPLOAD_PDF:
          serviceImpl.uploadPdf((pl.piomin.services.grpc.pdf.model.PdfProto.PdfData) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse>) responseObserver);
          break;
        case METHODID_DOWNLOAD_PDF:
          serviceImpl.downloadPdf((pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse>) responseObserver);
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
          getUploadPdfMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              pl.piomin.services.grpc.pdf.model.PdfProto.PdfData,
              pl.piomin.services.grpc.pdf.model.PdfProto.UploadPdfResponse>(
                service, METHODID_UPLOAD_PDF)))
        .addMethod(
          getDownloadPdfMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfRequest,
              pl.piomin.services.grpc.pdf.model.PdfProto.DownloadPdfResponse>(
                service, METHODID_DOWNLOAD_PDF)))
        .build();
  }

  private static abstract class PdfServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PdfServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pl.piomin.services.grpc.pdf.model.PdfProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PdfService");
    }
  }

  private static final class PdfServiceFileDescriptorSupplier
      extends PdfServiceBaseDescriptorSupplier {
    PdfServiceFileDescriptorSupplier() {}
  }

  private static final class PdfServiceMethodDescriptorSupplier
      extends PdfServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PdfServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (PdfServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PdfServiceFileDescriptorSupplier())
              .addMethod(getUploadPdfMethod())
              .addMethod(getDownloadPdfMethod())
              .build();
        }
      }
    }
    return result;
  }
}
