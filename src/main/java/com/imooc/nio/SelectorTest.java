package com.imooc.nio;

import java.io.IOException;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @program: netty-lecture
 * @description:
 * @author: sxj
 * @create: 2019-09-29 16:18
 **/
public class SelectorTest {
    public static void main(String[] args) throws IOException {
        Selector x;
        Selector.open();
        SelectorProvider.provider().openSelector();
    }
}
