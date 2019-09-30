package com.imooc.decorator;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-27 18:40
 **/
public class ConstructComponent implements Component {
    @Override
    public void doSometing() {
        System.out.println("功能A...");
    }
}
