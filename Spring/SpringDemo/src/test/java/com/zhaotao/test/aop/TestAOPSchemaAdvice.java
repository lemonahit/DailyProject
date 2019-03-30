package com.zhaotao.test.aop;

import com.zhaotao.aop.schema.advice.Fit;
import com.zhaotao.aop.schema.advice.biz.AspectBiz;
import com.zhaotao.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * @Author: huhu
 * @Date: 2019-03-10 23:29
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestAOPSchemaAdvice extends UnitTestBase {

    public TestAOPSchemaAdvice() {
        super("classpath:spring-aop-schema-advice.xml");
    }

    @Test
    public void testBiz() {
        AspectBiz biz = super.getBean("aspectBiz");
        biz.biz();
    }

    @Test
    public void testInit() {
        AspectBiz biz = super.getBean("aspectBiz");
        biz.init("moocService", 3);
    }

    @Test
    public void testFit() {
        Fit fit = (Fit)super.getBean("aspectBiz");
        fit.filter();
    }

}