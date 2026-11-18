package DTO;

public class AnsattDTO {
    // Feltvariabler som lagrer all informasjon om en ansatt
    private int idAnsatt;
    private String fornavn;
    private String etternavn;
    private String epost;
    private int telefonnummer;
    private String stillingstype;
    private String bilder;

    // Konstruktør som brukes til å opprette en ny ansatt med alle verdier på én gang
    public AnsattDTO(int idAnsatt, String fornavn, String etternavn, String epost, int telefonnummer, String stillingstype, String bilder) {
        this.idAnsatt = idAnsatt;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
        this.stillingstype = stillingstype;
        this.bilder = bilder;
    }

    // Getters: Lar andre klasser hente ut verdiene fra objektet
    public int getIdAnsatt() { return idAnsatt; }
    public String getFornavn() { return fornavn; }
    public String getEtternavn() { return etternavn; }
    public String getEpost() { return epost; }
    public int getTelefonnummer() { return telefonnummer; }
    public String getStillingstype() { return stillingstype; }
    public String getBilder() { return bilder; }

    // Setters: Lar andre klasser endre verdiene i objektet hvis det trengs
    public void setFornavn(String fornavn) { this.fornavn = fornavn; }
    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }
    public void setEpost(String epost) { this.epost = epost; }
    public void setTelefonnummer(int telefonnummer) { this.telefonnummer = telefonnummer; }
    public void setStillingstype(String stillingstype) { this.stillingstype = stillingstype; }
    public void setBilder(String bilder) { this.bilder = bilder; }

    // Gjør objektet om til en lesbar tekststreng (nyttig for utskrift og testing)
    @Override
    public String toString() {
        return "AnsattDTO [ID=" + idAnsatt + ", Navn=" + fornavn + " " + etternavn +
                ", Epost=" + epost + ", Tlf=" + telefonnummer + ", Stilling=" + stillingstype + "]";
    }
}