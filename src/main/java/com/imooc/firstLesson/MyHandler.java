package com.imooc.firstLesson;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


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
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,sxj!",CharsetUtil.UTF_8);
            FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK
                    ,byteBuf);

            resp.headers().add(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            resp.headers().add(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());

            ctx.writeAndFlush(resp);
        }

    }
}
