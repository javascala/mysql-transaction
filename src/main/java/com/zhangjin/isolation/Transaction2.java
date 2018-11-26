package com.zhangjin.isolation;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;

public class Transaction2 implements Runnable {

    private Connection connection;

    private CountDownLatch countDownLatch1;

    private CountDownLatch countDownLatch2;

    public Transaction2(Connection connection, CountDownLatch countDownLatch1, CountDownLatch countDownLatch2) {
        this.connection = connection;
        this.countDownLatch1 = countDownLatch1;
        this.countDownLatch2 = countDownLatch2;
    }

    @Override
    public void run() {
        try {
//            connection.setAutoCommit(false);
            countDownLatch2.await();
            connection.prepareStatement("INSERT into account (accountname, account) values(\"wangwu\", 1000)").execute();
//            connection.prepareStatement("UPDATE account set account = account+500 where accountname = \"lisi\"").execute();
            //let transaction 1 continue and isuue a select statement,
            // even this current transaction is not committed, transaction 1 will be able to see the inserted value but the statement above !
//            connection.commit();
            countDownLatch1.countDown();

            //wait before commit, we want to make sure that this transaction is not committed and yet transaction 1 can still read the inserted row !
//            countDownLatch1.await();
            // commit and exit !
        } catch (Exception ex) {

        }
    }
}
