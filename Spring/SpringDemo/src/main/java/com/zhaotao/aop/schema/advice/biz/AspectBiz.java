package com.zhaotao.aop.schema.advice.biz;

/**
 * @Author: huhu
 * @Date: 2019-02-17 23:30
 */
public class AspectBiz {

    public void biz() {
        System.out.println("AspectBiz biz.");
//        throw new RuntimeException();
    }

    public void init(String bizName, int times) {
        System.out.println("AspectBiz init : " + bizName + "   " + times);
    }
}
