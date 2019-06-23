package com.zhaotao.design.creational.abstractfactory;

/**
 * @Author: huhu
 * @Date: 2019-06-23 17:12
 */
public class PythonArticle extends Article {
    @Override
    public void produce() {
        System.out.println("编写Python课程手记");
    }
}
