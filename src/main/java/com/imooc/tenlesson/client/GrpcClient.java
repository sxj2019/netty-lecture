package com.imooc.tenlesson.client;

import com.imooc.tenlesson.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-26 15:49
 **/
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress("localhost",8899)
                .usePlaintext().build();
//        PersonServiceGrpc.PersonServiceBlockingStub stub =
//                PersonServiceGrpc.newBlockingStub(channel);
//        Iterator<MyResponseStream> respStream = stub.getPersonByAge(MyRequest.newBuilder().setAge(20).build());
//        while (respStream.hasNext()){
//            MyResponseStream resp = respStream.next();
//            System.out.println(resp.getName()+","+resp.getAge()+","+resp.getCity());
//        }

        StreamObserver<MyResponseList> respObserver = new StreamObserver<MyResponseList>() {
            @Override
            public void onNext(MyResponseList value) {
                System.out.println("onNext...");
//                System.out.println(value);
                value.getRespStreamList().forEach(resp -> {
                    System.out.println(resp.getName());
                    System.out.println(resp.getAge());
                    System.out.println(resp.getCity());
                    System.out.println("*******");
                });
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError...");
                System.out.println(t);
            }

            @Override
            public void onCompleted() {
                System.out.println("complete...");
            }
        };
        PersonServiceGrpc.PersonServiceStub asynStub = PersonServiceGrpc.newStub(channel);

        StreamObserver<MyRequest> reqObserver = asynStub.getPersonByAges(respObserver);
        //请求的观察者
        reqObserver.onNext(MyRequest.newBuilder().setAge(20).build());
        reqObserver.onNext(MyRequest.newBuilder().setAge(21).build());
        reqObserver.onCompleted();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
