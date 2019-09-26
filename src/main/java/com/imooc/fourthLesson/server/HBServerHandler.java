package com.imooc.fourthLesson.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-18 16:02
 **/
public class HBServerHandler extends ChannelInboundHandlerAdapter {
    //用户状态触发器
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("1..");
        if(evt instanceof IdleStateEvent){
            System.out.println("2..");
            IdleStateEvent idleEvt = (IdleStateEvent) evt;
            IdleState state = idleEvt.state();
            String evtType = null;
            switch (state){
                case READER_IDLE:
                    evtType = "读空闲";
                    break;
                case WRITER_IDLE:
                    evtType = "写空闲";
                    break;
                case ALL_IDLE:
                    evtType = "读写空闲";
            }
//            ctx.channel().writeAndFlush("aaa..."+msg+"\n");
            System.out.println(ctx.channel().remoteAddress()+" 超时事件："+evtType);
            ctx.channel().close();
        }
    }
}
