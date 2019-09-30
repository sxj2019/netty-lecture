package com.imooc.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-27 19:16
 **/
public class NIOTest {
    public static void main(String[] args) throws IOException, InterruptedException {
//        test02();
//        test03();
//        test04();
//        test05();
//        test06();
//        test07();
    }


    public static void test07() throws IOException {
        ByteBuffer[] buffers = new ByteBuffer[3];
        buffers[0] = ByteBuffer.allocate(2);
        buffers[1] = ByteBuffer.allocate(3);
        buffers[2] = ByteBuffer.allocate(4);

        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        fileChannel.read(buffers);
//        Arrays.asList(buffers).stream().map(buffer -> buffer.position()+","+buffer.limit())
//                .forEach(System.out::println);
        Arrays.asList(buffers).stream().forEach(buf -> {
            buf.flip();
            while (buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println("**********");
        });

        randomAccessFile.close();

    }

    public static void test06() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("random.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
//        mappedByteBuffer.put(new byte[]{1,2,3,4,5});
        mappedByteBuffer.put(0,(byte)'A');
        randomAccessFile.close();
    }

    public static void test05(){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.getClass());
        for (int i=0;i<buffer.capacity();i++){
            buffer.put((byte)i);
        }
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

    }
    public static void test04(){
        ByteBuffer buffer = ByteBuffer.allocate(20);
        for (int i=0;i<10;i++){
            buffer.put((byte)i);
        }
//        buffer.flip();
//        while (buffer.hasRemaining()){
//            System.out.println(buffer.get());
//        }
        buffer.position(3).limit(5);
        ByteBuffer sliceBuffer = buffer.slice();
        for (int j=0;j<sliceBuffer.capacity();j++){
            byte b = sliceBuffer.get(j);
            b *= 2;
            sliceBuffer.put(j,b);
        }

//        buffer.flip();
        buffer.position(0).limit(buffer.capacity());
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }



    }

    public static void test03(){
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.putInt(59);// 4
        buffer.putChar('A');//1
        buffer.putDouble(12.0);//8
        System.out.println("before: "+buffer.position());
        buffer.flip();
        System.out.println("-----------");
        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());
//        System.out.println(buffer.getInt());
        System.out.println(buffer.getDouble());
        System.out.println("-----after: "+buffer.position());
    }

    public static void test02(){
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        byteBuffer.putInt(125);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }

    public static void test01(){
        IntBuffer intBuffer = IntBuffer.allocate(10);
        for (int i=0;i<5;i++){
            int rdm = new SecureRandom().nextInt(20);
            intBuffer.put(rdm);
        }
        System.out.println("before: limit="+intBuffer.limit()); //10
        intBuffer.flip();
        System.out.println("after: limit="+intBuffer.limit()); //5
        while (intBuffer.hasRemaining()){
            System.out.println("position: "+intBuffer.position()+", limit： "+intBuffer.limit());
            System.out.println(intBuffer.get());
        }
    }
}
