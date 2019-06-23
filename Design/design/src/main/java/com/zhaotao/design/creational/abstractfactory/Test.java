package com.zhaotao.design.creational.abstractfactory;

/**
 * @Author: huhu
 * @Date: 2019-06-23 16:27
 */
public class Test {
    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }
}
