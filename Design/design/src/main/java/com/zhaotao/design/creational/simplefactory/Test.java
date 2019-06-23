package com.zhaotao.design.creational.simplefactory;

/**
 * @Author: huhu
 * @Date: 2019-05-25 01:14
 */
public class Test {
    public static void main(String[] args) {
//        VideoFactory videoFactory = new VideoFactory();
//        Video video = videoFactory.getVideo("java");
//        if (video == null) {
//            return;
//        }
//        video.produce();

        VideoFactory videoFactory = new VideoFactory();
        Video video = videoFactory.getVideo(JavaVideo.class);
        if (video == null) {
            return;
        }
        video.produce();
    }

}
