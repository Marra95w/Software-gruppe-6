import java.util.ArrayList;
import java.util.List;

// Denne klassen administrerer en liste med flere medlemmer og har metoder for å søke og sjekke dem
public class Medlemsregister {

    // Liste som lagrer alle medlemmene i systemet
    private List<medlem> alleMedlemmer;

    // Konstruktør: Oppretter en ny og tom liste når registeret startes
    public Medlemsregister() {
        this.alleMedlemmer = new ArrayList<>();
    }

    // Legger til et nytt medlem i registeret
    public void leggTilMedlem(medlem m) {
        alleMedlemmer.add(m);
    }

    // Henter ut listen med alle registrerte medlemmer
    public List<medlem> hentAlleMedlemmer() {
        return alleMedlemmer;
    }

    // Sjekker en liste med ID-er for å finne hvem som IKKE er registrert som medlem
    public List<Integer> hentIkkeMedlemmer(List<Integer> paameldteIder) {
        List<Integer> ikkeMedlemmer = new ArrayList<>();

        // Går gjennom hver ID i listen over påmeldte
        for (int id : paameldteIder) {
            boolean erMedlem = false;

            // Sjekker om ID-en finnes i medlemsregisteret
            for (medlem m : alleMedlemmer) {
                if (m.getIdMedlem() == id) {
                    erMedlem = true;
                    break;
                }
            }
            // Hvis personen ikke ble funnet i registeret, legges ID-en til i listen over ikke-medlemmer
            if (!erMedlem) {
                ikkeMedlemmer.add(id);
            }
        }
        return ikkeMedlemmer;
    }

    // Søker etter og returnerer et spesifikt medlem ved hjelp av medlemmets ID
    public medlem finnMedlemMedId(int id) {
        for (medlem m : alleMedlemmer) {
            if (m.getIdMedlem() == id) {
                return m; // Returnerer medlemmet hvis ID-en matcher
            }
        }
        return null; // Returnerer null hvis medlemmet ikke finnes
    }
}