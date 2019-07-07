package com.zhaotao.design.creational.builder.v2;

/**
 * @Author: huhu
 * @Date: 2019-07-07 19:32
 */
public class Test {
    public static void main(String[] args) {
        // 链式调用 + 按需调用
        Course course = new Course.CourseBuilder()
                .buildCourseName("Java")
                .buildCoursePPT("Java PPT")
                .buildCourseVideo("Java Video")
                .build();
        System.out.println(course);
    }
}
