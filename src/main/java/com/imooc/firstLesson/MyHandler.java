package com.imooc.firstLesson;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;


/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-17 11:39
 **/
public class MyHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest){
            HttpRequest req = (HttpRequest) msg;
            System.out.println("请求名称： "+req.method().name());
            URI uri = new URI(req.uri());
            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求favicon.ico...");
                return;
            }
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,sxj!",CharsetUtil.UTF_8);
            FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
                    ,byteBuf);

            resp.headers().add(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            resp.headers().add(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());

            ctx.writeAndFlush(resp);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("3,channelActive...");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("2,channelRegistered..");
        super.channelRegistered(ctx);
    }

//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        System.out.println("3,channelReadComplete..");
//        super.channelReadComplete(ctx);
//    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("1,Handeler added...");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("5,channel unRegistered...");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("4,channel Inactive..");
        super.channelInactive(ctx);
    }
}
