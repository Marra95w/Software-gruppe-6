package nettside.DTO;


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
