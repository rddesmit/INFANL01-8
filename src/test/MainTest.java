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
        assertTrue(database.insertQuery("INSERT INTO klassen VALUES ('INF2D', '1-1-2014', '31-12-2014');"));
        assertTrue(database.insertQuery("INSERT INTO klassen VALUES ('INF3D', '1-1-2015', '31-12-2015');"));
        assertTrue(database.insertQuery("INSERT INTO klassen VALUES ('INF25D', '1-7-2014', '30-6-2015');"));
    }

    @Test
    public void testAddressen(){
        assertTrue(database.insertQuery("INSERT INTO adressen VALUES ('3295ga', '''s-Gravendeel', 'Geuldam');"));
        assertTrue(database.insertQuery("INSERT INTO adressen VALUES ('2957 JB', 'Nieuw-Lekkerland', 'Watervluchtmolen');"));
        assertFalse(database.insertQuery("INSERT INTO adressen VALUES ('295721', 'Nieuw-Lekkerland', 'Watervluchtmolen');"));
        assertTrue(database.insertQuery("INSERT INTO adressen VALUES ('3011WN', 'Rotterdam', 'Wijnhaven');"));
    }

    @Test
    public void testPersonen(){
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Paul', 'Wols', '', '7-8-1990', 'man', '3295GA', '16', '(06) - 0606');"));
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Rudie', 'Smit', 'de', '1-1-1993', 'man', '2957JB', '45', '(06) - 21322121');"));
        assertFalse(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Piet', 'Verdriet', '', '1-1-1900', 'man', '2957JB', '45', '(06) - aaaa ');"));
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Tony', 'Busker', '', '1-1-1965', 'man', '3011WN', '107', '012.456789');"));
        assertTrue(database.insertQuery("INSERT INTO personen (voornaam, achternaam, tussenvoegsel, geboorte_datum, geslacht, postcode, huisnummer, telefoonnummer) VALUES ('Wendy', 'IJperen', 'van', '1-1-1980', 'vrouw', '3011WN', '107', '012.21352547');"));

    }

    @Test
    public void testStudenten(){
        assertTrue(database.insertQuery("INSERT INTO studenten VALUES ('0812345', '1');"));
        assertTrue(database.insertQuery("INSERT INTO studenten VALUES ('0854321', '2');"));
        assertFalse(database.insertQuery("INSERT INTO studenten VALUES ('0811111', '1');"));
    }

    @Test
    public void testDeelnemers(){
        assertTrue(database.insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF2D');"));
        assertTrue(database.insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF3D');"));
        assertFalse(database.insertQuery("INSERT INTO deelnemers VALUES ('0812345', 'INF25D');"));
        assertTrue(database.insertQuery("INSERT INTO deelnemers VALUES ('0854321', 'INF25D');"));
        assertFalse(database.insertQuery("INSERT INTO deelnemers VALUES ('0854321', 'INF2D');"));
        assertFalse(database.insertQuery("INSERT INTO deelnemers VALUES ('0854321', 'INF3D');"));
    }

    @Test
    public void testDocenten(){
        assertTrue(database.insertQuery("INSERT INTO docenten VALUES ('BUSAL.1', '4');"));
        assertFalse(database.insertQuery("INSERT INTO docenten VALUES ('BUSAL.2', '4');"));
        assertTrue(database.insertQuery("INSERT INTO docenten VALUES ('IJPEW.1', '5');"));
    }

    @Test
    public void testModules(){
        assertTrue(database.insertQuery("INSERT INTO modules VALUES ('INFANL', 'Analyse 8 - Advanced Database', 'BUSAL.1', '5-5-2014', '30-6-2014');"));
        assertTrue(database.insertQuery("INSERT INTO modules VALUES ('INFSKL', 'Skills - Jaar 2', 'IJPEW.1', '5-5-2014', '30-6-2014');"));
        assertFalse(database.insertQuery("INSERT INTO modules VALUES ('INFDEV01', 'Development 1 - HTML, CSS en Javascript', 'BUSAL.1', '5-5-2014', '31-7-2014');"));
    }

    @Test
    public void testLestijden(){
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (1, '08:30');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (2, '09:20');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (3, '10:30');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (4, '11:20');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (5, '12:10');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (6, '13:00');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (7, '13:50');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (8, '15:00');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (9, '15:50');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (10, '17:00');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (11, '17:50');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (12, '18:40');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (13, '19:30');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (14, '20:20');"));
        assertTrue(database.insertQuery("INSERT INTO lestijden VALUES (15, '21:10');"));
    }

    @Test
    public void testRoosters(){
        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF25D', 'INFANL', 'BUSAL.1', 1, 3, '5-7-2014', 'WH103.1.105');"));
        assertTrue(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFANL', 'BUSAL.1', 2, 4, '5-6-2014', 'WH104.1.105');"));
        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF3D', 'INFSKL', 'BUSAL.1', 3, 5, '5-6-2014', 'WH103.1.105');"));
        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFSKL', 'IJPEW.1', 1, 3, '5-6-2014', 'WH103.1.105');"));
        assertFalse(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF3D', 'INFSKL', 'IJPEW.1', 1, 3, '5-6-2014', 'WH104.1.105');"));
        assertTrue(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF25D', 'INFSKL', 'BUSAL.1', 4, 6, '5-6-2014', 'WH103.1.105');"));
        assertTrue(database.insertQuery("INSERT INTO roosters (klasnaam, modulecode, docent, beginuur, einduur, datum, lokaal) VALUES ('INF2D', 'INFSKL', 'IJPEW.1', 4, 6, '5-6-2014', 'WH104.1.105');"));
    }

    @After
    public void teardown() throws Exception {
        database.disconnect();

    }
}
