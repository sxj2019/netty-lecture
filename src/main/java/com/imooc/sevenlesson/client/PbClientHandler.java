package com.imooc.sevenlesson.client;

import com.imooc.sevenlesson.DataInfo;
//import com.imooc.sevenlesson.Student;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-19 17:21
 **/
public class PbClientHandler extends SimpleChannelInboundHandler<DataInfo.Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Message msg) throws Exception {

        System.out.println("From Server: ");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded...");
//        Student.Person person = Student.Person.newBuilder().setName("shiXiaoJun")
//                .setId(1011).setEmail("1010@qq.com").build();
////        byte[] bytes = person.toByteArray();
//        ctx.channel().writeAndFlush(person);

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive...");
//        Student.Person person = Student.Person.newBuilder().setName("shiXiaoJun")
//                .setId(1011).setEmail("1010@qq.com").build();
//        ctx.channel().writeAndFlush(person);
        DataInfo.Message message = null;
        int randomInt = new Random().nextInt(3);
        if(1 == randomInt){
            DataInfo.Cat cat = DataInfo.Cat.newBuilder().setId(11)
                    .setName("cat").setAge(2).build();
            message = DataInfo.Message.newBuilder().setDateType(DataInfo.Message.DataType.CatType)
                    .setCat(cat).build();
        }else if(2 == randomInt){
            DataInfo.Dog dog = DataInfo.Dog.newBuilder().setId(22)
                    .setName("Dog").setAge(3).build();
            message = DataInfo.Message.newBuilder().setDateType(DataInfo.Message.DataType.DogType)
                    .setDog(dog).build();
        }else {
            DataInfo.Bird bird = DataInfo.Bird.newBuilder().setId(33)
                    .setName("bird").setAge(1).build();
            message = DataInfo.Message.newBuilder().setDateType(DataInfo.Message.DataType.BirdType)
                    .setBird(bird).build();
        }

        ctx.channel().writeAndFlush(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
