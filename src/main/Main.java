package main;

import java.sql.Connection;

/**
 * Created by Rudie en Paul on 20-5-14.
 */
public class Main {
    // Change variables to your own settings.
    public static final Database DATABASE = new Database("Opdracht2");
    public static final String USERNAME = "javauser";
    public static final String PASSWORD = "java";

    public static final int DEFAULT_ISO_LEVEL = Connection.TRANSACTION_READ_COMMITTED;

    // Show the deadlock simulation (else shows the Phantom, Unrepeatable and Dirty Read.
    public static final boolean SHOW_DEADLOCK = false;

    public static void main(String[] args) {

        if (SHOW_DEADLOCK) {
            // Deadlock
            for (int i = 0; i < Integer.valueOf(args[0]); i++) {
                new Transactions().startThread(Transactions.DEADLOCK);
            }
        } else {
            // Phantom / Unrepeatable Read / Dirty Read
            for (int i = 0; i < Integer.valueOf(args[0]); i++) {
                new Transactions().startThread(Transactions.INSERT);
                new Transactions().startThread(Transactions.SELECT);
            }
        }
    }
}
