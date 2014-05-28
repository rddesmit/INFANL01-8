package main;

import java.sql.SQLException;

/**
 * Created by Rudie en Paul on 25-5-14.
 */
public class Transactions {

    private static final int RUNS = 100;
    private static final int MIN = -5;
    private static final int MAX = 5;

    public static final int INSERT = 0;
    public static final int SELECT = 1;
    public static final int DEADLOCK = 2;

    /**
     * Starting threads with a transaction type (transaction type is the type of error we want to simulate).
     * @param transaction
     */
    public void startThread(final int transaction){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Main.DATABASE.connect();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                int i = 0;
                while (i < RUNS){
                    switch (transaction){
                        case INSERT:
                            insert(Main.DATABASE);
                            break;
                        case SELECT:
                            select(Main.DATABASE);
                            break;
                        case DEADLOCK:
                            deadlock(Main.DATABASE);
                    }

                   try {
                        Thread.sleep((int) Math.random() * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                try {
                    Main.DATABASE.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Insert transaction for inserting queries in the stock table with random amounts of modifcations.
     * @param database
     */
    private void insert(Database database){
        try {
            Main.DATABASE.executeTransaction(
                    "INSERT INTO Stock(Product_id, Modification) " +
                            "VALUES(2, " + String.valueOf((int) (Math.random() * (MAX - MIN) + MIN)) + "); ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Select transaction to call the check_concurency function. This function checks collisions for the types
     * phantom and non-repeatable read.
     * @param database
     */
    private void select(Database database){
        try {
            database.executeTransaction("SELECT check_concurency(2) FROM Stock;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert query to simulate the deadlock error. Postgresql automatically resolves this problem. The query will
     * eventually complete.
     * @param database
     */
    private void deadlock(Database database){
        try {
            database.executeTransaction("LOCK TABLE Products IN SHARE MODE; " +
                    "INSERT INTO Stock(Product_id, Modification) " +
                    "VALUES((SELECT _id FROM Products WHERE _id = " +
                    String.valueOf((int) (Math.random() * 10 + 1)) + "), " +
                    String.valueOf((int) (Math.random() * (MAX - MIN) + MIN)) + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
