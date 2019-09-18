package com.imooc.secLesson.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.UUID;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-17 23:36
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<String> {
//    ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("-------客户端端口号："+ctx.channel().remoteAddress()+"数据："+msg+"\n");
        ctx.channel().writeAndFlush("From Server["+ctx.channel().localAddress()+"]: Hello,client... "+ UUID.randomUUID());
    }

//    @Override
//    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//        Channel channel =ctx.channel();
//        //广播
//        channels.writeAndFlush("[服务器]:"+channel.remoteAddress()+"上线了！");
//        //添加到channel组里
//        channels.add(channel);
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
