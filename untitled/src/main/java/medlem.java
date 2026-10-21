public class medlem {

    private final int idMedlem;
    private String navn;
    private String etternavn;
    private String epost;
    private int medlem;
    private int telefonnummer;

    // Konstruktør


    public medlem(int idMedlem, String navn, String etternavn, String epost, int medlem, int telefonnummer) {
        this.idMedlem = idMedlem;
        this.navn = navn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.medlem = medlem;
        this.telefonnummer = telefonnummer;
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

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public int getMedlem() {
        return medlem;
    }

    public void setMedlem(int medlem) {
        this.medlem = medlem;
    }

    public int getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // Utskriftsformat

    @Override
    public String toString() {
        return "medlem{" +
                "idMedlem=" + idMedlem +
                ", navn='" + navn + '\'' +
                ", etternavn='" + etternavn + '\'' +
                ", epost='" + epost + '\'' +
                ", medlem=" + medlem +
                ", telefonnummer=" + telefonnummer +
                '}';
    }
}