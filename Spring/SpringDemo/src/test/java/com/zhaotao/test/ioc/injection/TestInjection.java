package com.zhaotao.test.ioc.injection;

import com.zhaotao.ioc.injection.service.InjectionService;
import com.zhaotao.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by 陶 on 2018/11/5.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestInjection extends UnitTestBase {

    public TestInjection() {
        super("classpath:spring-injection.xml");
    }

    /**
     * 设值注入
     */
    @Test
    public void testSetter() {
        InjectionService service = super.getBean("injectionService");
        service.save("这是要保存的数据");
    }

    /**
     * 构造器注入
     */
    @Test
    public void testCons() {
        InjectionService service = super.getBean("injectionService");
        service.save("这是要保存的数据");
    }

}
