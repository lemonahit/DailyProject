package com.zhaotao.ioc.injection.dao;

/**
 * Created by 陶 on 2018/11/5.
 */
public class InjectionDAOImpl implements InjectionDAO {

    public void save(String arg) {
        //模拟数据库保存操作
        System.out.println("保存数据：" + arg);
    }

}
