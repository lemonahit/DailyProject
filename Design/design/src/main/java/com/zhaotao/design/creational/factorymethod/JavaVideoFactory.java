package com.zhaotao.design.creational.factorymethod;

/**
 * @Author: huhu
 * @Date: 2019-06-21 16:07
 */
public class JavaVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}
