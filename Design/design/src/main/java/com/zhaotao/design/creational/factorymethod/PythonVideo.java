package com.zhaotao.design.creational.factorymethod;

/**
 * @Author: huhu
 * @Date: 2019-05-25 01:13
 */
public class PythonVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制Python课程视频");
    }
}
