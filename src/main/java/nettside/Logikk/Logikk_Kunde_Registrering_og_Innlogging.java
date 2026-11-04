package nettside.Logikk;

import nettside.DTO.Innlogging_DTO;
import nettside.Klasser.medlem;
import io.github.cdimascio.dotenv.Dotenv;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Logikk_Kunde_Registrering_og_Innlogging {


    //dette er passord, brukernavn og serveradresse for databsen vår, som vi henter fra .env filen vår
    //for å beskytte disse mot uautorisert tilgang, selv om vårt github repo er privat
    Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .ignoreIfMalformed().load();
    String url = dotenv.get("DBA_URL2");
    String brukernavn = dotenv.get("DBA_USERNAME2");
    String passord = dotenv.get("DBA_PASSWORD2");

    //-----------------------------------------------------------------------------------------//


    //FUNKSJON FOR Å REGISTRERE KUNDER SOM MEDLEMMER/IKKE MEDLEMMER I SYSTEMET OG SJEKKE OPPLYSNINGER


    //-----------------------------------------------------------------------------------------//




    /*FUNKSJON SOM LEGGER INN MEDLEM I DATABASEN,DETTER ER INDIVIDEN SOM GJØR DET SELV FOR Å KUNNE
    REGISTRERE SEG PÅ KURSET SENERE*/


    public medlem leggTilMedlem(String navn,String etternavn,String epost,int medlem,int telefonnummer,String password_) {

        //Bruker BCypt som et alternativt for å skjerme passordet i databasen
        String passord_hash = BCrypt.hashpw(password_,BCrypt.gensalt());

        String sql = "INSERT INTO kunde_tabell (Navn, Etternavn, e_post, Medlem, telefonnummer,passord) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,navn);
            stmt.setString(2,etternavn);
            stmt.setString(3,epost);
            stmt.setInt(4,medlem);
            stmt.setInt(5,telefonnummer);
            stmt.setString(6,passord_hash);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    /*FUNKSJON SOM SJEKKER OM EPOST OG PASSORD MATCHER MOT EKSISTERENDE OPPLYSNINGER SOM LIGGER I DATABASEN*/

    public Innlogging_DTO sjekk_opplysninger(String epost, String password_) {

        String sql = "SELECT e_post,passord,Medlem FROM kunde_tabell WHERE e_post = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1,epost);


            try(ResultSet rs = stmt.executeQuery()){
                while (rs.next()){

                    /*sjekker vi med en if test om eposten man setter inn er iddentisk ettersom
                     vi oppdaget at databasen ser ikke på dette, så mente at dette kan være
                     et sikkerhetsrisiko, men samtidig ønsker vi å teste innloggingen for å sjekke om eposten matcher passord
                      for å kunne gjekjenne/godkjenne en bruker*/

                    String e_post_tastet_av_bruker = rs.getString("e_post");
                    if (e_post_tastet_av_bruker.equals(epost)) {


                        String skjermet_password = rs.getString("passord");

                        //Kode testing som sjekker om passord tastet(password_) matcher med passord i databasen(skjermet_password)
                        if (BCrypt.checkpw(password_, skjermet_password)) {
                            System.out.println("Godkjent!");

                            int medlemskap = rs.getInt("Medlem");

                            Innlogging_DTO godkjent = new Innlogging_DTO();
                            godkjent.setEpost(e_post_tastet_av_bruker);
                            godkjent.setMedlemstatus(medlemskap); // denne vil hjelpe med å bestemme om nettsiden åpnes for admin eller medlem/ikke medlem
                            godkjent.setPassord(null); // vi fant ut at det er best at dette forblir null for å ikke gjøre koden synlig i prossesen

                            return godkjent;


                        } else {
                            System.out.println("Noe gikk galt sjekk opplysningene!");
                            return null;
                        }

                        /*else tilhører e_post_tastet_av_bruker if testen som sjekker bokstav forskejellen mellom støre og små
                        * bokstav for å unngå å kompromittere kontoer, måtte bare flytte den hit for at den skulle funke*/
                    } else {
                        System.out.println("Sjekk epost eller passord!");
                        return null;
                    }
                }

            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }


}
