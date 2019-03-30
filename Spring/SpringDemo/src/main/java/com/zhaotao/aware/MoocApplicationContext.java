package com.zhaotao.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by 陶 on 2018/11/25.
 */
public class MoocApplicationContext implements ApplicationContextAware{

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        System.out.println("MoocApplicationContext : " + applicationContext.getBean("moocApplicationContext").hashCode());
    }
}
