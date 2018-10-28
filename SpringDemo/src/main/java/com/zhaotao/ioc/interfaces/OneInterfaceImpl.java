package com.zhaotao.ioc.interfaces;

/**
 * Created by 陶 on 2018/10/28.
 */
public class OneInterfaceImpl implements OneInterface{

    @Override
    public String hello(String word) {
        return "Word from interface \"OneInterface\":" + word;
    }

}
