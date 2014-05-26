package main;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Rudie on 25-5-14.
 */
public class Transactions {

    private static final int RUNS = 100;
    private static final int MIN = -5;
    private static final int MAX = 5;

    public static final int PHANTOM = 0;
    public static final int DIRTY_READ = 1;

    public void startThread(final int transaction, final boolean rollback){
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
                while (true){
                    switch (transaction){
                        case PHANTOM:
                            phantomTransaction(database);
                            break;
                        case DIRTY_READ:
                            dirtyReadTransaction(database, rollback);
                            break;
                    }

                    try {
                        Thread.sleep((int) Math.random() * 11);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i++;
                }
            }
        }).start();
    }

    private void phantomTransaction(Database database){
        try {
            database.insertQuery("INSERT INTO Stock(Product_id, Modification) VALUES(2, " +
                    String.valueOf((int) (Math.random() * (MAX - MIN) + MIN)) + ");"
                    , Connection.TRANSACTION_READ_COMMITTED, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void dirtyReadTransaction(Database database, boolean rollback){
        try {
            database.insertQuery("INSERT INTO Stock(Product_id, Modification) VALUES(2, " +
                    String.valueOf((int) (Math.random() * (MAX - MIN) + MIN)) + ");"
                    , Connection.TRANSACTION_READ_COMMITTED
                    , rollback);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
