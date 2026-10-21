import java.util.ArrayList;
import java.util.List;

public class Medlemsregister {

    private List<medlem> alleMedlemmer;

    public Medlemsregister() {
        this.alleMedlemmer = new ArrayList<>();
    }

    public void leggTilMedlem(medlem m) {
        alleMedlemmer.add(m);
    }

    public List<medlem> hentAlleMedlemmer() {
        return alleMedlemmer;
    }

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

    public medlem finnMedlemMedId(int id) {
        for (medlem m : alleMedlemmer) {
            if (m.getIdMedlem() == id) {
                return m;
            }
        }
        return null;
    }
}