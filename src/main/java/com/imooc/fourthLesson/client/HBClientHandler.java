package com.imooc.fourthLesson.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-18 16:13
 **/
public class HBClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead0...");
        System.out.println("From Server: "+msg);
    }
}
