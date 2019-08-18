package com.zhaotao.framework.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author: huhu
 * @Date: 2019-08-11 20:41
 */
public class DBUtils {

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/orm";
        String user = "root";
        String password = "root";
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
