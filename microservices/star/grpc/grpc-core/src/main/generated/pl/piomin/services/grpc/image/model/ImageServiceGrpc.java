package pl.piomin.services.grpc.image.model;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: image.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ImageServiceGrpc {

  private ImageServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "model.ImageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<pl.piomin.services.grpc.image.model.ImageProto.ImageData,
      pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse> getUploadImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadImage",
      requestType = pl.piomin.services.grpc.image.model.ImageProto.ImageData.class,
      responseType = pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pl.piomin.services.grpc.image.model.ImageProto.ImageData,
      pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse> getUploadImageMethod() {
    io.grpc.MethodDescriptor<pl.piomin.services.grpc.image.model.ImageProto.ImageData, pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse> getUploadImageMethod;
    if ((getUploadImageMethod = ImageServiceGrpc.getUploadImageMethod) == null) {
      synchronized (ImageServiceGrpc.class) {
        if ((getUploadImageMethod = ImageServiceGrpc.getUploadImageMethod) == null) {
          ImageServiceGrpc.getUploadImageMethod = getUploadImageMethod =
              io.grpc.MethodDescriptor.<pl.piomin.services.grpc.image.model.ImageProto.ImageData, pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.image.model.ImageProto.ImageData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ImageServiceMethodDescriptorSupplier("UploadImage"))
              .build();
        }
      }
    }
    return getUploadImageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest,
      pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse> getDownloadImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DownloadImage",
      requestType = pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest.class,
      responseType = pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest,
      pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse> getDownloadImageMethod() {
    io.grpc.MethodDescriptor<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest, pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse> getDownloadImageMethod;
    if ((getDownloadImageMethod = ImageServiceGrpc.getDownloadImageMethod) == null) {
      synchronized (ImageServiceGrpc.class) {
        if ((getDownloadImageMethod = ImageServiceGrpc.getDownloadImageMethod) == null) {
          ImageServiceGrpc.getDownloadImageMethod = getDownloadImageMethod =
              io.grpc.MethodDescriptor.<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest, pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DownloadImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ImageServiceMethodDescriptorSupplier("DownloadImage"))
              .build();
        }
      }
    }
    return getDownloadImageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ImageServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageServiceStub>() {
        @java.lang.Override
        public ImageServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageServiceStub(channel, callOptions);
        }
      };
    return ImageServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ImageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageServiceBlockingStub>() {
        @java.lang.Override
        public ImageServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageServiceBlockingStub(channel, callOptions);
        }
      };
    return ImageServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ImageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ImageServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ImageServiceFutureStub>() {
        @java.lang.Override
        public ImageServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ImageServiceFutureStub(channel, callOptions);
        }
      };
    return ImageServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void uploadImage(pl.piomin.services.grpc.image.model.ImageProto.ImageData request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadImageMethod(), responseObserver);
    }

    /**
     */
    default void downloadImage(pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDownloadImageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ImageService.
   */
  public static abstract class ImageServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ImageServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ImageService.
   */
  public static final class ImageServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ImageServiceStub> {
    private ImageServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageServiceStub(channel, callOptions);
    }

    /**
     */
    public void uploadImage(pl.piomin.services.grpc.image.model.ImageProto.ImageData request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void downloadImage(pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest request,
        io.grpc.stub.StreamObserver<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDownloadImageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ImageService.
   */
  public static final class ImageServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ImageServiceBlockingStub> {
    private ImageServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse uploadImage(pl.piomin.services.grpc.image.model.ImageProto.ImageData request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadImageMethod(), getCallOptions(), request);
    }

    /**
     */
    public pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse downloadImage(pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDownloadImageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ImageService.
   */
  public static final class ImageServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ImageServiceFutureStub> {
    private ImageServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ImageServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ImageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse> uploadImage(
        pl.piomin.services.grpc.image.model.ImageProto.ImageData request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse> downloadImage(
        pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDownloadImageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD_IMAGE = 0;
  private static final int METHODID_DOWNLOAD_IMAGE = 1;

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
        case METHODID_UPLOAD_IMAGE:
          serviceImpl.uploadImage((pl.piomin.services.grpc.image.model.ImageProto.ImageData) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse>) responseObserver);
          break;
        case METHODID_DOWNLOAD_IMAGE:
          serviceImpl.downloadImage((pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest) request,
              (io.grpc.stub.StreamObserver<pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse>) responseObserver);
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
          getUploadImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              pl.piomin.services.grpc.image.model.ImageProto.ImageData,
              pl.piomin.services.grpc.image.model.ImageProto.UploadImageResponse>(
                service, METHODID_UPLOAD_IMAGE)))
        .addMethod(
          getDownloadImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              pl.piomin.services.grpc.image.model.ImageProto.DownloadImageRequest,
              pl.piomin.services.grpc.image.model.ImageProto.DownloadImageResponse>(
                service, METHODID_DOWNLOAD_IMAGE)))
        .build();
  }

  private static abstract class ImageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ImageServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return pl.piomin.services.grpc.image.model.ImageProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ImageService");
    }
  }

  private static final class ImageServiceFileDescriptorSupplier
      extends ImageServiceBaseDescriptorSupplier {
    ImageServiceFileDescriptorSupplier() {}
  }

  private static final class ImageServiceMethodDescriptorSupplier
      extends ImageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ImageServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ImageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ImageServiceFileDescriptorSupplier())
              .addMethod(getUploadImageMethod())
              .addMethod(getDownloadImageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
