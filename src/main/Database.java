package main;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Rudie on 20-5-14.
 */
public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/Opdracht1";
    private static final String USERNAME = "javauser";
    private static final String PASSWORD = "java";

    private Connection connection;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public boolean insertQuery(String query){
        int result = 0;

        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);
            System.out.println(result);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result == 0){
            return false;
        }
        return true;
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
