package com.zhaotao.design.creational.factorymethod;

/**
 * @Author: huhu
 * @Date: 2019-06-21 16:21
 */
public class FEVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new FEVideo();
    }
}
