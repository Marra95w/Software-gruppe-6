package Klasser;

public class kurs_paamelding {

    private  int id;
    private int kundeId;
    private int kursId;
    private double pris_fakturert;

    public kurs_paamelding(int id, int kundeId, int kursId, double pris_fakturert) {
        this.id = id;
        this.kundeId = kundeId;
        this.kursId = kursId;
        this.pris_fakturert = pris_fakturert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKundeId() {
        return kundeId;
    }

    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    public double getPris_fakturert() {
        return pris_fakturert;
    }

    public void setPris_fakturert(double pris_fakturert) {
        this.pris_fakturert = pris_fakturert;
    }
}
