package com.imooc.tenlesson.impl;

import com.imooc.tenlesson.MyRequest;
import com.imooc.tenlesson.MyResponseList;
import com.imooc.tenlesson.MyResponseStream;
import com.imooc.tenlesson.PersonServiceGrpc;
import io.grpc.stub.StreamObserver;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-26 15:42
 **/
public class PersonServiceImpl extends PersonServiceGrpc.PersonServiceImplBase {
    @Override
    public void getPersonByAge(MyRequest request, StreamObserver<MyResponseStream> responseObserver) {
        System.out.println("参数是： "+request.getAge());
        responseObserver.onNext(MyResponseStream.newBuilder()
                .setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(MyResponseStream.newBuilder()
                .setName("李四").setAge(20).setCity("上海").build());
        responseObserver.onNext(MyResponseStream.newBuilder()
                .setName("王五").setAge(20).setCity("南京").build());
        responseObserver.onNext(MyResponseStream.newBuilder()
                .setName("赵六").setAge(20).setCity("杭州").build());
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<MyRequest> getPersonByAges(StreamObserver<MyResponseList> responseObserver) {

        return new StreamObserver<MyRequest>() {
            @Override
            public void onNext(MyRequest req) {
                System.out.println("参数Age: "+req.getAge());
                //返回给客户端
                MyResponseList respLst = MyResponseList.newBuilder()
                        .setRespStream(0,MyResponseStream.newBuilder().setName("张三").
                                setAge(20).setCity("北京").build())
                        .setRespStream(1, MyResponseStream.newBuilder().
                                setName("李四").setAge(20).setCity("上海").
                                build()).build();
                responseObserver.onNext(respLst);
                responseObserver.onCompleted();
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("接收请求完毕...");

            }
        };
    }
}
