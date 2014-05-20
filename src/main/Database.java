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
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(result == 0){
            return false;
        }
        return true;
    }

    private void insertDelete(String query){
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cleanDatabase(){
        deleteData();
        insertData();
    }

    private void deleteData(){
        insertDelete("DELETE FROM klassen;");
        insertDelete("DELETE FROM adressen;");
        insertDelete("DELETE FROM personen;");
        insertDelete("DELETE FROM studenten;");
        insertDelete("DELETE FROM deelnemers;");
        insertDelete("DELETE FROM docenten;");
        insertDelete("DELETE FROM modules;");
        insertDelete("DELETE FROM lestijden;");
        insertDelete("DELETE FROM roosters;");
        insertQuery("SELECT setval(pg_get_serial_sequence('personen', 'persoonsnummer'), 1);");
    }

    private void insertData(){
        insertQuery("INSERT INTO klassen VALUES ('INF2D', '1-1-2014', '31-12-2014');");
        insertQuery("INSERT INTO klassen VALUES ('INF3D', '1-1-2015', '31-12-2015');");
        insertQuery("INSERT INTO klassen VALUES ('INF25D', '1-7-2014', '30-6-2015');");

        insertQuery("INSERT INTO adressen VALUES ('3295ga', '''s-Gravendeel', 'Geuldam');");
        insertQuery("INSERT INTO adressen VALUES ('2957 JB', 'Nieuw-Lekkerland', 'Watervluchtmolen');");
        insertQuery("INSERT INTO adressen VALUES ('3011WN', 'Rotterdam', 'Wijnhaven');");

        insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Paul', 'Wols', '', '7-8-1990', 'man', '3295GA', '16', '(06) - 0606');");
        insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Rudie', 'Smit', 'de', '1-1-1993', 'man', '2957JB', '45', '(06) - 21322121');");
        insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Tony', 'Busker', '', '1-1-1965', 'man', '3011WN', '107', '012.456789');");
        insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Wendy', 'IJperen', 'van', '1-1-1980', 'vrouw', '3011WN', '107', '012.21352547');");

        insertQuery("INSERT INTO studenten VALUES ('0812345', '2');");
        insertQuery("INSERT INTO studenten VALUES ('0854321', '3');");

        insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF2D');");
        insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF3D');");
        insertQuery("INSERT INTO deelnemers VALUES ('0854321', 'INF25D');");

        insertQuery("INSERT INTO docenten VALUES ('BUSAL.1', '4');");
        insertQuery("INSERT INTO docenten VALUES ('IJPEW.1', '5');");

        insertQuery("INSERT INTO modules VALUES ('INFANL', 'Analyse 8 - Advanced Database', 'BUSAL.1', '5-5-2014', '30-6-2014');");
        insertQuery("INSERT INTO modules VALUES ('INFSKL', 'Skills - Jaar 2', 'IJPEW.1', '5-5-2014', '30-6-2014');");

        insertQuery("INSERT INTO lestijden VALUES (1, '08:30');");
        insertQuery("INSERT INTO lestijden VALUES (2, '09:20');");
        insertQuery("INSERT INTO lestijden VALUES (3, '10:30');");
        insertQuery("INSERT INTO lestijden VALUES (4, '11:20');");
        insertQuery("INSERT INTO lestijden VALUES (5, '12:10');");
        insertQuery("INSERT INTO lestijden VALUES (6, '13:00');");
        insertQuery("INSERT INTO lestijden VALUES (7, '13:50');");
        insertQuery("INSERT INTO lestijden VALUES (8, '15:00');");
        insertQuery("INSERT INTO lestijden VALUES (9, '15:50');");
        insertQuery("INSERT INTO lestijden VALUES (10, '17:00');");
        insertQuery("INSERT INTO lestijden VALUES (11, '17:50');");
        insertQuery("INSERT INTO lestijden VALUES (12, '18:40');");
        insertQuery("INSERT INTO lestijden VALUES (13, '19:30');");
        insertQuery("INSERT INTO lestijden VALUES (14, '20:20');");
        insertQuery("INSERT INTO lestijden VALUES (15, '21:10');");

        insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFANL', 'BUSAL.1', 2, 4, '5-6-2014', 'WH104.1.105');");
        insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF25D', 'INFSKL', 'BUSAL.1', 4, 6, '5-6-2014', 'WH103.1.105');");
        insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFSKL', 'IJPEW.1', 4, 6, '5-6-2014', 'WH104.1.105');");
    }

    public void disconnect() throws SQLException {
        connection.close();
    }
}
