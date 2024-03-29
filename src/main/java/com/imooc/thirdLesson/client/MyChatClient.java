package com.imooc.thirdLesson.client;

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
 * @create: 2019-09-18 10:49
 **/
public class MyChatClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());
            Channel channel = bootstrap.connect("localhost",8988).sync().channel();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                channel.writeAndFlush(br.readLine()+"\r\n");
            }
        }finally {
            group.shutdownGracefully();
        }
    }
}
