package com.imooc.thirdLesson.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-18 10:53
 **/
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    private  static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        //将从客户端收到的信息，转发给其他的客户端
        channels.forEach(ch ->{
            if(ch != channel){
                ch.writeAndFlush("收到来自["+channel.remoteAddress()+"]的信息["+msg+"]"+"\n");
            }else {
                ch.writeAndFlush("收到来自[MySelf]的信息["+msg+"]"+"\n");
            }
        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //广播
        channels.writeAndFlush("[服务器]："+channel.remoteAddress()+"加入！"+"\n");
        //添加
        channels.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        //广播
        channels.writeAndFlush("[服务器]："+ch.remoteAddress()+"退出！"+"\n");
        //删除： 默认会自己删除，不需要手动调用remove方法
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("----"+ctx.channel().remoteAddress()+"上线了！\n");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-----"+ctx.channel().remoteAddress()+"下线了！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
