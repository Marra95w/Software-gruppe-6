package DTO;

public class medlem_DTO {

    private String navn;
    private String etternavn;

    public medlem_DTO(String navn, String etternavn) {
        this.navn = navn;
        this.etternavn = etternavn;
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
}
