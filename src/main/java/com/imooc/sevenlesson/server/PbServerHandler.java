package com.imooc.sevenlesson.server;

import com.imooc.sevenlesson.DataInfo;
import com.imooc.sevenlesson.Student;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-19 17:00
 **/
public class PbServerHandler extends SimpleChannelInboundHandler<DataInfo.Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {
//        ProtobufDecoder x;
//        System.out.println(msg);
        DataInfo.Message.DataType dataType = msg.getDateType();
        if (DataInfo.Message.DataType.CatType == dataType){
            DataInfo.Cat cat = msg.getCat();
            System.out.println("Cat: "+cat);
        }else if(DataInfo.Message.DataType.DogType == dataType){
            DataInfo.Dog dog = msg.getDog();
            System.out.println("Dog: "+dog);
        }else {
            DataInfo.Bird bird = msg.getBird();
            System.out.println("Bird: "+bird);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
