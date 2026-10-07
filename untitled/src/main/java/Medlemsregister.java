import java.util.ArrayList;
import java.util.List;

public class Medlemsregister {

    // Liste som husker alle registrerte medlemmer
    private List<medlem> alleMedlemmer;

    public Medlemsregister() {
        this.alleMedlemmer = new ArrayList<>();
    }

    // Legger til et nytt medlem i registeret
    public void leggTilMedlem(medlem m) {
        alleMedlemmer.add(m);
    }

    // Spørring 1: Henter alle medlemmer
    public List<medlem> hentAlleMedlemmer() {
        return alleMedlemmer;
    }

    // Spørring 2: Henter personer som IKKE er medlemmer
    // (Finner ID-er som ikke finnes i alleMedlemmer-listen)
    public List<Integer> hentIkkeMedlemmer(List<Integer> paameldteIder) {
        List<Integer> ikkeMedlemmer = new ArrayList<>();

        for (int id : paameldteIder) {
            boolean erMedlem = false;
            for (medlem m : alleMedlemmer) {
                if (m.getIdMedlem() == id) {
                    erMedlem = true;
                    break;
                }
            }
            if (!erMedlem) {
                ikkeMedlemmer.add(id);
            }
        }
        return ikkeMedlemmer;
    }

    // Spørring 3: Finn et bestemt medlem via ID
    public medlem finnMedlemMedId(int id) {
        for (medlem m : alleMedlemmer) {
            if (m.getIdMedlem() == id) {
                return m;
            }
        }
        return null;
    }
}