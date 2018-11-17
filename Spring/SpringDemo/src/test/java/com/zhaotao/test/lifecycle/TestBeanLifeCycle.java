package com.zhaotao.test.lifecycle;

import com.zhaotao.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by 陶 on 2018/11/10.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestBeanLifeCycle extends UnitTestBase{

    public TestBeanLifeCycle() {
        super("classpath:spring-lifecycle.xml");
    }

    @Test
    public void test1() {
        super.getBean("beanLifeCycle");
    }
}
