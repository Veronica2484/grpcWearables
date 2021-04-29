package service2;

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
 *Name of the service + keyword Service. This service will track all the level of noisy along the night that are sent by the client 
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service2.proto")
public final class NoiseMonitorServiceGrpc {

  private NoiseMonitorServiceGrpc() {}

  public static final String SERVICE_NAME = "service2.NoiseMonitorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<service2.NoiseMonitorRequest,
      service2.NoiseMonitorResponse> getNoiseMonitorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "NoiseMonitor",
      requestType = service2.NoiseMonitorRequest.class,
      responseType = service2.NoiseMonitorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<service2.NoiseMonitorRequest,
      service2.NoiseMonitorResponse> getNoiseMonitorMethod() {
    io.grpc.MethodDescriptor<service2.NoiseMonitorRequest, service2.NoiseMonitorResponse> getNoiseMonitorMethod;
    if ((getNoiseMonitorMethod = NoiseMonitorServiceGrpc.getNoiseMonitorMethod) == null) {
      synchronized (NoiseMonitorServiceGrpc.class) {
        if ((getNoiseMonitorMethod = NoiseMonitorServiceGrpc.getNoiseMonitorMethod) == null) {
          NoiseMonitorServiceGrpc.getNoiseMonitorMethod = getNoiseMonitorMethod = 
              io.grpc.MethodDescriptor.<service2.NoiseMonitorRequest, service2.NoiseMonitorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service2.NoiseMonitorService", "NoiseMonitor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2.NoiseMonitorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service2.NoiseMonitorResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new NoiseMonitorServiceMethodDescriptorSupplier("NoiseMonitor"))
                  .build();
          }
        }
     }
     return getNoiseMonitorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NoiseMonitorServiceStub newStub(io.grpc.Channel channel) {
    return new NoiseMonitorServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NoiseMonitorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NoiseMonitorServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NoiseMonitorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NoiseMonitorServiceFutureStub(channel);
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will track all the level of noisy along the night that are sent by the client 
   * </pre>
   */
  public static abstract class NoiseMonitorServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *Client Streaming 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service2.NoiseMonitorRequest> noiseMonitor(
        io.grpc.stub.StreamObserver<service2.NoiseMonitorResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getNoiseMonitorMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getNoiseMonitorMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                service2.NoiseMonitorRequest,
                service2.NoiseMonitorResponse>(
                  this, METHODID_NOISE_MONITOR)))
          .build();
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will track all the level of noisy along the night that are sent by the client 
   * </pre>
   */
  public static final class NoiseMonitorServiceStub extends io.grpc.stub.AbstractStub<NoiseMonitorServiceStub> {
    private NoiseMonitorServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NoiseMonitorServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NoiseMonitorServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NoiseMonitorServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *Client Streaming 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service2.NoiseMonitorRequest> noiseMonitor(
        io.grpc.stub.StreamObserver<service2.NoiseMonitorResponse> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getNoiseMonitorMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will track all the level of noisy along the night that are sent by the client 
   * </pre>
   */
  public static final class NoiseMonitorServiceBlockingStub extends io.grpc.stub.AbstractStub<NoiseMonitorServiceBlockingStub> {
    private NoiseMonitorServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NoiseMonitorServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NoiseMonitorServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NoiseMonitorServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will track all the level of noisy along the night that are sent by the client 
   * </pre>
   */
  public static final class NoiseMonitorServiceFutureStub extends io.grpc.stub.AbstractStub<NoiseMonitorServiceFutureStub> {
    private NoiseMonitorServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NoiseMonitorServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NoiseMonitorServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NoiseMonitorServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_NOISE_MONITOR = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NoiseMonitorServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NoiseMonitorServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_NOISE_MONITOR:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.noiseMonitor(
              (io.grpc.stub.StreamObserver<service2.NoiseMonitorResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NoiseMonitorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NoiseMonitorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service2.NoiseMonitorServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NoiseMonitorService");
    }
  }

  private static final class NoiseMonitorServiceFileDescriptorSupplier
      extends NoiseMonitorServiceBaseDescriptorSupplier {
    NoiseMonitorServiceFileDescriptorSupplier() {}
  }

  private static final class NoiseMonitorServiceMethodDescriptorSupplier
      extends NoiseMonitorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NoiseMonitorServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (NoiseMonitorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NoiseMonitorServiceFileDescriptorSupplier())
              .addMethod(getNoiseMonitorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
