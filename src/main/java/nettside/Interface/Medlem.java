package nettside.Interface;

/*Bruker interface for å kunne opprette medlemmer, ikke medlemmer og administartor,
* dette hjelper med å holde koden løst men samtidig opprette de forskjellige rollene basert på medlemstatus,
* medlemstatus må vi være forsiktig med. Ellers som sagt sparer man tid ved å implementere disse ferdiglagt
* funskjonene */

public interface Medlem {


    int getIdMedlem();
    void setIdMedlem(int idMedlem);

    String getNavn();
    void setNavn(String navn);

    String getEtternavn();
    void setEtternavn(String etternavn);

    String getEpost();
    void setEpost(String epost);

    int getMedlem();
    void setMedlem(int medlem);

    int getTelefonnummer();
    void setTelefonnummer(int telefonnummer);

    String getPassord();
    void setPassord(String passord_);

}
