package Interface;

import java.time.LocalDateTime;

public interface Kurs {

    String getTittel();
    LocalDateTime getStartDatoTid();
    LocalDateTime getSluttDatoTid();
    int getKursavholder();
    double getPrisMedlem();
    double getPrisIkkeMedlem();
    String getBeskrivelse();
    int getMaksDeltakere();

    void setTittel(String tittel);
    void setStartDatoTid(LocalDateTime startDatoTid );
    void setSluttDatoTid(LocalDateTime sluttDatoTid);
    void setKursavholder(int kursavholder);
    void setPrisMedlem(double prisMedlem);
    void setPrisIkkeMedlem(double prisIkkeMedlem);
    void setBeskrivelse(String beskrivelse);
    void setMaksDeltakere(int deltakere);

}
