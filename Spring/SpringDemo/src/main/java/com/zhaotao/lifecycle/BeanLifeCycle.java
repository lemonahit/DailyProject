package com.zhaotao.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Bean生命周期
 *
 * Created by 陶 on 2018/11/10.
 */
public class BeanLifeCycle implements InitializingBean, DisposableBean{

    // xml文件配置bean生命周期
    public void start() {
        System.out.println("Bean start .");
    }

    public void stop() {
        System.out.println("Bean stop .");
    }

    // 通过实现接口配置bean生命周期
    @Override
    public void destroy() throws Exception {
        System.out.println("Bean destroy.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Bean afterPropertiesSet.");
    }

    // 全局默认配置
    public void defautInit() {
        System.out.println("Bean defautInit.");
    }

    public void defaultDestroy() {
        System.out.println("Bean defaultDestroy.");
    }
}
