// Denne klassen fungerer som en mal for et enkelt medlem og holder på dataene
public class medlem {

    // Variabler som lagrer medlemmets informasjon
    private final int idMedlem;
    private String navn;
    private String etternavn;
    private String epost;
    private int medlem;
    private int telefonnummer;

    // Konstruktør: Brukes til å fylle inn verdier når vi lager et nytt medlem-objekt
    public medlem(int idMedlem, String navn, String etternavn, String epost, int medlem, int telefonnummer) {
        this.idMedlem = idMedlem;
        this.navn = navn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.medlem = medlem;
        this.telefonnummer = telefonnummer;
    }

    // Henter ut ID-en (kan ikke endres fordi den er 'final')
    public int getIdMedlem() {
        return idMedlem;
    }

    // Henter fornavnet
    public String getNavn() {
        return navn;
    }

    // Endrer fornavnet
    public void setNavn(String navn) {
        this.navn = navn;
    }

    // Henter etternavnet
    public String getEtternavn() {
        return etternavn;
    }

    // Endrer etternavnet
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    // Henter e-postadressen
    public String getEpost() {
        return epost;
    }

    // Endrer e-postadressen
    public void setEpost(String epost) {
        this.epost = epost;
    }

    // Henter medlemsstatus
    public int getMedlem() {
        return medlem;
    }

    // Endrer medlemsstatus
    public void setMedlem(int medlem) {
        this.medlem = medlem;
    }

    // Henter telefonnummeret
    public int getTelefonnummer() {
        return telefonnummer;
    }

    // Endrer telefonnummeret
    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    // Gjør om objektet til en tekststreng slik at det er lett å printe ut i konsollen
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