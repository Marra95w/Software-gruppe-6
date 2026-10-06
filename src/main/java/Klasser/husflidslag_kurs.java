package Klasser;

import Interface.Kurs;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;

public class husflidslag_kurs implements Kurs {


    private String tittel;
    private LocalDateTime startDatoTid;
    private LocalDateTime sluttDatoTid;
    private int kursavholder;
    private double prisMedlem;
    private double prisIkkeMedlem;
    private String beskrivelse;
    private boolean tilgjengelig;
    private int maksDeltagere;

    public husflidslag_kurs( String tittel, LocalDateTime startDatoTid, LocalDateTime sluttDatoTid, int kursavholder,
                            double prisMedlem, double prisIkkeMedlem, String beskrivelse, boolean tilgjengelig,int maksDeltagere) {

        this.tittel = tittel;
        this.startDatoTid = startDatoTid;
        this.sluttDatoTid = sluttDatoTid;
        this.kursavholder = kursavholder;
        this.prisMedlem = prisMedlem;
        this.prisIkkeMedlem = prisIkkeMedlem;
        this.beskrivelse = beskrivelse;
        this.tilgjengelig = tilgjengelig;
        this.maksDeltagere = maksDeltagere;
    }

    public husflidslag_kurs() {
        super();
    }



    @Override
    public String getTittel() {
        return tittel;
    }

    @Override
    public LocalDateTime getStartDatoTid() {
        return startDatoTid;
    }

    @Override
    public LocalDateTime getSluttDatoTid() {
        return sluttDatoTid;
    }

    @Override
    public int getKursavholder() {
        return kursavholder;
    }

    @Override
    public double getPrisMedlem() {
        return prisMedlem;
    }

    @Override
    public double getPrisIkkeMedlem() {
        return prisIkkeMedlem;
    }

    @Override
    public String getBeskrivelse() {
        return beskrivelse;
    }

    @Override
    public void setTittel(String tittel) {
        this.tittel =tittel;
    }

    @Override
    public void setStartDatoTid(LocalDateTime startDatoTid) {
        this.startDatoTid = startDatoTid;
    }

    @Override
    public void setSluttDatoTid(LocalDateTime sluttDatoTid) {
        this.sluttDatoTid = sluttDatoTid;
    }

    @Override
    public void setKursavholder(int kursavholder) {
        this.kursavholder =kursavholder;
    }

    @Override
    public void setPrisMedlem(double prisMedlem) {
        this.prisMedlem = prisMedlem;
    }

    @Override
    public void setPrisIkkeMedlem(double prisIkkeMedlem) {
        this.prisIkkeMedlem = prisIkkeMedlem;
    }

    @Override
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public boolean isTilgjengelig() { //Endret fra erTilgjengelig
        return tilgjengelig;
    }


    @Override
    public int getMaksDeltakere() {
        return maksDeltagere;
    }

    @Override
    public void setMaksDeltakere(int deltakere) {
        if (maksDeltagere<=0){
            System.err.println("Ingen påmeldt! Vær så snill legg antall påmeldte for kurset!");
        }
        this.maksDeltagere = deltakere;
    }
}
