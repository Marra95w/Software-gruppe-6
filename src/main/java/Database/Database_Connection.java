package Database;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.*;
import java.util.*;

/*Dette er bare selve koblingen av databasen med Java koden, men mye av dette blir implemetert i Databse_Kurs_Logikk
* klassen og Database_paamelding_logikk klassen. */

public class Database_Connection {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed().load();
        String url = dotenv.get("DBA_URL2");
        String brukernavn = dotenv.get("DBA_USERNAME2");
        String passord = dotenv.get("DBA_PASSWORD2");

        if (url == null) {
            System.err.println("Kunne ikke lese DB_URL fra .env-filen. Sjekk filstien!");
            return;}

        try {
            Connection connection = DriverManager.getConnection(url, brukernavn, passord);

            String sql = " SELECT * FROM kurs_tabell";



            try (PreparedStatement prepStatement = connection.prepareStatement(sql);
                 ResultSet resultSet = prepStatement.executeQuery()) {

                // Creating an object of DateTimeFormatter class
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss a");


                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    LocalDateTime startDatoTid = resultSet.getObject("Start_dato_tid",LocalDateTime.class);
                    LocalDateTime sluttDatoTid = resultSet.getObject("Slutt_dato_tid",LocalDateTime.class);
                    System.out.println("Start Dato: " + startDatoTid + " | Slutt Dato: " + sluttDatoTid);

                    // Formatting LocalDateTime to string
                    String start_date_Time_String =startDatoTid.format(formatter);
                    String slutt_Date_Time_String = sluttDatoTid.format(formatter);

                    System.out.println("Id: "+id+" Start Dato: "+start_date_Time_String+" Slutt Dato: "+slutt_Date_Time_String);




                }











            }        catch (Exception e) {
                System.err.println(e.getMessage());
            }




        }catch (Exception e){
            System.err.println(e.getMessage());}


        }


    }




