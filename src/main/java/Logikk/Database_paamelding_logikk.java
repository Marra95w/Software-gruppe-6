package Logikk;
import Klasser.kurs_paamelding;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


//OBS DENNE KODEN ER MENT FOR BRUK AV ADMIN I ØSTFOLDS HUDSFLISLAG.
//Dette betyr at admin skla kunne håndterer manuelt registrering ved bruk av prepared statement
//Det som følger er enkel spørringer som sletter,velger og oppretter påmelding
//Denne klasssen hådterer logikken for spørringer mellom databasen og mysql spørringer

public class Database_paamelding_logikk {

    //dette er passord, brukernavn og serveradresse for databsen vår, som vi henter fra .env filen vår
    //for å beskytte disse mot uautorisert tilgang, selv om vårt github repo er privat
    Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .ignoreIfMalformed().load();
    String url = dotenv.get("DBA_URL2");
    String brukernavn = dotenv.get("DBA_USERNAME2");
    String passord = dotenv.get("DBA_PASSWORD2");



    //-----------------------------------------------------------------------------------------//


    //METODER FOR Å REGISTRERE MEDLEMMER PÅ KURS


    //-----------------------------------------------------------------------------------------//

    //METODE FOR Å REGISTRERE EN MEDLEM I KURS
    public kurs_paamelding registrer_kunde_i_kurs (int kundeId, int kursId,double pris_fakturert){

        String sql = "INSERT INTO kurs_paamelding VALUES ( ?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,brukernavn,passord)){

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1,kundeId);
            statement.setInt(2,kursId);
            statement.setDouble(3,pris_fakturert);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }



    //METODE FOR Å SLETTE  SPESIFIKK PÅMELDING
    public kurs_paamelding slette_påmelding_i_db (int kursId){

        String sql = "DELETE FROM kurs_paamelding WHERE kursId = ?";

        try(Connection connection = DriverManager.getConnection(url,brukernavn,passord)){

            PreparedStatement statement =connection.prepareStatement(sql);

            statement.setInt(1,kursId);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    //METODE FOR Å SLETTE  SPESIFIKK PÅMELDING
    public kurs_paamelding velge_påmelding_i_db (int kursId){

        String sql = "SELECT * FROM kurs_paamelding WHERE kursId = ?";

        try(Connection connection = DriverManager.getConnection(url,brukernavn,passord)){

            PreparedStatement statement =connection.prepareStatement(sql);

            statement.setInt(1,kursId);


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}






