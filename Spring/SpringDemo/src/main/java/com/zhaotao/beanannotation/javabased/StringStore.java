package com.zhaotao.beanannotation.javabased;

/**
 * Created by 陶 on 2019/1/6.
 */
public class StringStore implements Store<String>{

    public void init() {
        System.out.println("This is init.");
    }

    public void destroy() {
        System.out.println("This is destroy.");
    }

}
