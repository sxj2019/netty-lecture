package com.imooc.ninelesson.client;

import com.imooc.ninelesson.MyRequest;
import com.imooc.ninelesson.MyResponse;
import com.imooc.ninelesson.NameServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-26 12:54
 **/
public class GrpcClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress("localhost",8899)
                .usePlaintext().build();

        NameServiceGrpc.NameServiceBlockingStub stub =
                NameServiceGrpc.newBlockingStub(channel);
        MyRequest req = MyRequest.newBuilder().setName("lx").build();
        MyResponse resp  = stub.getFullName(req);
        System.out.println("来自服务器："+resp.getFullName());
    }
}
