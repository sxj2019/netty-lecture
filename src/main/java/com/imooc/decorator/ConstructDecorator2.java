package com.imooc.decorator;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-27 18:43
 **/
public class ConstructDecorator2 extends Decorator {
    public ConstructDecorator2(Component component) {
        super(component);
    }

    @Override
    public void doSometing() {
        super.doSometing();
        mth();
    }

    private void mth(){
        System.out.println("功能C...");
    }
}
