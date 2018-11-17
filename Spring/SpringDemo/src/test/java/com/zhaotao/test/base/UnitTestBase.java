package com.zhaotao.test.base;

/**
 * Created by 陶 on 2018/10/28.
 */
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class UnitTestBase {

    private ClassPathXmlApplicationContext context;

    private String springXmlpath;

    public UnitTestBase() {}

    public UnitTestBase(String springXmlpath) {
        this.springXmlpath = springXmlpath;
    }


    @Before
    public void before() {
        /**
         * 执行之前去判断springXmlpath是否为空
         * 去加载这个xml文件
         */
        if (StringUtils.isEmpty(springXmlpath)) {
            springXmlpath = "classpath*:spring-*.xml";
        }
        try {
            // 去创建context，这就是我们所说的spring的容器
            // 当它启动之后，会去查找配置文件中配置的信息，并把这些信息解析且装载到context中去
            context = new ClassPathXmlApplicationContext(springXmlpath.split("[,\\s]+"));
            context.start();
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        context.destroy();
    }

    @SuppressWarnings("unchecked")
    protected <T extends Object> T getBean(String beanId) {
        try {
            // 在使用发过程中通过getBean方法去获取对象
            return (T)context.getBean(beanId);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected <T extends Object> T getBean(Class<T> clazz) {
        try {
            return context.getBean(clazz);
        } catch (BeansException e) {
            e.printStackTrace();
            return null;
        }
    }

}
