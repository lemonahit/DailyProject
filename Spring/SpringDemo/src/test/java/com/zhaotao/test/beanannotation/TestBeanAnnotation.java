package com.zhaotao.test.beanannotation;

import com.zhaotao.beanannotation.BeanAnnotation;
import com.zhaotao.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by 陶 on 2018/12/2.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanAnnotation extends UnitTestBase {

    public TestBeanAnnotation() {
        super("classpath*:spring-beanannotation.xml");
    }

    @Test
    public void testSay() {
//        BeanAnnotation bean = super.getBean("beanAnnotation");
        BeanAnnotation bean = super.getBean("bean");
        bean.say("This is test.");
    }

    @Test
    public void testScpoe() {
        BeanAnnotation bean = super.getBean("beanAnnotation");
        bean.myHashCode();

        bean = super.getBean("beanAnnotation");
        bean.myHashCode();
    }

}
