package com.cjss.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCService {

    private static final String URL = "jdbc:mysql://localhost:3306/cjss?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "12332155";

    private Connection connection;

    private JDBCService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.exit(652);
        }
    }

    public static JDBCService getInstance() {
        return JDBCService.SingletonHandler.instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private static class SingletonHandler {
        static final JDBCService instance = new JDBCService();
    }
}
