package Kursavholder;

import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class KursavholderLogikkTest {
    private Connection connection;
    private KursavholderLogikk kursavholderLogikk;

    @BeforeEach
    public void setup() throws SQLException {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        connection = DriverManager.getConnection(url, username, password);

        //Viktig!! Her så skrur vi av auto-commit slik at vi kan angre endringene etter testen
        connection.setAutoCommit(false);

        kursavholderLogikk = new KursavholderLogikk();
    }
    @AfterEach
    public void tearDown() throws SQLException {
        // Her så lukker vi tilkoblingen etter hver test slik at ting er ryddige
        if (connection != null && !connection.isClosed()) {
            try { //Her så sørger vi at autoCommit er av før vi ruller tilbake
                if (connection.getAutoCommit()) {
                    connection.setAutoCommit(false);
                }
                connection.rollback();//Angrer alt som skjedde under testen
            }finally {
                connection.close();//Her så lukker tilkoblingen uansett
            }
        }
    }
    @Test
    public void tesHentAlleKursavholdere(){
        List<KursavholderDTO> alleKursavholdere = kursavholderLogikk.hentAlleKursavholdere();

        // Assert: sjekker om at vi fikk et resultat og at listen ikke er tom
        assertNotNull(alleKursavholdere, "Listen skal ikke være null");
        assertFalse(alleKursavholdere.isEmpty(), "Listen bør inneholde minst en kursavholder");

        System.out.println("Test fullført! Fant " +  alleKursavholdere.size() + " kursavholdere i databasen");
    }

    @Test
    public void testHentKursavholder(){
        List<KursavholderDTO> alleKursavholdere = kursavholderLogikk.hentAlleKursavholdere();
        assertFalse(alleKursavholdere.isEmpty(), "Må ha minst en kursavholder for å teste");

        //int id = alleKursavholdere.get(0).Kursavholder_id();
        int id = alleKursavholdere.get(0).getId();
        String navn = alleKursavholdere.get(0).getNavn();

        // Her så henter vi den spesifikke kursavholderen vi vil hente.
        KursavholderDTO hentedeKursavholderDTO = kursavholderLogikk.hentKursavholder(id);


        assertNotNull(hentedeKursavholderDTO, "Kursavholder skal ikke være null");
        assertEquals(id, hentedeKursavholderDTO.getId(), "ID-en matcher");
        assertEquals(navn, hentedeKursavholderDTO.getNavn(), "Navnet matcher");

        System.out.println("Test fullført! Fant kursavholder: " +  hentedeKursavholderDTO.getNavn());
    }

    @Test
    public void testLeggTilKursavholder() {
        String testNavn = "Husflid Test";
        String testAdresse = "Test 123";

        kursavholderLogikk.leggTilKursavholder(testNavn, testAdresse);

        //Henter ut fra via DTO-metoden siden det er dette kontrollen bruker
        boolean sjekkerOmDetFinnes = kursavholderLogikk.hentAlleKursavholdere().stream().anyMatch(kursavholderDTO -> kursavholderDTO.getNavn().equals(testNavn) && kursavholderDTO.getAdresse().equals(testAdresse));
        assertTrue(sjekkerOmDetFinnes, "Her så sjekker vi den raden vi har laggt finnes");
    }

    @Test
    public void testDeleteKursavholder() {
        String testNavn = " Test: OsloTest";
        String testAdresse = "Test: lønneve123";

        //Her så legger vi legger vi til en kursavholder som vi kan da teste på om vi kan slette de
        kursavholderLogikk.leggTilKursavholder(testNavn, testAdresse);

        //Sjekker: finner id-en til den lagte kursavholderen som vi skulle teste sletting på ved å sjekke listen
        List<KursavholderDTO> alleDtoer = kursavholderLogikk.hentAlleKursavholdere();
        KursavholderDTO opprettetKursavholderDTO = alleDtoer.stream().filter(kursavholderDTO -> kursavholderDTO.getNavn().equals(testNavn)).findFirst().orElse(null);

        assertNotNull(opprettetKursavholderDTO, "Kursavholderen må finnes i listen før vi sletter den");
        int idSomSkalSlettes = opprettetKursavholderDTO.id;

        //Utfører slettingen
        kursavholderLogikk.deleteKursavholder(idSomSkalSlettes);
        boolean sjekkerOmDenFinnes = kursavholderLogikk.hentAlleKursavholdere().stream().anyMatch(kursavholderDTO -> kursavholderDTO.id ==idSomSkalSlettes);
        assertFalse(sjekkerOmDenFinnes, "kursavholderen skal være slettet og finnes ikke lenger");
        System.out.println("Testen er fullført! slettet kursavholder med ID: " +  idSomSkalSlettes);
    }

    @Test
    public void testUpdateKursavholder() {
        String gammeltNavn = "Gammelt Husflid";
        String gammeltAdresse = "gammeltAdresse123";
        //Legger til en rad først for å ha noe å oppdatere
        kursavholderLogikk.leggTilKursavholder( gammeltNavn, gammeltAdresse);
        //Finner Id-en til den raden vi nettop la til ved å sjekke DTO-listen
        List<KursavholderDTO> alleDtoer = kursavholderLogikk.hentAlleKursavholdere();
        KursavholderDTO opprettetKursavholderDTO = alleDtoer.stream().filter(kursavholderDTO -> kursavholderDTO.getNavn().equals(gammeltNavn)).findFirst().orElse(null);
        assertNotNull(opprettetKursavholderDTO, "Kursavholderen må finnes i listen før vi sletter den");
        int idSomSkalOppdateres = opprettetKursavholderDTO.id;

        //kjører metoden som vil teste. oppdatere navn og adresse
        String nyttNavn = "Nytt Oppdatert Husflid";
        String nyttAdresse = "nyttAdresse123";
        kursavholderLogikk.oppdaterKursavholder(idSomSkalOppdateres, nyttNavn, nyttAdresse);
        //Henter ut den oppdaterte kursavholder raden via DTO-en deretter sjekker om den har endret seg ved oppdateringen vi har gjort
        KursavholderDTO oppdatertKursavholder = kursavholderLogikk.hentKursavholder(idSomSkalOppdateres);
        assertNotNull(oppdatertKursavholder, "Kursavholderen skal finnes i databasen");
        assertEquals(nyttNavn, oppdatertKursavholder.getNavn(), "Navnet skal være oppdatert til den nye");
        assertEquals(nyttAdresse, oppdatertKursavholder.getAdresse(), "Adressen skal være oppdattert til den nye");
        System.out.println("Testen er fullført! Oppdatert kursavholder med ID: " +  idSomSkalOppdateres);

    }
    @Test
    @DisplayName("LeggTilKursavholder: Kaster IllegalArgumentException ved tomt navn")
    public void testLeggTilKursavholder_tomNavn() {
        String tomtNavn = "    ";
        String tomtAdresse = "    ";
        assertThrows(IllegalArgumentException.class, () -> {kursavholderLogikk.validerKursavholder(tomtNavn, tomtAdresse);
        }, "skal kaste feil ved tomt navn");
    }
    @Test
    @DisplayName("validering: Kaster IllegalArgumentException ved negativ ID")
    public void testVedOperasjonerSomTrengerI(){
        int ugyldigId = -1;

        assertThrows(IllegalArgumentException.class, ()->{kursavholderLogikk.validerID(ugyldigId);}
                , "Skal kaste feil ved negativ ID ");

    }

}
