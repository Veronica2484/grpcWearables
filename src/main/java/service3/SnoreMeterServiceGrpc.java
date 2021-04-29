package service3;

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
 *Name of the service + keyword Service. This service will monitor the level of snore from the sleepers and will send an alert when it overtakes a certain level
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: service3.proto")
public final class SnoreMeterServiceGrpc {

  private SnoreMeterServiceGrpc() {}

  public static final String SERVICE_NAME = "service3.SnoreMeterService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<service3.SnoreMeterRequest,
      service3.SnoreMeterResponse> getSnoreMeterMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SnoreMeter",
      requestType = service3.SnoreMeterRequest.class,
      responseType = service3.SnoreMeterResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<service3.SnoreMeterRequest,
      service3.SnoreMeterResponse> getSnoreMeterMethod() {
    io.grpc.MethodDescriptor<service3.SnoreMeterRequest, service3.SnoreMeterResponse> getSnoreMeterMethod;
    if ((getSnoreMeterMethod = SnoreMeterServiceGrpc.getSnoreMeterMethod) == null) {
      synchronized (SnoreMeterServiceGrpc.class) {
        if ((getSnoreMeterMethod = SnoreMeterServiceGrpc.getSnoreMeterMethod) == null) {
          SnoreMeterServiceGrpc.getSnoreMeterMethod = getSnoreMeterMethod = 
              io.grpc.MethodDescriptor.<service3.SnoreMeterRequest, service3.SnoreMeterResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "service3.SnoreMeterService", "SnoreMeter"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service3.SnoreMeterRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  service3.SnoreMeterResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SnoreMeterServiceMethodDescriptorSupplier("SnoreMeter"))
                  .build();
          }
        }
     }
     return getSnoreMeterMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SnoreMeterServiceStub newStub(io.grpc.Channel channel) {
    return new SnoreMeterServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SnoreMeterServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SnoreMeterServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SnoreMeterServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SnoreMeterServiceFutureStub(channel);
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will monitor the level of snore from the sleepers and will send an alert when it overtakes a certain level
   * </pre>
   */
  public static abstract class SnoreMeterServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *BiDi Streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service3.SnoreMeterRequest> snoreMeter(
        io.grpc.stub.StreamObserver<service3.SnoreMeterResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getSnoreMeterMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSnoreMeterMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                service3.SnoreMeterRequest,
                service3.SnoreMeterResponse>(
                  this, METHODID_SNORE_METER)))
          .build();
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will monitor the level of snore from the sleepers and will send an alert when it overtakes a certain level
   * </pre>
   */
  public static final class SnoreMeterServiceStub extends io.grpc.stub.AbstractStub<SnoreMeterServiceStub> {
    private SnoreMeterServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SnoreMeterServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SnoreMeterServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SnoreMeterServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *BiDi Streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<service3.SnoreMeterRequest> snoreMeter(
        io.grpc.stub.StreamObserver<service3.SnoreMeterResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSnoreMeterMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will monitor the level of snore from the sleepers and will send an alert when it overtakes a certain level
   * </pre>
   */
  public static final class SnoreMeterServiceBlockingStub extends io.grpc.stub.AbstractStub<SnoreMeterServiceBlockingStub> {
    private SnoreMeterServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SnoreMeterServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SnoreMeterServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SnoreMeterServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * <pre>
   *Name of the service + keyword Service. This service will monitor the level of snore from the sleepers and will send an alert when it overtakes a certain level
   * </pre>
   */
  public static final class SnoreMeterServiceFutureStub extends io.grpc.stub.AbstractStub<SnoreMeterServiceFutureStub> {
    private SnoreMeterServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SnoreMeterServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SnoreMeterServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SnoreMeterServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SNORE_METER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SnoreMeterServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SnoreMeterServiceImplBase serviceImpl, int methodId) {
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
        case METHODID_SNORE_METER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.snoreMeter(
              (io.grpc.stub.StreamObserver<service3.SnoreMeterResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SnoreMeterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SnoreMeterServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return service3.SnoreMeterServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SnoreMeterService");
    }
  }

  private static final class SnoreMeterServiceFileDescriptorSupplier
      extends SnoreMeterServiceBaseDescriptorSupplier {
    SnoreMeterServiceFileDescriptorSupplier() {}
  }

  private static final class SnoreMeterServiceMethodDescriptorSupplier
      extends SnoreMeterServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SnoreMeterServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SnoreMeterServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SnoreMeterServiceFileDescriptorSupplier())
              .addMethod(getSnoreMeterMethod())
              .build();
        }
      }
    }
    return result;
  }
}
