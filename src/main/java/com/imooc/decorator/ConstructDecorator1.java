package com.imooc.decorator;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-27 18:42
 **/
public class ConstructDecorator1 extends Decorator {

    public ConstructDecorator1(Component component) {
        super(component);
    }

    @Override
    public void doSometing() {
        super.doSometing();
        mth2();
    }

    public void mth2(){
        System.out.println("功能B...");
    }
}
