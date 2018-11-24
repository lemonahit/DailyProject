package com.zhaotao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by 陶 on 2018/11/18.
 */
public interface GirlRepository extends JpaRepository<Girl, Integer>{

    // 通过年龄来查询
    public List<Girl> findByAge(Integer age);
}
