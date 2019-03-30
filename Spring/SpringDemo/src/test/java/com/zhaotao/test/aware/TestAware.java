package com.zhaotao.test.aware;

import com.zhaotao.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by 陶 on 2018/11/25.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestAware extends UnitTestBase{

    public TestAware() {
        super("classpath:spring-aware.xml");
    }

	@Test
	public void testMoocApplicationContext() {
		System.out.println("testMoocApplicationContext : " + super.getBean("moocApplicationContext").hashCode());
	}

    @Test
    public void textMoocBeanName() {
        System.out.println("textMoocBeanName : " + super.getBean("moocBeanName").hashCode());
    }

}
