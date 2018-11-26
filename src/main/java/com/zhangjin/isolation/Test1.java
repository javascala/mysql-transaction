package com.zhangjin.isolation;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test1 {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        CountDownLatch t1 = new CountDownLatch(1);
        CountDownLatch t2 = new CountDownLatch(1);

        Connection connection1 = ConnectionsProvider.getConnection();
        Connection connection2 = ConnectionsProvider.getConnection();


        Transaction1 transaction1 = new Transaction1(connection1,t1, t2);
        Transaction2 transaction2 = new Transaction2(connection2,t1, t2);


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(transaction1);
        executorService.submit(transaction2);
    }
}
