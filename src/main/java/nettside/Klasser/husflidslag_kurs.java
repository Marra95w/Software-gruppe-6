package nettside.Klasser;

import nettside.Interface.Kurs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* Denne klassen bruker vi for å kunne fremvise kurs for kunden. Ved å gå til Databse_Kurs_Logikk,
* kan man se at det finnes funksjoner som gjør det lett å redigere og velge kurs */

public class husflidslag_kurs implements Kurs {

    private int id;
    private String tittel;
    private LocalDateTime startDatoTid;
    private LocalDateTime sluttDatoTid;
    private int kursavholder;
    private double prisMedlem;
    private double prisIkkeMedlem;
    private String beskrivelse;
    private boolean tilgjengelig;
    private int maksDeltagere;
    private String adresse;
    private String kursavholderNavn;

    public husflidslag_kurs( String tittel, LocalDateTime startDatoTid, LocalDateTime sluttDatoTid, int kursavholder,
                            double prisMedlem, double prisIkkeMedlem, String beskrivelse, boolean tilgjengelig,int maksDeltagere, String adresse,String kursavholderNavn) {

        this.tittel = tittel;
        this.startDatoTid = startDatoTid;
        this.sluttDatoTid = sluttDatoTid;
        this.kursavholder = kursavholder;
        this.prisMedlem = prisMedlem;
        this.prisIkkeMedlem = prisIkkeMedlem;
        this.beskrivelse = beskrivelse;
        this.tilgjengelig = tilgjengelig;
        this.maksDeltagere = maksDeltagere;
        this.adresse = adresse;
        this.kursavholderNavn = kursavholderNavn;
    }

    public husflidslag_kurs() {
        super();
    }

    @Override
    public int getId() {return id;}

    @Override
    public void setId(int id) {this.id = id;}

    @Override
    public String getTittel() {
        return tittel;
    }

    @Override
    public LocalDateTime getStartDatoTid() {
        return startDatoTid;
    }

    @Override
    //Local Date Time formaten var for uoversiktig så vi bytte til en mer oversektlig som kan vises for brukeren
    //Gjennom oracle sin dokumentasjon for JAVA klarte jeg å finne formatteringen for norsk språket
    public String getStartDatoTidString(){

        DateTimeFormatter norsk_Formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy 'kl.' HH:mm", Locale.forLanguageTag("no-NO"));

        return startDatoTid.format(norsk_Formatter);}

    @Override
    public LocalDateTime getSluttDatoTid() {
        return sluttDatoTid;
    }

    @Override
    //Local Date Time formaten var for uoversiktig så vi bytte til en mer oversektlig som kan vises for brukeren
    //Gjennom oracle sin dokumentasjon for JAVA klarte jeg å finne formatteringen for norsk språket
    public String getSluttDatoTidString(){

        DateTimeFormatter norsk_Formatter = DateTimeFormatter.ofPattern("d. MMMM yyyy 'kl.' HH:mm", Locale.forLanguageTag("no-NO"));

        return sluttDatoTid.format(norsk_Formatter);}

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

    /* Desverre på grunn av dårlig plannlegging og lite tid, denne funksjonenn må vi fikse senere*/
    public boolean erTilgjengelig() {
        return tilgjengelig;
    }



    @Override
    public int getMaksDeltakere() {
        return maksDeltagere;
    }

    @Override
    public void setMaksDeltakere(int deltakere) {
        if (deltakere<=0){
            System.err.println("Ingen påmeldt! Vær så snill legg antall påmeldte for kurset!");
        }
        this.maksDeltagere = deltakere;
    }

    @Override
    public String getAdresse() {
        return adresse;
    }

    /* Desverre på grunn av dårlig plannlegging og lite tid, denne funksjonenn må vi fikse senere*/
    @Override
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String getKursavholderNavn() {
        return kursavholderNavn;
    }

    @Override
    public void setKursavholderNavn(String kursavholderNavn) {
        this.kursavholderNavn = kursavholderNavn;
    }
}



