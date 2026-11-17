package nettside.DTO;

/* Vi bruker DTO for å for å begrense info vi ønsker å motta i tillegg skiller vi fra det interne modellen. Derfor har vi bare
* epost og passord som sjekkes mot databasen. medlemstatus er bare internt for å kunne vise kurs basert på hvem som er innlogget
* altså sjekker om personen er medlem eller ikke.*/

public class Innlogging_DTO {

    private String epost;
    private String passord;
    private int medlemstatus;

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public int getMedlemstatus(){
        return medlemstatus;
    }

    public void setMedlemstatus(int medlemstatus){
        this.medlemstatus = medlemstatus;
    }

}
