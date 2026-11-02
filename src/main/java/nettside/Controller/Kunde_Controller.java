package nettside.Controller;

import nettside.DTO.Innlogging_DTO;
import nettside.Klasser.medlem;
import nettside.Logikk.Logikk_Kunde_Registrering_og_Innlogging;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="http://localhost:5173")
public class Kunde_Controller {

    private  Logikk_Kunde_Registrering_og_Innlogging kunde_logikk = new Logikk_Kunde_Registrering_og_Innlogging();


    @PostMapping("innlogging")
    public Innlogging_DTO sjekk_innlogging(@RequestBody Innlogging_DTO innloggingDto){

        return kunde_logikk.sjekk_opplysninger(innloggingDto.getEpost(),innloggingDto.getPassord());
    }


    @PostMapping("Registrere_ny_bruker")
    public medlem ny_registrering(@RequestBody medlem ny_registrering){

        return kunde_logikk.leggTilMedlem(ny_registrering.getNavn(),ny_registrering.getEtternavn(),ny_registrering.getEpost(),ny_registrering.getMedlem(),ny_registrering.getTelefonnummer(),ny_registrering.getPassord());

    }

}
