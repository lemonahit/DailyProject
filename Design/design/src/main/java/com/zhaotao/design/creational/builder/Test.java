package com.zhaotao.design.creational.builder;

/**
 * @Author: huhu
 * @Date: 2019-07-07 17:40
 */
public class Test {
    public static void main(String[] args) {
        CourseBuilder courseBuilder = new CourseActualBuilder();
        Coach coach = new Coach();
        coach.setCourseBuilder(courseBuilder);

        Course course = coach.makeCourse("Java",
                "Java PPT",
                "Java Video",
                "Java Article",
                "Java QA");
        System.out.println(course);
    }
}
