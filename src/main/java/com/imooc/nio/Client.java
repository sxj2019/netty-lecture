package com.imooc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-29 22:50
 **/
public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8899);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(inetSocketAddress);
        socketChannel.configureBlocking(false);
        while (!socketChannel.finishConnect()){
            System.out.println("未完成连接");
        }

        //完成连接，往服务器发送信息
        ByteBuffer buffer = ByteBuffer.allocate(20);
        int cnt=0;
        while (socketChannel.read(buffer) != -1){
            cnt++;
            System.out.println(cnt);
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println(cnt+ ","+(char)buffer.get());
            }
            buffer.clear();
        }
//        socketChannel.read(buffer);
        socketChannel.write(ByteBuffer.wrap("Hello,server!".getBytes()));
        Thread.sleep(5000);
        socketChannel.close();
    }
}
