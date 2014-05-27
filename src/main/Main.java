package main;

import java.sql.Connection;

/**
 * Created by Rudie on 20-5-14.
 */
public class Main {

    public static void main(String[] args){
        //new Transactions().startThread(Transactions.UNREPEATABLE_READ, false);
        //new Transactions().startThread(Transactions.UNREPEATABLE_READ, false);

        new Transactions().startThread(Transactions.INSERT);
        new Transactions().startThread(Transactions.INSERT);
        new Transactions().startThread(Transactions.INSERT);
        new Transactions().startThread(Transactions.INSERT);
        new Transactions().startThread(Transactions.INSERT);

        new Transactions().startThread(Transactions.SELECT);
        new Transactions().startThread(Transactions.SELECT);
        new Transactions().startThread(Transactions.SELECT);
        new Transactions().startThread(Transactions.SELECT);
        new Transactions().startThread(Transactions.SELECT);

        //new Transactions().startThread(Transactions.DIRTY_READ, true);
        //new Transactions().startThread(Transactions.DIRTY_READ, false);
    }
}
