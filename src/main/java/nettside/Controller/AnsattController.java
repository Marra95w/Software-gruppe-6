package nettside.Controller;
import nettside.DTO.AnsattDTO;
import nettside.Database.DatabaseKobling;
import nettside.Service.Ansattregister;
import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.util.List;

// Gjør klassen til en REST-kontroller som kan motta HTTP-forespørsler og returnere data (som JSON til frontend)
@RestController

// Bestemmer at alle URL-er i denne kontrolleren starter med /ansatt
@RequestMapping("/ansatt")

// Åpner opp for CORS slik at frontend (på port 5173) kan kommunisere med backend uten at nettleseren blokkerer det
@CrossOrigin(origins = "http://localhost:5173")
public class AnsattController {

    // Oppretter en forekomst av logikk-klassen for å kunne hente ut data
    private Ansattregister ansattLogikk = new Ansattregister();

    // Knytter denne metoden til en HTTP GET-forespørsel på /ansatt/hente_ansatte
    @GetMapping("/hente_ansatte")
    public List<AnsattDTO> henteAnsatte() {

        // Åpner en databasetilkobling i en try-with-resources-blokk som automatisk lukker tilkoblingen etter bruk
        try (Connection conn = DatabaseKobling.getConnection()) {

            // Sender tilkoblingen videre til logikk-klassen for å hente ut alle ansatte, og returnerer listen
            return ansattLogikk.hentAlleAnsatte(conn);

        } catch (Exception e) {

            // Fanger opp eventuelle feil som oppstår, skriver feilmeldingen til konsollen, og returnerer null
            e.printStackTrace();
            return null;

        }
    }

    // Hent én ansatt basert på ID (GET)
    @GetMapping("/{id}")
    public AnsattDTO hentAnsattMedId(@PathVariable int id) {
        try (Connection conn = DatabaseKobling.getConnection()) {
            return ansattLogikk.finnAnsattMedId(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Legg til ny ansatt (POST)
    @PostMapping("/legg_til")
    public boolean leggTilAnsatt(@RequestBody AnsattDTO nyAnsatt) {
        try (Connection conn = DatabaseKobling.getConnection()) {
            return ansattLogikk.leggTilAnsatt(
                    conn,
                    nyAnsatt.getFornavn(),
                    nyAnsatt.getEtternavn(),
                    nyAnsatt.getEpost(),
                    nyAnsatt.getTelefonnummer(),
                    nyAnsatt.getStillingstype(),
                    nyAnsatt.getBilder()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Oppdater en eksisterende ansatt (PUT)
    @PutMapping("/oppdater/{id}")
    public boolean oppdaterAnsatt(@PathVariable int id, @RequestBody AnsattDTO oppdatertAnsatt) {
        try (Connection conn = DatabaseKobling.getConnection()) {
            return ansattLogikk.oppdaterAnsatt(
                    conn,
                    id,
                    oppdatertAnsatt.getFornavn(),
                    oppdatertAnsatt.getEtternavn(),
                    oppdatertAnsatt.getEpost(),
                    oppdatertAnsatt.getTelefonnummer(),
                    oppdatertAnsatt.getStillingstype(),
                    oppdatertAnsatt.getBilder()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Slett ansatt basert på ID (DELETE)
    @DeleteMapping("/slett/{id}")
    public boolean slettAnsatt(@PathVariable int id) {
        try (Connection conn = DatabaseKobling.getConnection()) {
            return ansattLogikk.slettAnsattMedId(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}