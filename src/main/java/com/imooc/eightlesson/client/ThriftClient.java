package com.imooc.eightlesson.client;

import com.imooc.eightlesson.thrift.generated.Person;
import com.imooc.eightlesson.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-20 11:42
 **/
public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport = new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(tTransport);
        PersonService.Client client = new PersonService.Client(protocol);

        try{
            tTransport.open();

            Person person = client.getPersonByName("张三");
            System.out.println("客户端收到："+person);
            System.out.println("-----------------");
            Person p2 = new Person();
            p2.setName("LiSi");
            p2.setAge(30);
            p2.setIsMarried(true);
            client.savePerson(p2);
        }catch (Exception ex){

        }finally {

        }

    }
}
