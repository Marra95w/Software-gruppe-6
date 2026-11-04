package nettside.Controller;

import nettside.Klasser.husflidslag_kurs;
import nettside.Klasser.kurs_paamelding;
import nettside.Logikk.Database_Kurs_Logikk;
import nettside.Logikk.Database_paamelding_logikk;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/* Bruker Spring Boot sin API for å opprette en koblingspunkt mllom backend og frontend
* frontend må bare fetche disse for å kunne synliggjøre alle kurs men også få med noe
* funksjoner som både admin og medlemm kan utnytte*/

@RestController
@RequestMapping("/id")
@CrossOrigin(origins = "http://localhost:5173")
public class Kurs_Controller {

private  Database_Kurs_Logikk kursLogikk = new Database_Kurs_Logikk();
private Database_paamelding_logikk paameldingLogikk = new Database_paamelding_logikk();


@GetMapping("Henter_spesifikk_kurs_fra_databasen/{id}")
    public husflidslag_kurs hente_kurs_fra_databasen(@PathVariable("id") int id){

    return kursLogikk.hent_kurs_fra_db(id);
}


@GetMapping
public List<husflidslag_kurs> hent_all_kurs(){

    return kursLogikk.hent_alle_kurs_fra_db();

}



@PostMapping("opprett_ny_kurs_gjennom_frontend")
    public husflidslag_kurs lag_ny_kurs(@RequestBody husflidslag_kurs nykurs){

return kursLogikk.sett_kurs_i_db(nykurs.getTittel(),nykurs.getStartDatoTid(),nykurs.getSluttDatoTid(),nykurs.getKursavholder(),nykurs.getPrisMedlem(),nykurs.getPrisIkkeMedlem(), nykurs.getBeskrivelse());
}

@DeleteMapping("Fjerner_kurs")
    public husflidslag_kurs slett_kurs(@PathVariable String tittel){

    return kursLogikk.slett_kurs_fra_db(tittel);
}

@PutMapping("Oppdatering_av_kurs")
    public husflidslag_kurs oppdater_kurs(@PathVariable int id,@RequestBody husflidslag_kurs oppdatertkurs){

    return kursLogikk.oppdatere_kurs_i_db(oppdatertkurs.getTittel(),oppdatertkurs.getStartDatoTid(),oppdatertkurs.getSluttDatoTid(),oppdatertkurs.getKursavholder(),oppdatertkurs.getPrisMedlem(),oppdatertkurs.getPrisIkkeMedlem(),oppdatertkurs.getBeskrivelse(),oppdatertkurs.getId());
}


@PostMapping("melder_en_kund_i_et_kurs")
    public kurs_paamelding meld_opp_i_kurs(@RequestBody kurs_paamelding oppmelding){

    return paameldingLogikk.registrer_kunde_i_kurs(oppmelding.getKundeId(),oppmelding.getKursId(),oppmelding.getPris_fakturert());
}


}
