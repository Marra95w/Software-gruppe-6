package Interface;

import java.time.LocalDateTime;

/*Bruker dette interfacet for å gjøre koden så løst som mulig
Østfolds husflidslag tilbyer forskjellige kurser så vi ønsker å kunne tilpasse dette
derfor har vi bestemt å bruke metodene som er relevant for alle og da kan heller tilpasse vi klassene i tilfelle
noe spesielt requests skulle dukke opp.*/

public interface Kurs {
    int getId();

    int getId();

    String getTittel();

    LocalDateTime getStartDatoTid();

    LocalDateTime getSluttDatoTid();

    String getKursavholder();

    double getPrisMedlem();

    double getPrisIkkeMedlem();

    String getBeskrivelse();

    int getMaksDeltakere();

    void setId(int id);

    void setTittel(String tittel);

    void setStartDatoTid(LocalDateTime startDatoTid);

    void setSluttDatoTid(LocalDateTime sluttDatoTid);

    void setKursavholder(String kursavholder);

    void setPrisMedlem(double prisMedlem);

    void setPrisIkkeMedlem(double prisIkkeMedlem);

    void setBeskrivelse(String beskrivelse);

    void setMaksDeltakere(int deltakere);

}
