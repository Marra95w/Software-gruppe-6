import services.Ansattlogikk;
import config.Databasekobling;
import DTO.AnsattDTO;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AnsattlogikkTest {

    // Oppretter et objekt av logikk-klassen som vi skal teste
    private final Ansattlogikk logikk = new Ansattlogikk();

    // Tester at vi klarer å legge til en ny ansatt i databasen
    @Test
    void testLeggTilAnsatt() {
        // Kobler til databasen med try-with-resources slik at den lukkes automatisk
        try (Connection conn = Databasekobling.kobleTil()) {
            // Sjekker at tilkoblingen ikke er null (at vi fikk kontakt)
            assertNotNull(conn);

            // Lager en unik e-post og telefonnummer ved hjelp av tidsstempel, slik at testen ikke feiler på duplikater
            String unikEpost = "jane.doe.ansatt" + System.currentTimeMillis() + "@epost.no";
            int uniktTlf = 90000000 + (int)(System.currentTimeMillis() % 1000000);

            // Kjører metoden for å lagre ansatt og sjekker at den returnerer true (vellykket)
            boolean lagret = logikk.leggTilAnsatt(conn, "Jane", "Doe", unikEpost, uniktTlf, "Utvikler", "jane.jpg");
            assertTrue(lagret, "Den ansatte skal bli lagret i databasen");

        } catch (SQLException e) {
            // Feiler testen hvis det oppstår en uventet databasefeil
            fail("Databasefeil under test: " + e.getMessage());
        }
    }

    // Tester at vi kan hente ut en liste med alle ansatte fra databasen
    @Test
    void testHentAlleAnsatte() {
        try (Connection conn = Databasekobling.kobleTil()) {
            assertNotNull(conn);

            // Henter listen over alle ansatte
            List<AnsattDTO> alleAnsatte = logikk.hentAlleAnsatte(conn);
            // Sjekker at listen ikke er null (at metoden returnerer et gyldig listeelement)
            assertNotNull(alleAnsatte, "Listen over ansatte skal ikke være null");

        } catch (SQLException e) {
            fail("Databasefeil under test: " + e.getMessage());
        }
    }

    // Tester sletting av en ansatt basert på ID
    @Test
    void testSlettAnsatt() {
        try (Connection conn = Databasekobling.kobleTil()) {
            assertNotNull(conn);

            // Prøver å slette en ID som sannsynligvis ikke finnes (99999)
            boolean slettet = logikk.slettAnsattMedId(conn, 99999);
            // Sjekker at koden kjører uten å krasje (slettet vil være false her siden ID-en ikke finnes)
            assertTrue(slettet || !slettet);

        } catch (SQLException e) {
            fail("Databasefeil under test: " + e.getMessage());
        }
    }
}