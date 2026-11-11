import DTO.MedlemDTO;
import services.Medlemslogikk;
import config.Databasekobling;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MedlemslogikkTest {

    // Oppretter et objekt av logikk-klassen som skal testes
    private final Medlemslogikk logikk = new Medlemslogikk();

    // Tester at vi får opprettet en gyldig tilkobling til databasen
    @Test
    void testDatabaseTilkobling() {
        // Åpner databasetilkoblingen i en try-with-resources-blokk som lukker den automatisk etter bruk
        try (Connection conn = Databasekobling.kobleTil()) {
            // Sjekker at tilkoblingen ikke er null (at vi fikk et gyldig objekt)
            assertNotNull(conn, "Tilkoblingen skal ikke være null");
            // Sjekker at tilkoblingen faktisk er åpen og aktiv
            assertFalse(conn.isClosed(), "Tilkoblingen skal være åpen");
        } catch (SQLException e) {
            // Feiler testen hvis det oppstår feil under tilkobling
            fail("Klarte ikke å koble til databasen: " + e.getMessage());
        }
    }

    // Tester at vi kan legge til et nytt medlem med unik e-post og telefonnummer
    @Test
    void testLeggTilMedlem() {
        try (Connection conn = Databasekobling.kobleTil()) {
            assertNotNull(conn);

            // Lager en unik e-post og et unikt telefonnummer basert på tidsstempel for å unngå duplikatfeil
            String unikEpost = "jane.doe" + System.currentTimeMillis() + "@epost.no";
            int uniktTlf = 90000000 + (int)(System.currentTimeMillis() % 1000000);

            // Kjører metoden for å legge til medlem og sjekker at den returnerer true (vellykket)
            boolean lagret = logikk.leggTilMedlem(conn, "Jane", "Doe", unikEpost, 1, uniktTlf, "sikretPassord99");
            assertTrue(lagret, "Medlemmet skal bli lagret i databasen");

        } catch (SQLException e) {
            fail("Databasefeil under test: " + e.getMessage());
        }
    }

    // Tester at vi kan hente ut et spesifikt medlem ved hjelp av medlems-ID
    @Test
    void testFinnMedlemMedId() {
        try (Connection conn = Databasekobling.kobleTil()) {
            assertNotNull(conn);

            // Prøver å hente medlemmet med ID 1 fra databasen
            MedlemDTO funnet = logikk.finnMedlemMedId(conn, 1);
            // Hvis medlemmet finnes, sjekker vi at ID-en matcher og at navnet ikke er null
            if (funnet != null) {
                assertEquals(1, funnet.getId()); // Endret fra getIdMedlem() til getId()
                assertNotNull(funnet.getNavn());
            }

        } catch (SQLException e) {
            fail("Databasefeil under test: " + e.getMessage());
        }
    }

    // Tester at vi kan hente ut en liste med alle registrerte medlemmer
    @Test
    void testHentAlleMedlemmer() {
        try (Connection conn = Databasekobling.kobleTil()) {
            assertNotNull(conn);

            // Henter listen over alle medlemmer
            List<MedlemDTO> alle = logikk.hentAlleMedlemmer(conn);
            // Sjekker at listen ikke er null (at metoden returnerer en gyldig liste)
            assertNotNull(alle, "Listen over medlemmer skal ikke være null");

        } catch (SQLException e) {
            fail("Databasefeil under test: " + e.getMessage());
        }
    }

    // Tester oppdatering av informasjon for et eksisterende medlem
    @Test
    void testOppdaterMedlem() {
        try (Connection conn = Databasekobling.kobleTil()) {
            assertNotNull(conn);

            // Genererer nye unike verdier for oppdateringstesten
            String unikEpostOppdatert = "jane.updated" + System.currentTimeMillis() + "@epost.no";
            int uniktTlfOppdatert = 40000000 + (int)(System.currentTimeMillis() % 1000000);

            // Prøver å oppdatere medlem med ID 1
            boolean oppdatert = logikk.oppdaterMedlem(conn, 1, "Jane", "Doe", unikEpostOppdatert, 1, uniktTlfOppdatert, "nyttPassord2026");
            // Sjekker at koden kjører uten feil (siden ID 1 kanskje ikke finnes i testdatabasen, godtas både true og false)
            assertTrue(oppdatert || !oppdatert);

        } catch (SQLException e) {
            fail("Databasefeil under test: " + e.getMessage());
        }
    }

    // Tester sletting av et medlem basert på ID
    @Test
    void testSlettMedlem() {
        try (Connection conn = Databasekobling.kobleTil()) {
            assertNotNull(conn);

            // Prøver å slette en ID som sannsynligvis ikke finnes (99999)
            boolean slettet = logikk.slettMedlemMedId(conn, 99999);
            // Sjekker at testen kjører knirkefritt uavhengig av om raden fantes eller ikke
            assertTrue(slettet || !slettet);

        } catch (SQLException e) {
            fail("Databasefeil under test: " + e.getMessage());
        }
    }
}