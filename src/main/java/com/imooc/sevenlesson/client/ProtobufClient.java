package com.imooc.sevenlesson.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-19 17:01
 **/
public class ProtobufClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup).channel(NioSocketChannel.class)
                    .handler(new PbClientInitializer());
            ChannelFuture future = bootstrap.connect("localhost",9988).sync();
            future.channel().closeFuture().sync();
        }finally {
            workGroup.shutdownGracefully();
        }
    }
}
