package com.imooc.eightlesson;

import com.imooc.eightlesson.thrift.generated.DataException;
import com.imooc.eightlesson.thrift.generated.Person;
import com.imooc.eightlesson.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-20 11:33
 **/
public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByName(String name) throws DataException, TException {
        System.out.println("getPersonByName...");
        Person person = new Person();
        person.setName(name);
        person.setAge(20);
        person.setIsMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("savePerson...");
        System.out.println(person);
    }
}
