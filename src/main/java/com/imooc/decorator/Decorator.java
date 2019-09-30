package com.imooc.decorator;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-27 18:40
 **/
public  abstract class Decorator implements Component {

    private Component component;

    public Decorator(Component component){
        this.component = component;
    }
    @Override
    public void doSometing() {
        component.doSometing();
    }
}
