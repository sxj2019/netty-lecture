package com.imooc.eightlesson.server;

import com.imooc.eightlesson.PersonServiceImpl;
import com.imooc.eightlesson.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

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

        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        server.serve();
    }
}
