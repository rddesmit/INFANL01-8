package main;

import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Rudie en Paul on 20-5-14.
 */
public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/";

    private static int UNREPEATABLE_COUNT = 0;
    private static int PHANTOM_ROW = 0;

    private String table;
    private Connection connection;

    /**
     * Constructor for Database class.
     * @param table
     */
    public Database(String table){
        this.table = table;
    }

    /**
     * Setup a connection to the database. Disables autocommit and set the transaction isolation level to the default
     * value from main class.
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL + table, Main.USERNAME, Main.PASSWORD);
        connection.setAutoCommit(false);
        connection.setTransactionIsolation(Main.DEFAULT_ISO_LEVEL);
    }

    /**
     * Executing the given query and showing the output when colliding.
     * @param query
     * @throws SQLException
     */
    public void executeTransaction(String query) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
            connection.commit();
        } catch (PSQLException e){
            if(e.getMessage().equals("ERROR: unrepeatable read")){
                UNREPEATABLE_COUNT++;
                System.out.println(UNREPEATABLE_COUNT + "\t" + e.getMessage());
            }
            if(e.getMessage().equals("ERROR: phantom row")){
                PHANTOM_ROW++;
                System.out.println(PHANTOM_ROW + "\t" + e.getMessage());
            }

            connection.rollback();
            executeTransaction(query);
        }

    }

    /**
     * Disconnecting the connection to the database.
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        connection.close();
    }
}
