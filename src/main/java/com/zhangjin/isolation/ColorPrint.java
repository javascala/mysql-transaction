package com.zhangjin.isolation;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ColorPrint {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    static void colorPrint(String color, String s) {
        System.out.print(color + s + ANSI_RESET);
    }

    public static void redPrint(String s) {
        colorPrint(ANSI_RED, s);
    }

    public static void greenPrint(String s) {
        colorPrint(ANSI_GREEN, s);
    }

    public static void purplePrint(String s) {
        colorPrint(ANSI_PURPLE, s);
    }

    static void printResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int count = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < count; i++) {
                ColorPrint.redPrint(metaData.getColumnName(i + 1));
                ColorPrint.purplePrint("=");
                ColorPrint.greenPrint(resultSet.getString(i + 1));
                System.out.print(", ");
            }
            System.out.println();
        }
    }
}
