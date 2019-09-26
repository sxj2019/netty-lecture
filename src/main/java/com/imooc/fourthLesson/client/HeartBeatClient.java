package com.imooc.fourthLesson.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-18 16:08
 **/
public class HeartBeatClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioSocketChannel.class)
                    .handler(new HBClientChannelInitializer());
//            ChannelFuture future = bootstrap.connect("localhost",8899).sync();
//            future.channel().closeFuture().sync();
            Channel channel = bootstrap.connect("localhost",8899).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                channel.writeAndFlush(br.readLine()+"\r\n");
            }
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}
