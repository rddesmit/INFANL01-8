package test;

import main.Database;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by Rudie on 20-5-14.
 */
public class MainTest{

    Database database;

    @Before
    public void setup() throws Exception {
        database = new Database();
        database.connect();
    }

    @Test
    public void testKlassen() {
        database.cleanDatabase();
        assertTrue(database.insertQuery("INSERT INTO klassen VALUES ('INF4D', '1-1-2014', '31-12-2014');"));
        assertTrue(database.insertQuery("INSERT INTO klassen VALUES ('INF3E', '1-1-2015', '31-12-2015');"));
        assertTrue(database.insertQuery("INSERT INTO klassen VALUES ('INF26D', '1-7-2014', '30-6-2015');"));
    }

    @Test
    public void testAddressen(){
        database.cleanDatabase();
        assertTrue(database.insertQuery("INSERT INTO adressen VALUES ('3296ga', '''s-Gravendeel', 'Geuldam');"));
        assertTrue(database.insertQuery("INSERT INTO adressen VALUES ('2947 JB', 'Nieuw-Lekkerland', 'Watervluchtmolen');"));
        assertFalse(database.insertQuery("INSERT INTO adressen VALUES ('295721', 'Nieuw-Lekkerland', 'Watervluchtmolen');"));
        assertTrue(database.insertQuery("INSERT INTO adressen VALUES ('3012WN', 'Rotterdam', 'Wijnhaven');"));
    }

    @Test
    public void testPersonen(){
        database.cleanDatabase();
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Paul', 'Wols', '', '7-8-1990', 'man', '3295GA', '16', '(06) - 0606');"));
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Rudie', 'Smit', 'de', '1-1-1993', 'man', '2957JB', '45', '(06) - 21322121');"));
        assertFalse(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Piet', 'Verdriet', '', '1-1-1900', 'man', '2957JB', '45', '(06) - aaaa ');"));
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Tony', 'Busker', '', '1-1-1965', 'man', '3011WN', '107', '012.456789');"));
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Wendy', 'IJperen', 'van', '1-1-1980', 'vrouw', '3011WN', '107', '012.21352547');"));

    }

    @Test
    public void testStudenten(){
        database.cleanDatabase();
        assertTrue(database.insertQuery("INSERT INTO studenten VALUES ('0812344', '4');"));
        assertTrue(database.insertQuery("INSERT INTO studenten VALUES ('0854322', '5');"));
        assertFalse(database.insertQuery("INSERT INTO studenten VALUES ('0811112', '4');"));
    }

    @Test
    public void testDeelnemers(){
        database.cleanDatabase();
        database.insertQuery("INSERT INTO klassen VALUES ('INF4D', '1-1-2016', '31-12-2017');");
        database.insertQuery("INSERT INTO klassen VALUES ('INF3E', '1-1-2018', '31-12-2019');");
        database.insertQuery("INSERT INTO klassen VALUES ('INF25E', '1-7-2015', '30-6-2016');");

        assertTrue(database.insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF4D');"));
        assertTrue(database.insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF3E');"));
        assertFalse(database.insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF25E');"));
        assertTrue(database.insertQuery("INSERT INTO deelnemers VALUES ('0854321', 'INF25E');"));
        assertFalse(database.insertQuery("INSERT INTO deelnemers VALUES ('0854321', 'INF4D');"));
    }

    @Test
    public void testDocenten(){
        database.cleanDatabase();
        assertTrue(database.insertQuery("INSERT INTO docenten VALUES ('BUSAL.3', '2');"));
        assertFalse(database.insertQuery("INSERT INTO docenten VALUES ('BUSAL.4', '2');"));
        assertTrue(database.insertQuery("INSERT INTO docenten VALUES ('IJPEW.3', '3');"));
    }

    @Test
    public void testModules(){
        database.cleanDatabase();
        database.insertQuery("INSERT INTO docenten VALUES ('BUSAL.3', '2');");
        database.insertQuery("INSERT INTO docenten VALUES ('IJPEW.3', '3');");

        assertTrue(database.insertQuery("INSERT INTO modules VALUES ('INFANLX', 'Analyse 8 - Advanced Database', 'BUSAL.3', '5-5-2014', '30-6-2014');"));
        assertTrue(database.insertQuery("INSERT INTO modules VALUES ('INFSKLX', 'Skills - Jaar 2', 'IJPEW.3', '5-5-2014', '30-6-2014');"));
        assertFalse(database.insertQuery("INSERT INTO modules VALUES ('INFDEV01X', 'Development 1 - HTML, CSS en Javascript', 'BUSAL.3', '5-5-2014', '31-7-2014');"));
    }

    @Test
    public void testRoosters(){
        database.cleanDatabase();
        database.insertQuery("INSERT INTO modules VALUES ('INFANLX', 'Analyse 8 - Advanced Database', 'BUSAL.1', '5-5-2015', '30-6-2015');");
        database.insertQuery("INSERT INTO modules VALUES ('INFSKLX', 'Skills - Jaar 2', 'IJPEW.1', '5-5-2015', '30-6-2015');");

        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF25D', 'INFANLX', 'BUSAL.1', 1, 3, '5-7-2015', 'WH103.1.105');"));
        assertTrue(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFANLX', 'BUSAL.1', 2, 4, '5-6-2015', 'WH104.1.105');"));
        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF3D', 'INFSKLX', 'BUSAL.1', 3, 5, '5-6-2015', 'WH103.1.105');"));
        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFSKLX', 'IJPEW.1', 1, 3, '5-6-2015', 'WH103.1.105');"));
        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF3D', 'INFSKLX', 'IJPEW.1', 1, 3, '5-6-2015', 'WH104.1.105');"));
        assertTrue(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF25D', 'INFSKLX', 'BUSAL.1', 4, 6, '5-6-2015', 'WH103.1.105');"));
        assertTrue(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFSKLX', 'IJPEW.1', 4, 6, '5-6-2015', 'WH104.1.105');"));
    }

    @After
    public void teardown() throws Exception {
        database.disconnect();

    }
}
