package Kursavholder;

public class Kursavholder {

    private final int Kursavholder_id;
    private String navn;
    private int Adresse_Kursvavholder_Id;
    private String Adresse_husflidslag;



    public Kursavholder(int Kursavholder_id, String navn,  int Adresse_Kursvavholder_Id, String Adresse_husflidslag) {
        this.Kursavholder_id = Kursavholder_id;
        this.navn = navn;
        this.Adresse_Kursvavholder_Id = Adresse_Kursvavholder_Id;
        this.Adresse_husflidslag = Adresse_husflidslag;
    }

    public int Kursavholder_id() {
        return this.Kursavholder_id;
    }

    public String getNavn() {
        return this.navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getAdresse_Kursvavholder_Id() {
        return Adresse_Kursvavholder_Id;
    }

    public void setAdresse_Kursvavholder_Id(int adresseID) {
        //this.Adresse_Kursvavholder_Id = Adresse_Kursvavholder_Id;
    }

    public String getAdresse_husflidslag() {
        return Adresse_husflidslag;
    }

    public void setAdresse_husflidslag(String adresse) {
        this.Adresse_husflidslag = Adresse_husflidslag;
    }

    @Override
    public String toString() {
        return "Kursavholder{" +
                "Kursavholder_id=" + Kursavholder_id +
                ", navn='" + navn + '\'' +
                ", Adresse_Kursvavholder_Id=" + Adresse_Kursvavholder_Id +
                ", Adresse_husflidslag='" + Adresse_husflidslag + '\'' +
                '}';
    }
}
