package com.imooc.eightlesson.server;

import com.imooc.eightlesson.PersonServiceImpl;
import com.imooc.eightlesson.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.*;
import org.apache.thrift.transport.*;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-20 11:36
 **/
public class ThriftServer {
    public static void main(String[] args) throws Exception{
        TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
        THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2)
                .maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<>(
                new PersonServiceImpl()
        );

        /*传输格式：
        TBinaryProtocol 二进制格式
        TCompactProtocol 压缩格式;
        TJSONProtocol Json格式*/
        arg.protocolFactory(new TCompactProtocol.Factory());
        /*传输方式：
        TSocket 阻塞式socket;  TFramedTransport 以Frame为单位进行传输，非阻塞式;
        TFileTransport 以文件方式进行传输;
        TMemoryInputTransport 将内存用于IO ?? */
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        /*服务模型
        TSimpleServer 简单的单线程服务模型;
        TThreadPoolServer 多线程服务模型，阻塞式IO;
        TNonblockingServer 多线程，非阻塞式IO;
        THsHaServer 半同步半异步 ;*/
        TServer server = new THsHaServer(arg);
        server.serve();
    }
}
