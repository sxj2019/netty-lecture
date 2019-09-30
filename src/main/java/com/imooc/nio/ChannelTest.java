package com.imooc.nio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-27 19:25
 **/
public class ChannelTest {
    public static void main(String[] args) throws IOException, InterruptedException {
//        channelCopy2();
//        gather();
//        test();
        transferFrom();

        SelectableChannel x;
    }

    public static void server() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));
    }
    public static void transferFrom() throws IOException {
        ReadableByteChannel readableByteChannel = Channels.newChannel(System.in);
        RandomAccessFile file = new RandomAccessFile("1.txt","rw");
        FileChannel channel = file.getChannel();
        channel.transferFrom(readableByteChannel,0,10);
        System.out.println("1...");
        file.close();
        readableByteChannel.close();
    }

    public static void test() throws IOException {
        RandomAccessFile file = new RandomAccessFile("1.txt","rw");
        FileChannel channel = file.getChannel();
        WritableByteChannel writableByteChannel = Channels.newChannel(System.out);
        channel.transferTo(0,channel.size(),writableByteChannel);

        file.close();
        writableByteChannel.close();
    }

    public static void scatter() throws IOException {
        RandomAccessFile file = new RandomAccessFile("1.txt","rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer header = ByteBuffer.allocate(8);
        ByteBuffer body = ByteBuffer.allocate(4);
        ByteBuffer[] http = {header,body};


        fileChannel.read(http);

        header.flip();
        body.flip();
        while ((header.hasRemaining())){
            System.out.println((char) header.get());
        }
        System.out.println("---------------");

        while (body.hasRemaining()){
            System.out.println((char)body.get());
        }
    }

    public static void gather() throws IOException {
        ByteBuffer header = ByteBuffer.allocate(8);
        header.putChar('h');
        header.putChar('e');
        header.putChar('a');
        header.putChar('d');
        ByteBuffer body = ByteBuffer.allocate(4);
        body.putChar('1');
        body.putChar('2');

        header.flip();
        body.flip();



        ByteBuffer[] http = {header,body};

        RandomAccessFile file = new RandomAccessFile("4.txt","rw");
        FileChannel fileChannel = file.getChannel();
        fileChannel.write(http);
        file.close();
    }

    public static void channelCopy2() throws IOException {
        ReadableByteChannel srcChannel = Channels.newChannel(System.in);
        WritableByteChannel destChannel = Channels.newChannel(System.out);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int i= 0;
        while (srcChannel.read(buffer)!=-1){
            buffer.flip();
////            System.out.println("1...");
            ++i;
            while (buffer.hasRemaining()){
//                destChannel.write(buffer);
                System.out.println(i + "次，值="+(byte)buffer.get());
            }
////            System.out.println("2..");
            buffer.compact();

        }

        srcChannel.close();
        destChannel.close();

    }

    public static void channelCopy() throws IOException {
        ReadableByteChannel src = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int cnt = 0;
        while ((cnt = src.read(buffer)) != -1){
            System.out.println("cnt: "+cnt+",position:"+buffer.position()+",limit: "+buffer.limit());
            buffer.flip();
            dest.write(buffer);
//            buffer.clear();
            buffer.compact();
        }
        //EOF ??
        buffer.flip();
        while (buffer.hasRemaining()){
            dest.write(buffer);
        }
        System.out.println("end...");
        src.close();
        dest.close();

    }

    private static void readAndWriteFile() throws IOException, InterruptedException {
        FileInputStream inputStream = new FileInputStream("1.txt");
        FileChannel inChannel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        FileOutputStream outputStream = new FileOutputStream("2.txt");
        FileChannel outChannel = outputStream.getChannel();

        int cnt = 0;
        while (true){
            //byteBuffer.clear();
            System.out.println("position: "+byteBuffer.position());
            int read = inChannel.read(byteBuffer);
            System.out.println("readCnt: "+read);
            if (-1 == read) break;
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            System.out.println("--------cnt: "+cnt);
            if(++cnt >3){
                break;
            }

        }



        inputStream.close();
        outChannel.close();
    }

    private static void writeToFile() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(20);
        byte[] bytes = "You are welcome!".getBytes();
        byteBuffer.put(bytes);
        byteBuffer.flip();
        FileOutputStream fileOutputStream = new FileOutputStream("2.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileChannel.write(byteBuffer);
        fileOutputStream.close();

    }
    private static void readFromFile() throws IOException {
        FileInputStream fin = new FileInputStream("1.txt");
        FileChannel fileChannel = fin.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(20);
        fileChannel.read(buffer);
        System.out.println("*********");
        buffer.flip();
        while (buffer.remaining() > 0){
            byte bt = buffer.get();
            System.out.println("byte: "+bt+", "+(char)bt);
        }
        fin.close();
    }
}
