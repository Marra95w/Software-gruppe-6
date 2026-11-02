package nettside.Klasser;

import nettside.Interface.Medlem;

/*Denne klassen implementerer Medlem Interfacet, men vi kunne egentlig hadde brukt et mer
* globalt navn siden Admin er registrert på samme måte i samme tabell noe som kan skape forvirringer men ikke
* funksjonell risiko. Men det hadde vært mer oversiktlig.*/

public class medlem implements Medlem {

    private int idMedlem;
    private String navn;
    private String etternavn;
    private String epost;
    private int medlem;
    private int telefonnummer;
    private String password_;

    // Konstruktør


    public medlem(int idMedlem, String navn, String etternavn, String epost, int medlem, int telefonnummer) {
        this.idMedlem = idMedlem;
        this.navn = navn;
        this.etternavn = etternavn;
        this.epost = epost;
        this.medlem = medlem;
        this.telefonnummer = telefonnummer;
    }




    public medlem() {
        super();
    }

    @Override
    public int getIdMedlem() {
        return idMedlem;
    }

    @Override
    public void setIdMedlem(int idMedlem) {
        this.idMedlem = idMedlem;
    }

    @Override
    public String getNavn() {
        return navn;
    }

    @Override
    public void setNavn(String navn) {
        this.navn=navn;
    }

    @Override
    public String getEtternavn() {
        return etternavn;
    }

    @Override
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    @Override
    public String getEpost() {
        return epost;
    }

    @Override
    public void setEpost(String epost) {
        this.epost=epost;
    }

    @Override
    public int getMedlem() {
        return medlem;
    }

    @Override
    public void setMedlem(int medlem) {
        this.medlem = medlem;
    }

    @Override
    public int getTelefonnummer() {
        return telefonnummer;
    }

    @Override
    public void setTelefonnummer(int telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    @Override
    public String getPassord() {
        return password_;
    }

    @Override
    public void setPassord(String password_) {
        this.password_=password_;
    }
}
