package com.zhaotao.framework.dao;

import java.io.Serializable;

/**
 * @Author: huhu
 * @Date: 2019-08-10 19:28
 */
public interface BaseDAO {

    /**
     * insert into xxx(col1, col2, col3...) values(?,?,?...)
     *
     * @param t
     * @param <T>
     * @return
     */
    <T>Serializable save(T t);

}
