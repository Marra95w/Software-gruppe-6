import Klasser.husflidslag_kurs;

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
        kurs.setPrisMedlem(1500);
        System.out.println("Følgende kurs: "+kurs.getTittel()+" starter: "+kurs.getStartDatoTid()+" slutter: "+kurs.getSluttDatoTid()+" medlem pris: "+kurs.getPrisMedlem()+" ikke medlem pris: "+kurs.getPrisIkkeMedlem()+" beskrivelse: "+kurs.getBeskrivelse());


    }
}
