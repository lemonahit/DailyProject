package com.zhaotao.test.beanannotation;

import com.zhaotao.beanannotation.javabased.MyDriverManager;
import com.zhaotao.beanannotation.javabased.Store;
import com.zhaotao.beanannotation.javabased.StringStore;
import com.zhaotao.test.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/**
 * Created by 陶 on 2019/1/6.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class TestJavabased extends UnitTestBase {

    public TestJavabased() {
        super("classpath*:spring-beanannotation.xml");
    }

    @Test
    public void test() {
        Store store = super.getBean("stringStore");
        System.out.println(store.getClass().getName());
    }

    @Test
    public void testMyDriverManager() {
        MyDriverManager manager = super.getBean("myDriverManager");
        System.out.println(manager.getClass().getName());
    }

    @Test
    public void testScope() {
        Store store = super.getBean("stringStore");
        System.out.println(store.hashCode());

        store = super.getBean("stringStore");
        System.out.println(store.hashCode());
    }

    @Test
    public void testG() {
        StringStore store = super.getBean("stringStoreTest");
    }

}
