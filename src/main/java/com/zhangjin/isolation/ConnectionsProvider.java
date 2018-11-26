package com.zhangjin.isolation;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionsProvider {

    static final String DB_URL = "jdbc:mysql://localhost/isolation-test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "zhangjin123";

    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
