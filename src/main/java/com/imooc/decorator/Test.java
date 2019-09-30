package com.imooc.decorator;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-27 18:44
 **/
public class Test {
    public static void main(String[] args) {
        Component component = new ConstructComponent();
        component.doSometing();
        System.out.println("--------------------");
        Component cmp2 = new ConstructDecorator1(component);
        cmp2.doSometing();
        System.out.println("--------------------");
        Component cmp3 = new ConstructDecorator2(cmp2);
        cmp3.doSometing();
    }
}
