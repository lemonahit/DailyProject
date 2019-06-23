package com.zhaotao.design.creational.factorymethod;

/**
 * @Author: huhu
 * @Date: 2019-06-21 16:09
 */
public class PythonVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
