package com.imooc.sevenlesson.client;

import com.imooc.sevenlesson.DataInfo;
import com.imooc.sevenlesson.Student;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-19 17:20
 **/
public class PbClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        //客户端把信息发送给服务器端，所以是编码
//        ProtobufEncoder x;
        // Encoder
 /* pipeline.addLast("frameEncoder", new {@link LengthFieldPrepender}(4));
 * pipeline.addLast("protobufEncoder", new {@link ProtobufEncoder}());
 * </pre>
 * and then you can use a {@code MyMessage} instead of a {@link ByteBuf}
 * as a message:
 * <pre>
 * void channelRead({@link ChannelHandlerContext} ctx, Object msg) {
 *     MyMessage req = (MyMessage) msg;
 *     MyMessage res = MyMessage.newBuilder().setText(
                    *                               "Did you say '" + req.getText() + "'?").build();
 *     ch.write(res);
 * */
//        pipeline.addLast("frameEncoder",new LengthFieldPrepender(4));
//        pipeline.addLast("protobufEncoder",new ProtobufEncoder());
//        pipeline.addLast(new PbClientHandler());

        pipeline.addLast(new ProtobufVarint32FrameDecoder());
//        pipeline.addLast(new ProtobufDecoder(Student.Person.getDefaultInstance()));
        pipeline.addLast(new ProtobufDecoder(DataInfo.Message.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new PbClientHandler());
    }
}
