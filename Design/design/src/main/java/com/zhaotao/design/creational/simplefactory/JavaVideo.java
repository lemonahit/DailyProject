package com.zhaotao.design.creational.simplefactory;

/**
 * @Author: huhu
 * @Date: 2019-05-25 01:12
 */
public class JavaVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制Java课程视频");
    }
}
