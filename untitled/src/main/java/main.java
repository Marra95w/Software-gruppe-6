import java.util.Arrays;
import java.util.List;

public class main {
    public static void main(String[] args) {

        // 1. Oppretter medlem-objekter
        medlem medlem1 = new medlem(1, "Rigmor Kalnes", "rigmor@kalnes.no");
        medlem medlem2 = new medlem(2, "Ola Nordmann", "ola.nordmann@epost.no");
        medlem medlem3 = new medlem(3, "Kari Hansen", "kari.hansen@epost.no");

        // 2. Oppretter registeret og legger til medlemmene
        Medlemsregister register = new Medlemsregister();
        register.leggTilMedlem(medlem1);
        register.leggTilMedlem(medlem2);
        register.leggTilMedlem(medlem3);

        // 3. SPØRRING: Henter og skriver ut alle medlemmer
        System.out.println("=== ALLE REGISTRERTE MEDLEMMER ===");
        List<medlem> alle = register.hentAlleMedlemmer();
        for (medlem m : alle) {
            System.out.println(m);
        }

        // 4. SPØRRING: Sjekker liste med påmeldte ID-er for hvem som IKKE er medlemmer (f.eks. ID 1, 2, 99)
        List<Integer> paameldteMedlemIder = Arrays.asList(1, 2, 99);
        List<Integer> ikkeMedlemmer = register.hentIkkeMedlemmer(paameldteMedlemIder);

        System.out.println("\n=== PÅMELDTE ID-ER SOM IKKE ER MEDLEMMER ===");
        for (int id : ikkeMedlemmer) {
            System.out.println("ID " + id + " er ikke registrert som medlem.");
        }
    }
}
