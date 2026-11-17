package DTO;

public class AnsattDTO {
    private int idAnsatt;
    private String fornavn;
    private String etternavn;
    private String epost;
    private int telefonnummer;
    private String stillingstype;
    private String bilder;

    public AnsattDTO(int idAnsatt, String fornavn, String etternavn, String epost, int telefonnummer, String stillingstype, String bilder) {
        this.idAnsatt = idAnsatt;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
        this.stillingstype = stillingstype;
        this.bilder = bilder;
    }

    public int getIdAnsatt() { return idAnsatt; }
    public String getFornavn() { return fornavn; }
    public String getEtternavn() { return etternavn; }
    public String getEpost() { return epost; }
    public int getTelefonnummer() { return telefonnummer; }
    public String getStillingstype() { return stillingstype; }
    public String getBilder() { return bilder; }

    public void setFornavn(String fornavn) { this.fornavn = fornavn; }
    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }
    public void setEpost(String epost) { this.epost = epost; }
    public void setTelefonnummer(int telefonnummer) { this.telefonnummer = telefonnummer; }
    public void setStillingstype(String stillingstype) { this.stillingstype = stillingstype; }
    public void setBilder(String bilder) { this.bilder = bilder; }

    @Override
    public String toString() {
        return "AnsattDTO [ID=" + idAnsatt + ", Navn=" + fornavn + " " + etternavn +
                ", Epost=" + epost + ", Tlf=" + telefonnummer + ", Stilling=" + stillingstype + "]";
    }
}