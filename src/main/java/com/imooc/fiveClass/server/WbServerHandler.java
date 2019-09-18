package com.imooc.fiveClass.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-18 17:23
 **/
public class WbServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("From Client: "+textWebSocketFrame.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("Hello,client!"));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded..."+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved.."+ctx.channel().id().asShortText());
    }
}
