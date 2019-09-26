package com.imooc.sevenlesson;

import com.imooc.sevenlesson.Student;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-19 17:49
 **/
public class StudentTest {
    public static void main(String[] args) throws Exception{
        Student.Person person = Student.Person.newBuilder()
                .setId(11).setName("sxj").setEmail("1011@qq.com").build();
        byte[] bytes = person.toByteArray();
        Student.Person p2 = Student.Person.parseFrom(bytes);
        System.out.println(p2);
    }
}
