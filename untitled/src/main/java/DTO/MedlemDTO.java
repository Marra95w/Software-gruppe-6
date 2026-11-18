package DTO;

public class MedlemDTO {
    // Feltvariabler som lagrer informasjon om et medlem
    private int id;
    private String navn;
    private String etternavn;
    private String epost;
    private int medlemStatus;
    private int telefonnummer;
    private String passord;

    // Full konstruktør som setter alle verdiene når et nytt MedlemDTO-objekt opprettes
    public MedlemDTO(int id, String navn, String etternavn, String epost, int medlemStatus, int telefonnummer, String passord) {
        this.id = id;
        this.navn = navn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.medlemStatus = medlemStatus;
        this.telefonnummer = telefonnummer;
        this.passord = passord;
    }

    // Gettere: Lar andre klasser hente ut verdiene fra medlems-objektet
    public int getId() { return id; }
    public String getNavn() { return navn; }
    public String getEtternavn() { return etternavn; }
    public String getEpost() { return epost; }
    public int getMedlemStatus() { return medlemStatus; }
    public int getTelefonnummer() { return telefonnummer; }
    public String getPassord() { return passord; }

    // Gjør objektet om til en kort og fin tekststreng (navn, etternavn og e-post)
    @Override
    public String toString() {
        return navn + " " + etternavn + " (" + epost + ")";
    }
}