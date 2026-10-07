public class medlem {

    private final int idMedlem;
    private String navn;
    private String epost;

    // Konstruktør
    public medlem(int idMedlem, String navn, String epost) {
        this.idMedlem = idMedlem;
        this.navn = navn;
        this.epost = epost;
    }

    // Gettere og settere
    public int getIdMedlem() {
        return idMedlem;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    // Utskriftsformat
    @Override
    public String toString() {
        return "Id: " + idMedlem + " | Navn: " + navn + " | E-post: " + epost;
    }
}