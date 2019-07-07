package com.zhaotao.design.creational.builder;

/**
 * @Author: huhu
 * @Date: 2019-07-07 17:34
 */
public class Coach {
    private CourseBuilder courseBuilder;

    public void setCourseBuilder(CourseBuilder courseBuilder) {
        this.courseBuilder = courseBuilder;
    }

    public Course makeCourse(String courseName, String coursePPT,
                             String courseVideo, String courseArtile,
                             String courseQA) {
        this.courseBuilder.buildCourseName(courseName);
        this.courseBuilder.buildCoursePPT(coursePPT);
        this.courseBuilder.buildCourseVideo(courseVideo);
        this.courseBuilder.buildCourseArticle(courseArtile);
        this.courseBuilder.buildCourseQA(courseQA);

        return this.courseBuilder.makeCourse();
    }
}
