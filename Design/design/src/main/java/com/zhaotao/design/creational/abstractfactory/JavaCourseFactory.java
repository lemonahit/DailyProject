package com.zhaotao.design.creational.abstractfactory;

/**
 * @Author: huhu
 * @Date: 2019-06-23 15:51
 */
public class JavaCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }

    @Override
    public Article getArticle() {
        return new JavaArticle();
    }
}
