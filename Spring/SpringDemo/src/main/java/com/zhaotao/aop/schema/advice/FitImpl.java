package com.zhaotao.aop.schema.advice;

/**
 * @Author: huhu
 * @Date: 2019-03-24 23:49
 */
public class FitImpl implements Fit {

    @Override
    public void filter() {
        System.out.println("FitImpl filter.");
    }

}
