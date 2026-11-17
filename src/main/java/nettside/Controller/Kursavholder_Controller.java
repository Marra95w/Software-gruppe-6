package nettside.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import nettside.DTO.KursavholderDTO;
import nettside.Service.KursavholderService;


@RestController
@RequestMapping("/api/kursavholder")
@CrossOrigin(origins =  "http://localhost:5173")
public class Kursavholder_Controller {

    private final KursavholderService kursavholderService;


    public Kursavholder_Controller(KursavholderService kursavholderService) {
        this.kursavholderService = kursavholderService;
    }

    //Henter alle kursavholdere (GET /Api/kursavholder)
@GetMapping
    public ResponseEntity<List<KursavholderDTO>> hentAlleKursavholder() {
        List<KursavholderDTO> list = kursavholderService.hentAlleKursavholdere();
        return ResponseEntity.ok(list);
}

//Henter enkelte kursavholder(GET /Api/kursavholder/{id})
    @GetMapping("/{id}")
    public ResponseEntity<KursavholderDTO> hentAlleKursavholderById(@PathVariable int id ) {
        try {
            kursavholderService.validerID(id);
            KursavholderDTO dto = kursavholderService.hentKursavholder(id);
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
    @PostMapping
    public ResponseEntity<String> leggTilKursavholder(@RequestBody  KursavholderDTO nyDTO) {
        try{
            kursavholderService.validerKursavholder(nyDTO.getNavn(), nyDTO.getAdresse());
            kursavholderService.leggTilKursavholder(nyDTO.getNavn(), nyDTO.getAdresse());
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
            kursavholderService.validerID(id);
            kursavholderService.validerKursavholder(oppdatertDTO.getNavn(), oppdatertDTO.getAdresse());
            kursavholderService.oppdaterKursavholder(id,oppdatertDTO.getNavn(), oppdatertDTO.getAdresse());
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
            kursavholderService.validerID(id);
            kursavholderService.deleteKursavholder(id);
            return ResponseEntity.ok("Kursavholder med ID " + id + " ble slettet");
        }
        catch (IllegalArgumentException e ){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Feil ved sletting av kursavhodler: " + e.getMessage());
        }

    }
    }
