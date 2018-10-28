package com.zhaotao.ioc.interfaces;

/**
 * Created by 陶 on 2018/10/28.
 */
public class Main {

    public static void main(String[] args) {
        OneInterface oif = new OneInterfaceImpl();
        System.out.println(oif.hello("word"));
    }

}
