package com.zhaotao.ioc.injection.service;

import com.zhaotao.ioc.injection.dao.InjectionDAO;

/**
 * Created by 陶 on 2018/11/5.
 */
public class InjectionServiceImpl implements InjectionService {

    private InjectionDAO injectionDAO;

    //构造器注入
    public InjectionServiceImpl(InjectionDAO injectionDAO) {
        this.injectionDAO = injectionDAO;
    }

    //设值注入
    public void setInjectionDAO(InjectionDAO injectionDAO) {
        this.injectionDAO = injectionDAO;
    }

    public void save(String arg) {
        //模拟业务操作
        System.out.println("Service接收参数：" + arg);
        arg = arg + ":" + this.hashCode();
        injectionDAO.save(arg);
    }

}
