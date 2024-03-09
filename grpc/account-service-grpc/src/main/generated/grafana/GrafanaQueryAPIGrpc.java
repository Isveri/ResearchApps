package grafana;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The GrafanaQueryAPI definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.57.2)",
    comments = "Source: api.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GrafanaQueryAPIGrpc {

  private GrafanaQueryAPIGrpc() {}

  public static final java.lang.String SERVICE_NAME = "grafana.GrafanaQueryAPI";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grafana.Api.ListDimensionKeysRequest,
      grafana.Api.ListDimensionKeysResponse> getListDimensionKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListDimensionKeys",
      requestType = grafana.Api.ListDimensionKeysRequest.class,
      responseType = grafana.Api.ListDimensionKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grafana.Api.ListDimensionKeysRequest,
      grafana.Api.ListDimensionKeysResponse> getListDimensionKeysMethod() {
    io.grpc.MethodDescriptor<grafana.Api.ListDimensionKeysRequest, grafana.Api.ListDimensionKeysResponse> getListDimensionKeysMethod;
    if ((getListDimensionKeysMethod = GrafanaQueryAPIGrpc.getListDimensionKeysMethod) == null) {
      synchronized (GrafanaQueryAPIGrpc.class) {
        if ((getListDimensionKeysMethod = GrafanaQueryAPIGrpc.getListDimensionKeysMethod) == null) {
          GrafanaQueryAPIGrpc.getListDimensionKeysMethod = getListDimensionKeysMethod =
              io.grpc.MethodDescriptor.<grafana.Api.ListDimensionKeysRequest, grafana.Api.ListDimensionKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListDimensionKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.ListDimensionKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.ListDimensionKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrafanaQueryAPIMethodDescriptorSupplier("ListDimensionKeys"))
              .build();
        }
      }
    }
    return getListDimensionKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grafana.Api.ListDimensionValuesRequest,
      grafana.Api.ListDimensionValuesResponse> getListDimensionValuesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListDimensionValues",
      requestType = grafana.Api.ListDimensionValuesRequest.class,
      responseType = grafana.Api.ListDimensionValuesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grafana.Api.ListDimensionValuesRequest,
      grafana.Api.ListDimensionValuesResponse> getListDimensionValuesMethod() {
    io.grpc.MethodDescriptor<grafana.Api.ListDimensionValuesRequest, grafana.Api.ListDimensionValuesResponse> getListDimensionValuesMethod;
    if ((getListDimensionValuesMethod = GrafanaQueryAPIGrpc.getListDimensionValuesMethod) == null) {
      synchronized (GrafanaQueryAPIGrpc.class) {
        if ((getListDimensionValuesMethod = GrafanaQueryAPIGrpc.getListDimensionValuesMethod) == null) {
          GrafanaQueryAPIGrpc.getListDimensionValuesMethod = getListDimensionValuesMethod =
              io.grpc.MethodDescriptor.<grafana.Api.ListDimensionValuesRequest, grafana.Api.ListDimensionValuesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListDimensionValues"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.ListDimensionValuesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.ListDimensionValuesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrafanaQueryAPIMethodDescriptorSupplier("ListDimensionValues"))
              .build();
        }
      }
    }
    return getListDimensionValuesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grafana.Api.ListMetricsRequest,
      grafana.Api.ListMetricsResponse> getListMetricsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListMetrics",
      requestType = grafana.Api.ListMetricsRequest.class,
      responseType = grafana.Api.ListMetricsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grafana.Api.ListMetricsRequest,
      grafana.Api.ListMetricsResponse> getListMetricsMethod() {
    io.grpc.MethodDescriptor<grafana.Api.ListMetricsRequest, grafana.Api.ListMetricsResponse> getListMetricsMethod;
    if ((getListMetricsMethod = GrafanaQueryAPIGrpc.getListMetricsMethod) == null) {
      synchronized (GrafanaQueryAPIGrpc.class) {
        if ((getListMetricsMethod = GrafanaQueryAPIGrpc.getListMetricsMethod) == null) {
          GrafanaQueryAPIGrpc.getListMetricsMethod = getListMetricsMethod =
              io.grpc.MethodDescriptor.<grafana.Api.ListMetricsRequest, grafana.Api.ListMetricsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMetrics"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.ListMetricsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.ListMetricsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrafanaQueryAPIMethodDescriptorSupplier("ListMetrics"))
              .build();
        }
      }
    }
    return getListMetricsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grafana.Api.GetMetricValueRequest,
      grafana.Api.GetMetricValueResponse> getGetMetricValueMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMetricValue",
      requestType = grafana.Api.GetMetricValueRequest.class,
      responseType = grafana.Api.GetMetricValueResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grafana.Api.GetMetricValueRequest,
      grafana.Api.GetMetricValueResponse> getGetMetricValueMethod() {
    io.grpc.MethodDescriptor<grafana.Api.GetMetricValueRequest, grafana.Api.GetMetricValueResponse> getGetMetricValueMethod;
    if ((getGetMetricValueMethod = GrafanaQueryAPIGrpc.getGetMetricValueMethod) == null) {
      synchronized (GrafanaQueryAPIGrpc.class) {
        if ((getGetMetricValueMethod = GrafanaQueryAPIGrpc.getGetMetricValueMethod) == null) {
          GrafanaQueryAPIGrpc.getGetMetricValueMethod = getGetMetricValueMethod =
              io.grpc.MethodDescriptor.<grafana.Api.GetMetricValueRequest, grafana.Api.GetMetricValueResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMetricValue"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.GetMetricValueRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.GetMetricValueResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrafanaQueryAPIMethodDescriptorSupplier("GetMetricValue"))
              .build();
        }
      }
    }
    return getGetMetricValueMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grafana.Api.GetMetricHistoryRequest,
      grafana.Api.GetMetricHistoryResponse> getGetMetricHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMetricHistory",
      requestType = grafana.Api.GetMetricHistoryRequest.class,
      responseType = grafana.Api.GetMetricHistoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grafana.Api.GetMetricHistoryRequest,
      grafana.Api.GetMetricHistoryResponse> getGetMetricHistoryMethod() {
    io.grpc.MethodDescriptor<grafana.Api.GetMetricHistoryRequest, grafana.Api.GetMetricHistoryResponse> getGetMetricHistoryMethod;
    if ((getGetMetricHistoryMethod = GrafanaQueryAPIGrpc.getGetMetricHistoryMethod) == null) {
      synchronized (GrafanaQueryAPIGrpc.class) {
        if ((getGetMetricHistoryMethod = GrafanaQueryAPIGrpc.getGetMetricHistoryMethod) == null) {
          GrafanaQueryAPIGrpc.getGetMetricHistoryMethod = getGetMetricHistoryMethod =
              io.grpc.MethodDescriptor.<grafana.Api.GetMetricHistoryRequest, grafana.Api.GetMetricHistoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMetricHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.GetMetricHistoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.GetMetricHistoryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrafanaQueryAPIMethodDescriptorSupplier("GetMetricHistory"))
              .build();
        }
      }
    }
    return getGetMetricHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grafana.Api.GetMetricAggregateRequest,
      grafana.Api.GetMetricAggregateResponse> getGetMetricAggregateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMetricAggregate",
      requestType = grafana.Api.GetMetricAggregateRequest.class,
      responseType = grafana.Api.GetMetricAggregateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grafana.Api.GetMetricAggregateRequest,
      grafana.Api.GetMetricAggregateResponse> getGetMetricAggregateMethod() {
    io.grpc.MethodDescriptor<grafana.Api.GetMetricAggregateRequest, grafana.Api.GetMetricAggregateResponse> getGetMetricAggregateMethod;
    if ((getGetMetricAggregateMethod = GrafanaQueryAPIGrpc.getGetMetricAggregateMethod) == null) {
      synchronized (GrafanaQueryAPIGrpc.class) {
        if ((getGetMetricAggregateMethod = GrafanaQueryAPIGrpc.getGetMetricAggregateMethod) == null) {
          GrafanaQueryAPIGrpc.getGetMetricAggregateMethod = getGetMetricAggregateMethod =
              io.grpc.MethodDescriptor.<grafana.Api.GetMetricAggregateRequest, grafana.Api.GetMetricAggregateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMetricAggregate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.GetMetricAggregateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grafana.Api.GetMetricAggregateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrafanaQueryAPIMethodDescriptorSupplier("GetMetricAggregate"))
              .build();
        }
      }
    }
    return getGetMetricAggregateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrafanaQueryAPIStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrafanaQueryAPIStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrafanaQueryAPIStub>() {
        @java.lang.Override
        public GrafanaQueryAPIStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrafanaQueryAPIStub(channel, callOptions);
        }
      };
    return GrafanaQueryAPIStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrafanaQueryAPIBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrafanaQueryAPIBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrafanaQueryAPIBlockingStub>() {
        @java.lang.Override
        public GrafanaQueryAPIBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrafanaQueryAPIBlockingStub(channel, callOptions);
        }
      };
    return GrafanaQueryAPIBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrafanaQueryAPIFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrafanaQueryAPIFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrafanaQueryAPIFutureStub>() {
        @java.lang.Override
        public GrafanaQueryAPIFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrafanaQueryAPIFutureStub(channel, callOptions);
        }
      };
    return GrafanaQueryAPIFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The GrafanaQueryAPI definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Returns a list of all available dimensions
     * </pre>
     */
    default void listDimensionKeys(grafana.Api.ListDimensionKeysRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.ListDimensionKeysResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListDimensionKeysMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns a list of all dimension values for a certain dimension
     * </pre>
     */
    default void listDimensionValues(grafana.Api.ListDimensionValuesRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.ListDimensionValuesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListDimensionValuesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Returns all metrics from the system
     * </pre>
     */
    default void listMetrics(grafana.Api.ListMetricsRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.ListMetricsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMetricsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Gets a metric's current value
     * </pre>
     */
    default void getMetricValue(grafana.Api.GetMetricValueRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.GetMetricValueResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMetricValueMethod(), responseObserver);
    }

    /**
     * <pre>
     * Gets the history of a metric's values
     * </pre>
     */
    default void getMetricHistory(grafana.Api.GetMetricHistoryRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.GetMetricHistoryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMetricHistoryMethod(), responseObserver);
    }

    /**
     * <pre>
     * Gets the history of a metric's aggregated value
     * </pre>
     */
    default void getMetricAggregate(grafana.Api.GetMetricAggregateRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.GetMetricAggregateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMetricAggregateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GrafanaQueryAPI.
   * <pre>
   * The GrafanaQueryAPI definition.
   * </pre>
   */
  public static abstract class GrafanaQueryAPIImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GrafanaQueryAPIGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GrafanaQueryAPI.
   * <pre>
   * The GrafanaQueryAPI definition.
   * </pre>
   */
  public static final class GrafanaQueryAPIStub
      extends io.grpc.stub.AbstractAsyncStub<GrafanaQueryAPIStub> {
    private GrafanaQueryAPIStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrafanaQueryAPIStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrafanaQueryAPIStub(channel, callOptions);
    }

    /**
     * <pre>
     * Returns a list of all available dimensions
     * </pre>
     */
    public void listDimensionKeys(grafana.Api.ListDimensionKeysRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.ListDimensionKeysResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListDimensionKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns a list of all dimension values for a certain dimension
     * </pre>
     */
    public void listDimensionValues(grafana.Api.ListDimensionValuesRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.ListDimensionValuesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListDimensionValuesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Returns all metrics from the system
     * </pre>
     */
    public void listMetrics(grafana.Api.ListMetricsRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.ListMetricsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMetricsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Gets a metric's current value
     * </pre>
     */
    public void getMetricValue(grafana.Api.GetMetricValueRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.GetMetricValueResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMetricValueMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Gets the history of a metric's values
     * </pre>
     */
    public void getMetricHistory(grafana.Api.GetMetricHistoryRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.GetMetricHistoryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMetricHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Gets the history of a metric's aggregated value
     * </pre>
     */
    public void getMetricAggregate(grafana.Api.GetMetricAggregateRequest request,
        io.grpc.stub.StreamObserver<grafana.Api.GetMetricAggregateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMetricAggregateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GrafanaQueryAPI.
   * <pre>
   * The GrafanaQueryAPI definition.
   * </pre>
   */
  public static final class GrafanaQueryAPIBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GrafanaQueryAPIBlockingStub> {
    private GrafanaQueryAPIBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrafanaQueryAPIBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrafanaQueryAPIBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Returns a list of all available dimensions
     * </pre>
     */
    public grafana.Api.ListDimensionKeysResponse listDimensionKeys(grafana.Api.ListDimensionKeysRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListDimensionKeysMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns a list of all dimension values for a certain dimension
     * </pre>
     */
    public grafana.Api.ListDimensionValuesResponse listDimensionValues(grafana.Api.ListDimensionValuesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListDimensionValuesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Returns all metrics from the system
     * </pre>
     */
    public grafana.Api.ListMetricsResponse listMetrics(grafana.Api.ListMetricsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMetricsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Gets a metric's current value
     * </pre>
     */
    public grafana.Api.GetMetricValueResponse getMetricValue(grafana.Api.GetMetricValueRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMetricValueMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Gets the history of a metric's values
     * </pre>
     */
    public grafana.Api.GetMetricHistoryResponse getMetricHistory(grafana.Api.GetMetricHistoryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMetricHistoryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Gets the history of a metric's aggregated value
     * </pre>
     */
    public grafana.Api.GetMetricAggregateResponse getMetricAggregate(grafana.Api.GetMetricAggregateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMetricAggregateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GrafanaQueryAPI.
   * <pre>
   * The GrafanaQueryAPI definition.
   * </pre>
   */
  public static final class GrafanaQueryAPIFutureStub
      extends io.grpc.stub.AbstractFutureStub<GrafanaQueryAPIFutureStub> {
    private GrafanaQueryAPIFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrafanaQueryAPIFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrafanaQueryAPIFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Returns a list of all available dimensions
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grafana.Api.ListDimensionKeysResponse> listDimensionKeys(
        grafana.Api.ListDimensionKeysRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListDimensionKeysMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns a list of all dimension values for a certain dimension
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grafana.Api.ListDimensionValuesResponse> listDimensionValues(
        grafana.Api.ListDimensionValuesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListDimensionValuesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Returns all metrics from the system
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grafana.Api.ListMetricsResponse> listMetrics(
        grafana.Api.ListMetricsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMetricsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Gets a metric's current value
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grafana.Api.GetMetricValueResponse> getMetricValue(
        grafana.Api.GetMetricValueRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMetricValueMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Gets the history of a metric's values
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grafana.Api.GetMetricHistoryResponse> getMetricHistory(
        grafana.Api.GetMetricHistoryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMetricHistoryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Gets the history of a metric's aggregated value
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<grafana.Api.GetMetricAggregateResponse> getMetricAggregate(
        grafana.Api.GetMetricAggregateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMetricAggregateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_DIMENSION_KEYS = 0;
  private static final int METHODID_LIST_DIMENSION_VALUES = 1;
  private static final int METHODID_LIST_METRICS = 2;
  private static final int METHODID_GET_METRIC_VALUE = 3;
  private static final int METHODID_GET_METRIC_HISTORY = 4;
  private static final int METHODID_GET_METRIC_AGGREGATE = 5;

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
        case METHODID_LIST_DIMENSION_KEYS:
          serviceImpl.listDimensionKeys((grafana.Api.ListDimensionKeysRequest) request,
              (io.grpc.stub.StreamObserver<grafana.Api.ListDimensionKeysResponse>) responseObserver);
          break;
        case METHODID_LIST_DIMENSION_VALUES:
          serviceImpl.listDimensionValues((grafana.Api.ListDimensionValuesRequest) request,
              (io.grpc.stub.StreamObserver<grafana.Api.ListDimensionValuesResponse>) responseObserver);
          break;
        case METHODID_LIST_METRICS:
          serviceImpl.listMetrics((grafana.Api.ListMetricsRequest) request,
              (io.grpc.stub.StreamObserver<grafana.Api.ListMetricsResponse>) responseObserver);
          break;
        case METHODID_GET_METRIC_VALUE:
          serviceImpl.getMetricValue((grafana.Api.GetMetricValueRequest) request,
              (io.grpc.stub.StreamObserver<grafana.Api.GetMetricValueResponse>) responseObserver);
          break;
        case METHODID_GET_METRIC_HISTORY:
          serviceImpl.getMetricHistory((grafana.Api.GetMetricHistoryRequest) request,
              (io.grpc.stub.StreamObserver<grafana.Api.GetMetricHistoryResponse>) responseObserver);
          break;
        case METHODID_GET_METRIC_AGGREGATE:
          serviceImpl.getMetricAggregate((grafana.Api.GetMetricAggregateRequest) request,
              (io.grpc.stub.StreamObserver<grafana.Api.GetMetricAggregateResponse>) responseObserver);
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
          getListDimensionKeysMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grafana.Api.ListDimensionKeysRequest,
              grafana.Api.ListDimensionKeysResponse>(
                service, METHODID_LIST_DIMENSION_KEYS)))
        .addMethod(
          getListDimensionValuesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grafana.Api.ListDimensionValuesRequest,
              grafana.Api.ListDimensionValuesResponse>(
                service, METHODID_LIST_DIMENSION_VALUES)))
        .addMethod(
          getListMetricsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grafana.Api.ListMetricsRequest,
              grafana.Api.ListMetricsResponse>(
                service, METHODID_LIST_METRICS)))
        .addMethod(
          getGetMetricValueMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grafana.Api.GetMetricValueRequest,
              grafana.Api.GetMetricValueResponse>(
                service, METHODID_GET_METRIC_VALUE)))
        .addMethod(
          getGetMetricHistoryMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grafana.Api.GetMetricHistoryRequest,
              grafana.Api.GetMetricHistoryResponse>(
                service, METHODID_GET_METRIC_HISTORY)))
        .addMethod(
          getGetMetricAggregateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              grafana.Api.GetMetricAggregateRequest,
              grafana.Api.GetMetricAggregateResponse>(
                service, METHODID_GET_METRIC_AGGREGATE)))
        .build();
  }

  private static abstract class GrafanaQueryAPIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrafanaQueryAPIBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grafana.Api.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrafanaQueryAPI");
    }
  }

  private static final class GrafanaQueryAPIFileDescriptorSupplier
      extends GrafanaQueryAPIBaseDescriptorSupplier {
    GrafanaQueryAPIFileDescriptorSupplier() {}
  }

  private static final class GrafanaQueryAPIMethodDescriptorSupplier
      extends GrafanaQueryAPIBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GrafanaQueryAPIMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (GrafanaQueryAPIGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrafanaQueryAPIFileDescriptorSupplier())
              .addMethod(getListDimensionKeysMethod())
              .addMethod(getListDimensionValuesMethod())
              .addMethod(getListMetricsMethod())
              .addMethod(getGetMetricValueMethod())
              .addMethod(getGetMetricHistoryMethod())
              .addMethod(getGetMetricAggregateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
