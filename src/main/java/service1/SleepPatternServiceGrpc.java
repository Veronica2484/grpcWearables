package service1;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *Name of the service + keyword Service. This service will send to the user her/his pattern of sleep during a night or numbers of night
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service1.proto")
public final class SleepPatternServiceGrpc {

  private SleepPatternServiceGrpc() {}

  public static final String SERVICE_NAME = "service1.SleepPatternService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<service1.SleepPatternRequest,
      service1.SleepPatternResponse> getSleepPatternMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SleepPattern",
      requestType = service1.SleepPatternRequest.class,
      responseType = service1.SleepPatternResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<service1.SleepPatternRequest,
      service1.SleepPatternResponse> getSleepPatternMethod() {
    io.grpc.MethodDescriptor<service1.SleepPatternRequest, service1.SleepPatternResponse> getSleepPatternMethod;
    if ((getSleepPatternMethod = SleepPatternServiceGrpc.getSleepPatternMethod) == null) {
      synchronized (SleepPatternServiceGrpc.class) {
        if ((getSleepPatternMethod = SleepPatternServiceGrpc.getSleepPatternMethod) == null) {
          SleepPatternServiceGrpc.getSleepPatternMethod = getSleepPatternMethod = 
              io.grpc.MethodDescriptor.<service1.SleepPatternRequest, service1.SleepPatternResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "service1.SleepPatternService", "SleepPattern"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service1.SleepPatternRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service1.SleepPatternResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SleepPatternServiceMethodDescriptorSupplier("SleepPattern"))
                  .build();
          }
        }
     }
     return getSleepPatternMethod;
  }

  private static volatile io.grpc.MethodDescriptor<service1.WeeklySleepPatternRequest,
      service1.WeeklySleepPatternResponse> getWeeklySleepPatternMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WeeklySleepPattern",
      requestType = service1.WeeklySleepPatternRequest.class,
      responseType = service1.WeeklySleepPatternResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<service1.WeeklySleepPatternRequest,
      service1.WeeklySleepPatternResponse> getWeeklySleepPatternMethod() {
    io.grpc.MethodDescriptor<service1.WeeklySleepPatternRequest, service1.WeeklySleepPatternResponse> getWeeklySleepPatternMethod;
    if ((getWeeklySleepPatternMethod = SleepPatternServiceGrpc.getWeeklySleepPatternMethod) == null) {
      synchronized (SleepPatternServiceGrpc.class) {
        if ((getWeeklySleepPatternMethod = SleepPatternServiceGrpc.getWeeklySleepPatternMethod) == null) {
          SleepPatternServiceGrpc.getWeeklySleepPatternMethod = getWeeklySleepPatternMethod = 
              io.grpc.MethodDescriptor.<service1.WeeklySleepPatternRequest, service1.WeeklySleepPatternResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service1.SleepPatternService", "WeeklySleepPattern"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service1.WeeklySleepPatternRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service1.WeeklySleepPatternResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SleepPatternServiceMethodDescriptorSupplier("WeeklySleepPattern"))
                  .build();
          }
        }
     }
     return getWeeklySleepPatternMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SleepPatternServiceStub newStub(io.grpc.Channel channel) {
    return new SleepPatternServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SleepPatternServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SleepPatternServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SleepPatternServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SleepPatternServiceFutureStub(channel);
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will send to the user her/his pattern of sleep during a night or numbers of night
   * </pre>
   */
  public static abstract class SleepPatternServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *Unary. 
     * </pre>
     */
    public void sleepPattern(service1.SleepPatternRequest request,
        io.grpc.stub.StreamObserver<service1.SleepPatternResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSleepPatternMethod(), responseObserver);
    }

    /**
     * <pre>
     *Server Streaming
     * </pre>
     */
    public void weeklySleepPattern(service1.WeeklySleepPatternRequest request,
        io.grpc.stub.StreamObserver<service1.WeeklySleepPatternResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getWeeklySleepPatternMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSleepPatternMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                service1.SleepPatternRequest,
                service1.SleepPatternResponse>(
                  this, METHODID_SLEEP_PATTERN)))
          .addMethod(
            getWeeklySleepPatternMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                service1.WeeklySleepPatternRequest,
                service1.WeeklySleepPatternResponse>(
                  this, METHODID_WEEKLY_SLEEP_PATTERN)))
          .build();
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will send to the user her/his pattern of sleep during a night or numbers of night
   * </pre>
   */
  public static final class SleepPatternServiceStub extends io.grpc.stub.AbstractStub<SleepPatternServiceStub> {
    private SleepPatternServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SleepPatternServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SleepPatternServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SleepPatternServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary. 
     * </pre>
     */
    public void sleepPattern(service1.SleepPatternRequest request,
        io.grpc.stub.StreamObserver<service1.SleepPatternResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSleepPatternMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Server Streaming
     * </pre>
     */
    public void weeklySleepPattern(service1.WeeklySleepPatternRequest request,
        io.grpc.stub.StreamObserver<service1.WeeklySleepPatternResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getWeeklySleepPatternMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will send to the user her/his pattern of sleep during a night or numbers of night
   * </pre>
   */
  public static final class SleepPatternServiceBlockingStub extends io.grpc.stub.AbstractStub<SleepPatternServiceBlockingStub> {
    private SleepPatternServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SleepPatternServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SleepPatternServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SleepPatternServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary. 
     * </pre>
     */
    public service1.SleepPatternResponse sleepPattern(service1.SleepPatternRequest request) {
      return blockingUnaryCall(
          getChannel(), getSleepPatternMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Server Streaming
     * </pre>
     */
    public java.util.Iterator<service1.WeeklySleepPatternResponse> weeklySleepPattern(
        service1.WeeklySleepPatternRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getWeeklySleepPatternMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will send to the user her/his pattern of sleep during a night or numbers of night
   * </pre>
   */
  public static final class SleepPatternServiceFutureStub extends io.grpc.stub.AbstractStub<SleepPatternServiceFutureStub> {
    private SleepPatternServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SleepPatternServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SleepPatternServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SleepPatternServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *Unary. 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<service1.SleepPatternResponse> sleepPattern(
        service1.SleepPatternRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSleepPatternMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SLEEP_PATTERN = 0;
  private static final int METHODID_WEEKLY_SLEEP_PATTERN = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SleepPatternServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SleepPatternServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SLEEP_PATTERN:
          serviceImpl.sleepPattern((service1.SleepPatternRequest) request,
              (io.grpc.stub.StreamObserver<service1.SleepPatternResponse>) responseObserver);
          break;
        case METHODID_WEEKLY_SLEEP_PATTERN:
          serviceImpl.weeklySleepPattern((service1.WeeklySleepPatternRequest) request,
              (io.grpc.stub.StreamObserver<service1.WeeklySleepPatternResponse>) responseObserver);
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

  private static abstract class SleepPatternServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SleepPatternServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service1.SleepPatternServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SleepPatternService");
    }
  }

  private static final class SleepPatternServiceFileDescriptorSupplier
      extends SleepPatternServiceBaseDescriptorSupplier {
    SleepPatternServiceFileDescriptorSupplier() {}
  }

  private static final class SleepPatternServiceMethodDescriptorSupplier
      extends SleepPatternServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SleepPatternServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SleepPatternServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SleepPatternServiceFileDescriptorSupplier())
              .addMethod(getSleepPatternMethod())
              .addMethod(getWeeklySleepPatternMethod())
              .build();
        }
      }
    }
    return result;
  }
}
