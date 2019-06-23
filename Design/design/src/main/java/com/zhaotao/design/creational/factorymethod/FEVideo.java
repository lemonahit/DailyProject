package com.zhaotao.design.creational.factorymethod;

/**
 * @Author: huhu
 * @Date: 2019-06-21 16:20
 */
public class FEVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制FE课程视频");
    }
}
