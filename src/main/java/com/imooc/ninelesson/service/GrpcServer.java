package com.imooc.ninelesson.service;

import com.imooc.ninelesson.impl.NameServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-26 12:55
 **/
public class GrpcServer {
    private Server server;

    private void start() throws IOException {
        this.server = ServerBuilder.forPort(8899).
                addService(new NameServiceImpl())
                .build().start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("1111111111111");
            this.stop();
        }));
    }

    private void stop(){
        System.out.println("server is stop!");
        if (null != this.server){
            this.server.shutdown();
        }
    }

    private void aWait() throws InterruptedException {
        if (null != this.server){
            this.server.awaitTermination();;
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        GrpcServer se = new GrpcServer();
        se.start();
        se.aWait();
    }

}
