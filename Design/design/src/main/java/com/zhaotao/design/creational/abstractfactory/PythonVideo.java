package com.zhaotao.design.creational.abstractfactory;

/**
 * @Author: huhu
 * @Date: 2019-06-23 17:13
 */
public class PythonVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制Python课程视频");
    }
}
