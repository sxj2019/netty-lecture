package com.imooc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-29 22:50
 **/
public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.wrap("Hello,client!".getBytes());
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8899));
        ssc.configureBlocking(false);
        while (true){
            System.out.println("Waiting for connection...");
            SocketChannel sc = ssc.accept();
            if(sc == null){
                System.out.println("***No connection**");
                Thread.sleep(2000);
            }else {
                System.out.println("***Have connection..**"+sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            }
        }

    }
}
