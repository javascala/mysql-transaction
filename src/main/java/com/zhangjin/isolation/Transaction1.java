package com.zhangjin.isolation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.CountDownLatch;

public class Transaction1 implements Runnable{

    private Connection connection;

    private CountDownLatch countDownLatch1;

    private CountDownLatch countDownLatch2;

    public Transaction1(Connection connection, CountDownLatch countDownLatch1, CountDownLatch countDownLatch2) {
        this.connection = connection;
        this.countDownLatch1 = countDownLatch1;
        this.countDownLatch2 = countDownLatch2;
    }

    /**
     * 在同一个事务里面读两次，这两次中间有个更新，结果集都是一样的
     */
    @Override
    public void run() {
        try{
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            ResultSet resultSet = connection.prepareStatement("select * from account where account >0").executeQuery();

            System.out.println("result 1");
            ColorPrint.printResultSet(resultSet);
            countDownLatch2.countDown();
            countDownLatch1.await();

            connection.prepareStatement("UPDATE account set accountname = \"wangwu\" where accountname = \"wangwu\"").execute();
            ResultSet resultSet2 = connection.prepareStatement("select * from account where account >0").executeQuery();
            System.out.println("result 2");
            ColorPrint.printResultSet(resultSet2);

            connection.commit();
        } catch (Exception ex){

        }
    }
}
