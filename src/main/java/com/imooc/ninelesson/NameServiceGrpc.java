package com.imooc.ninelesson;

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
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: Person.proto")
public final class NameServiceGrpc {

  private NameServiceGrpc() {}

  public static final String SERVICE_NAME = "ninelesson.NameService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getGetFullNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFullName",
      requestType = MyRequest.class,
      responseType = MyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<MyRequest,
      MyResponse> getGetFullNameMethod() {
    io.grpc.MethodDescriptor<MyRequest, MyResponse> getGetFullNameMethod;
    if ((getGetFullNameMethod = NameServiceGrpc.getGetFullNameMethod) == null) {
      synchronized (NameServiceGrpc.class) {
        if ((getGetFullNameMethod = NameServiceGrpc.getGetFullNameMethod) == null) {
          NameServiceGrpc.getGetFullNameMethod = getGetFullNameMethod =
              io.grpc.MethodDescriptor.<MyRequest, MyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFullName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  MyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new NameServiceMethodDescriptorSupplier("getFullName"))
              .build();
        }
      }
    }
    return getGetFullNameMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NameServiceStub newStub(io.grpc.Channel channel) {
    return new NameServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NameServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new NameServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NameServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new NameServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class NameServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getFullName(MyRequest request,
                            io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFullNameMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetFullNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                MyRequest,
                MyResponse>(
                  this, METHODID_GET_FULL_NAME)))
          .build();
    }
  }

  /**
   */
  public static final class NameServiceStub extends io.grpc.stub.AbstractStub<NameServiceStub> {
    private NameServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NameServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameServiceStub(channel, callOptions);
    }

    /**
     */
    public void getFullName(MyRequest request,
                            io.grpc.stub.StreamObserver<MyResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFullNameMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NameServiceBlockingStub extends io.grpc.stub.AbstractStub<NameServiceBlockingStub> {
    private NameServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NameServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public MyResponse getFullName(MyRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetFullNameMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NameServiceFutureStub extends io.grpc.stub.AbstractStub<NameServiceFutureStub> {
    private NameServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private NameServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected NameServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new NameServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<MyResponse> getFullName(
        MyRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFullNameMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_FULL_NAME = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NameServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NameServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_FULL_NAME:
          serviceImpl.getFullName((MyRequest) request,
              (io.grpc.stub.StreamObserver<MyResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NameServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NameServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrpcData.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NameService");
    }
  }

  private static final class NameServiceFileDescriptorSupplier
      extends NameServiceBaseDescriptorSupplier {
    NameServiceFileDescriptorSupplier() {}
  }

  private static final class NameServiceMethodDescriptorSupplier
      extends NameServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NameServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NameServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NameServiceFileDescriptorSupplier())
              .addMethod(getGetFullNameMethod())
              .build();
        }
      }
    }
    return result;
  }
}
