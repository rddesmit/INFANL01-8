package main;

/**
 * Created by Rudie on 20-5-14.
 */
public class Main {

    public static void main(String[] args){
//        new Transactions().startThread(Transactions.PHANTOM);
//        new Transactions().startThread(Transactions.PHANTOM);

        new Transactions().startThread(Transactions.DIRTY_READ, true);
        new Transactions().startThread(Transactions.DIRTY_READ, false);
    }
}
