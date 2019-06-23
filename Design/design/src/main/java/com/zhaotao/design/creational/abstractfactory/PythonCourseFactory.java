package com.zhaotao.design.creational.abstractfactory;

/**
 * @Author: huhu
 * @Date: 2019-06-23 17:14
 */
public class PythonCourseFactory implements CourseFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }

    @Override
    public Article getArticle() {
        return new PythonArticle();
    }
}
