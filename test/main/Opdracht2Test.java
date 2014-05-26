package main;

import org.junit.*;

/**
 * Created by Rudie on 25-5-14.
 */
public class Opdracht2Test {

    Database database;

    @Before
    public void setup() throws Exception {
        database = new Database("Opdracht2");
        database.connect();
    }

    @Test
    public void unrepeatableRead(){
        new Transactions().startThread(Transactions.PHANTOM, false);
        new Transactions().startThread(Transactions.PHANTOM, false);
    }

    @After
    public void teardown() throws Exception {
        database.disconnect();

    }
}
