package com.zhaotao.framework.domain;

import com.zhaotao.framework.annotation.HuHuBean;
import com.zhaotao.framework.annotation.HuHuField;

import java.util.Date;

/**
 * @Author: huhu
 * @Date: 2019-08-10 19:18
 */
@HuHuBean("t_user")
public class User {

    private Integer id;

    private String name;

    private Integer age;

    @HuHuField("birth_day")
    private Date bithday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBithday() {
        return bithday;
    }

    public void setBithday(Date bithday) {
        this.bithday = bithday;
    }

    public User() {}

    public User(String name, Integer age, Date bithday) {
        this.name = name;
        this.age = age;
        this.bithday = bithday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bithday=" + bithday +
                '}';
    }
}
