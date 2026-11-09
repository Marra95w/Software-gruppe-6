package no.hiof.controllers;
import DTO.AnsattDTO;
import services.Ansattlogikk;
import config.Databasekobling; // <-- Nå henter vi den fra config-pakken
import org.springframework.web.bind.annotation.*;
import java.sql.Connection;
import java.util.List;

@RestController
@RequestMapping("/ansatt")
@CrossOrigin(origins = "http://localhost:5173")
public class AnsattController {

    private Ansattlogikk ansattLogikk = new Ansattlogikk();

    @GetMapping("/hente_ansatte")
    public List<AnsattDTO> henteAnsatte() {
        try (Connection conn = Databasekobling.kobleTil()) {
            return ansattLogikk.hentAlleAnsatte(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}