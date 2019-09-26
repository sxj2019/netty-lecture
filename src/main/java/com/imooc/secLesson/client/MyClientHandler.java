package com.imooc.secLesson.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-17 23:50
 **/
public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
       System.out.println("-------服务器端口号："+ctx.channel().remoteAddress()+"信息："+msg+"\n");
        ctx.channel().writeAndFlush("客户端发送信息： "+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("From Client["+ctx.channel().localAddress()+"]: hello，server...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
