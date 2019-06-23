package com.zhaotao.design.creational.factorymethod;

/**
 * @Author: huhu
 * @Date: 2019-05-25 01:14
 */
public class Test {
    public static void main(String[] args) {
        VideoFactory videoFactory = new JavaVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();
    }

}
