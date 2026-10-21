import Klasser.husflidslag_kurs;
import Logikk.Database_Kurs_Logikk;
import Klasser.adresse_husflidslag;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hei");

        husflidslag_kurs kurs = new husflidslag_kurs(
                "Java for nybegynnere",
                LocalDateTime.of(2026, 10, 1, 9, 0),  // 1. okt 2026 kl. 09:00
                LocalDateTime.of(2026, 10, 1, 16, 0), // 1. okt 2026 kl. 16:00
                101,
                1500.0,
                2500.0,
                "Lær det grunnleggende i Java-programmering.",true,20);
        kurs.setPrisMedlem(1600);
        System.out.println("Følgende kurs: "+kurs.getTittel()+" starter: "+kurs.getStartDatoTid()+" slutter: "+kurs.getSluttDatoTid()+" medlem pris: "+kurs.getPrisMedlem()+" ikke medlem pris: "+kurs.getPrisIkkeMedlem()+" beskrivelse: "+kurs.getBeskrivelse());


        Database_Kurs_Logikk kursLogikk = new Database_Kurs_Logikk();

        System.out.println("Prøver å hente kurs fra databasen...");

        // 2. Kall metoden med en ID du vet finnes i databasen (f.eks. ID 1)
        husflidslag_kurs funnetKurs = kursLogikk.hent_kurs_fra_db(2);

        // kall metoden med tilsvarende id for å riktig kombinere adrresen med kurset
        adresse_husflidslag adresse_for_funnet_kurs = kursLogikk.hent_adresse_fra_db(2);





        // 3. Sjekk om metoden returnerte et kurs eller null (fant ingenting)
        if (funnetKurs != null) {
            System.out.println(" Suksess! Kurs ble funnet og pushed inn i klassen.");

            // 4. Print ut verdiene for å verifisere at de ble fylt riktig
            System.out.println("ID: " + funnetKurs.getId());
            System.out.println("Tittel: " + funnetKurs.getTittel());
            System.out.println("Kursavholder: " + funnetKurs.getKursavholder());
            System.out.println("Pris for medlemmer: " + funnetKurs.getPrisMedlem() + " kr");
            System.out.println("Beskrivelse: " + funnetKurs.getBeskrivelse());
            System.out.println("Adresse: "+adresse_for_funnet_kurs.getAdresse_husflidslag());



        } else {
            System.out.println(" Fant ikke kurset. Metoden returnerte null.");
            System.out.println("Sjekk at ID-en stemmer og at databasekoblingen er aktiv.");
        }
    }
}




