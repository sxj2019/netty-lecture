package com.imooc.tenlesson.server;

import com.imooc.tenlesson.impl.PersonServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-26 15:45
 **/
public class GrpcServer {
    private Server server;

    private void start() throws IOException {
        this.server = ServerBuilder.forPort(8899).addService(new PersonServiceImpl())
                .build().start();
    }

    private void aWait() throws InterruptedException {
        if (null != this.server){
            this.server.awaitTermination();
        }
    }

    private void stop(){
        if (null != this.server){
            this.server.shutdown();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer server = new GrpcServer();
        server.start();
        server.aWait();
    }
}
