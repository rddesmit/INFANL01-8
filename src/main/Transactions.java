package main;

import java.sql.SQLException;

/**
 * Created by Rudie on 25-5-14.
 */
public class Transactions {

    private static final int RUNS = 100;
    private static final int MIN = -5;
    private static final int MAX = 5;

    public static final int INSERT = 0;
    public static final int SELECT = 1;

    public void startThread(final int transaction){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Database database = new Database("Opdracht2");
                try {
                    database.connect();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                int i = 0;
                while (i < RUNS){
                    switch (transaction){
                        case INSERT:
                            insert(database);
                            break;
                        case SELECT:
                            select(database);
                            break;
                    }

                   try {
                        Thread.sleep((int) Math.random() * 500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }

                try {
                    database.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void insert(Database database){
        try {
            database.insertTransactionQuery(
                    "INSERT INTO Stock(Product_id, Modification) " +
                            "VALUES(2, " + String.valueOf((int) (Math.random() * (MAX - MIN) + MIN)) + "); ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void select(Database database){
        try {
            database.insertTransactionQuery("SELECT check_concurency(2) FROM Stock;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
