public class Kursavholder {
    private final int kursavholderId;
    private String navn;


    public Kursavholder(int kursavholderId, String navn) {
        this.kursavholderId=kursavholderId;
        this.navn=navn;
    }

    public int getKursavholderId() {
        return kursavholderId;
    }
    public String getNavn() {
        return navn;
    }
    public void setNavn(String navn) {
        this.navn = navn;
    }

    @Override
    public String toString() {
        return "ID: " + kursavholderId +
                " Navn: "  + navn;
    }
}

