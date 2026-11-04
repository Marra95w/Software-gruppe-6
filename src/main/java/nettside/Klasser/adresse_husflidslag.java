package nettside.Klasser;

/*Formålet med klassen er å gi muligheten til administartoren å endre, slette, oppdatere eller legge
* ny adresse for en spesifikk husflidslag i Østfold. Dette kommer vi til å se i Databse_Kurs_Logikk klassen
* med alle spesifikke funksjonene.*/

public class adresse_husflidslag {

    private int id;
    private int Adresse_Kursvavholder_Id;
    private String Adresse_husflidslag;

    public adresse_husflidslag(int id, int adresse_Kursvavholder_Id, String adresse_husflidslag) {
        this.id = id;
        this.Adresse_Kursvavholder_Id = adresse_Kursvavholder_Id;
        this.Adresse_husflidslag = adresse_husflidslag;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdresse_Kursvavholder_Id() {
        return Adresse_Kursvavholder_Id;
    }

    public void setAdresse_Kursvavholder_Id(int adresse_Kursvavholder_Id) {
        this.Adresse_Kursvavholder_Id = adresse_Kursvavholder_Id;
    }

    public String getAdresse_husflidslag() {
        return Adresse_husflidslag;
    }

    public void setAdresse_husflidslag(String adresse_husflidslag) {
        this.Adresse_husflidslag = adresse_husflidslag;
    }
}
