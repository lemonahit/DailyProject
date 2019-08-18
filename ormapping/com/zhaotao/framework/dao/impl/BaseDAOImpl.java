package com.zhaotao.framework.dao.impl;

import com.zhaotao.framework.dao.BaseDAO;
import com.zhaotao.framework.domain.User;
import com.zhaotao.framework.utils.DBUtils;
import com.zhaotao.framework.utils.Tools;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: huhu
 * @Date: 2019-08-10 19:35
 */
public class BaseDAOImpl implements BaseDAO {

    /**
     * 入参是T：User
     *
     * insert into t_user (name,age,birth_day) values (?,?,?);
     *
     * t: user
     * user.getId() user.getName() 通过这种方式依次将值给获取出来
     *
     * 常见面试考点：StringBuffer与StringBuilder的区别
     *
     * @param t
     * @param <T>
     * @return
     */
    @Override
    public <T> Serializable save(T t) {

        StringBuilder builder = new StringBuilder("insert into ");
        String table = Tools.getTable(t.getClass());
        builder.append(table).append(" (");

        Class<?> clazz = t.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //判断获取的字段是否为id，id为自增主键，因此不需要拼接
            if (!field.getName().equals("id")) {
                String column = Tools.getColumn(field);
                builder.append(column).append(",");
            }
        }

        // 删除逗号
        builder.deleteCharAt(builder.toString().length()-1)
                .append(") values (");

        // 有几个?取决于需要拼接几个字段
        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                builder.append("?,");
            }
        }

        // 删除逗号
        builder.deleteCharAt(builder.toString().length()-1)
                .append(")");

//        System.out.println(builder.toString());

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs =null;
        int index = 1;

        try {
            connection = DBUtils.getConnection();
            // 将拼接好的SQL传入
//            pstmt = connection.prepareStatement(builder.toString());
            // 传入拼接好的sql以及需要返回的字段名 id
            pstmt = connection.prepareStatement(builder.toString(), new String[]{"id"});

            for (Field field : fields) {
                if (!field.getName().equals("id")) {
                    String getMethod = Tools.getMethod(field);
                    Method method = clazz.getDeclaredMethod(getMethod);
                    Object obj = method.invoke(t);
                    pstmt.setObject(index++, obj);
                }
            }

            int rowCount = pstmt.executeUpdate();
            System.out.println("rowCount: " + rowCount);
            if (rowCount > 0) {
                rs = pstmt.getGeneratedKeys();
                rs.next();
                // 返回id字段的最新值
                return (Serializable) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
