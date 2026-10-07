package Interface;

import java.time.LocalDateTime;

public interface Kurs {

    String getTittel();
    LocalDateTime getStartDatoTid();
    LocalDateTime getSluttDatoTid();
    String getKursavholder();
    double getPrisMedlem();
    double getPrisIkkeMedlem();
    String getBeskrivelse();
    int getMaksDeltakere();

    void setTittel(String tittel);
    void setStartDatoTid(LocalDateTime startDatoTid );
    void setSluttDatoTid(LocalDateTime sluttDatoTid);
    void setKursavholder(String kursavholder);
    void setPrisMedlem(double prisMedlem);
    void setPrisIkkeMedlem(double prisIkkeMedlem);
    void setBeskrivelse(String beskrivelse);
    void setMaksDeltakere(int deltakere);

}
