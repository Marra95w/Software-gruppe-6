package no.hiof.controllers;
import DTO.AnsattDTO;
import services.Ansattlogikk;
import config.Databasekobling;
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
    private Ansattlogikk ansattLogikk = new Ansattlogikk();

    // Knytter denne metoden til en HTTP GET-forespørsel på /ansatt/hente_ansatte
    @GetMapping("/hente_ansatte")
    public List<AnsattDTO> henteAnsatte() {

        // Åpner en databasetilkobling i en try-with-resources-blokk som automatisk lukker tilkoblingen etter bruk
        try (Connection conn = Databasekobling.kobleTil()) {

            // Sender tilkoblingen videre til logikk-klassen for å hente ut alle ansatte, og returnerer listen
            return ansattLogikk.hentAlleAnsatte(conn);

        } catch (Exception e) {

            // Fanger opp eventuelle feil som oppstår, skriver feilmeldingen til konsollen, og returnerer null
            e.printStackTrace();
            return null;

        }
    }
}