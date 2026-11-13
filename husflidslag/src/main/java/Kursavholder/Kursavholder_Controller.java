package Kursavholder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;

import Kursavholder.KursavholderDTO;
import Kursavholder.KursavholderLogikk;

@RestController
@RequestMapping("/api/kursavholder")
@CrossOrigin(origins = " \"http://localhost:5173\"")
public class Kursavholder_Controller {

    private final KursavholderLogikk kursavholderLogikk;


    public Kursavholder_Controller(KursavholderLogikk kursavholderLogikk) {
        this.kursavholderLogikk = kursavholderLogikk;
    }

    //Henter alle kursavholdere (GET /Api/kursavholder)
@GetMapping
    public ResponseEntity<List<KursavholderDTO>> hentAlleKursavholder() {
        List<KursavholderDTO> list = kursavholderLogikk.hentAlleKursavholdere();
        return ResponseEntity.ok(list);
}

//Henter enkelte kursavholder(GET /Api/kursavholder/{id})
    @GetMapping("/{id}")
    public ResponseEntity<KursavholderDTO> hentAlleKursavholderById(@PathVariable int id ) {
        try {
            kursavholderLogikk.validerID(id);
            KursavholderDTO dto = kursavholderLogikk.hentKursavholder(id);
            if(dto != null) {
                return ResponseEntity.ok(dto);//200 OK
            }
            return ResponseEntity.notFound().build();
        }
        catch (IllegalArgumentException e ){
            return ResponseEntity.badRequest().build();
        }
    }

    //Legger til (Post /Api/kursavholder)
    @GetMapping
    public ResponseEntity<String> leggTilKursavholder(@RequestBody  Kursavholder nyDTO) {
        try{
            kursavholderLogikk.validerKursavholder(nyDTO.getNavn(), nyDTO.getAdresse_husflidslag());
            kursavholderLogikk.leggTilKursavholder(nyDTO.getNavn(), nyDTO.getAdresse_husflidslag());
            return ResponseEntity.ok("Suksess: Kursavholder ble lagret");
        }
        catch (IllegalArgumentException e ){
        return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Feil ved lagring: " + e.getMessage());
        }
    }
    // Oppdaterer kursavholder (PUT /api/kursavholder/{id})
    @PutMapping("/{id}")
    public ResponseEntity<String> oppdatereKursavholder(@PathVariable int id, @RequestBody  KursavholderDTO oppdatertDTO) {
        try {
            kursavholderLogikk.validerID(id);
            kursavholderLogikk.validerKursavholder(oppdatertDTO.getNavn(), oppdatertDTO.getAdresse());
            kursavholderLogikk.oppdaterKursavholder(id,oppdatertDTO.getNavn(), oppdatertDTO.getAdresse());
            return ResponseEntity.ok("Kursavholder med ID " + id + " ble oppdatert");
        }
        catch (IllegalArgumentException e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Feil ved oppdatering: " + e.getMessage());
        }
    }
    // Sletter kursavholder (DELETE /api/kursavholder/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> slettKursavholder(@PathVariable int id) {
        try {
            kursavholderLogikk.validerID(id);
            kursavholderLogikk.deleteKursavholder(id);
            return ResponseEntity.ok("Kursavholder med ID " + id + " ble slett");
        }
        catch (IllegalArgumentException e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Feil ved sletting av kursavhodler: " + e.getMessage());
        }
    }
    }
