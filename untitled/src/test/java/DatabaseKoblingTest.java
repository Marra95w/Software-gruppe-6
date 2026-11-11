import config.Databasekobling;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseKoblingTest {

    // Forteller JUnit at denne metoden er en enhetstest som skal kjøres
    @Test
    public void testDatabaseKobling() {
        // Bruker Databasekobling-klassen for å hente tilkoblingen i en try-with-resources-blokk som lukker den etter bruk
        try (Connection conn = Databasekobling.kobleTil()) {

            // Sjekker at tilkoblingsobjektet ikke er null (at vi faktisk fikk et svar)
            assertNotNull(conn, "Tilkoblingen skal ikke være null");
            // Sjekker at databasetilkoblingen er åpen og aktiv
            assertFalse(conn.isClosed(), "Tilkoblingen skal være åpen");
            System.out.println("Koblet til databasen!");

        } catch (SQLException ex) {
            // Feiler testen og skriver ut en feilmelding hvis tilkoblingen feiler
            fail("Klarte ikke å koble: " + ex.getMessage());
        }
    }
}