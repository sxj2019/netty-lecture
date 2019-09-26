package com.imooc.ninelesson.impl;

import com.imooc.ninelesson.MyRequest;
import com.imooc.ninelesson.MyResponse;
import com.imooc.ninelesson.NameServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-26 12:49
 **/
public class NameServiceImpl extends NameServiceGrpc.NameServiceImplBase {
    @Override
    public void getFullName(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接收到的参数："+request.getName());
        MyResponse response = MyResponse.newBuilder().setFullName("Lin XI").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
