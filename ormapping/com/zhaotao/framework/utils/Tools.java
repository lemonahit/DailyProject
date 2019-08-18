package com.zhaotao.framework.utils;

import com.zhaotao.framework.annotation.HuHuBean;
import com.zhaotao.framework.annotation.HuHuField;

import java.lang.reflect.Field;

/**
 * @Author: huhu
 * @Date: 2019-08-11 16:31
 */
public class Tools {

    /**
     * 根据注解获取表名
     *
     * @param clazz
     * @return
     */
    public static String getTable(Class<?> clazz) {

        String tableName = "";

        HuHuBean huHuBean = clazz.getAnnotation(HuHuBean.class);

        if (huHuBean != null) {
            // 获取注解中的值
            tableName = huHuBean.value();
        } else {
            // 如果为空，就直接获取类名
            tableName = clazz.getSimpleName();
        }

        return tableName;

    }

    /**
     * 根据注解获取属性名称
     *
     * @param field
     * @return
     */
    public static String getColumn(Field field) {

        String column = "";

        HuHuField huHuField = field.getAnnotation(HuHuField.class);
        if (huHuField != null) {
            // 获取注解中的值
            column = huHuField.value();
        } else {
            // 如果为空，就直接获取字段名
            column = field.getName();
        }

        return column;
    }

    /**
     * 根据字段获取对应的get方法
     *
     * @param field
     * @return
     */
    public static String getMethod(Field field) {
        String fieldName = field.getName();

        // id==>getId
        // name==>getNamet
        String name = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
        return "get" + name;
    }
}
