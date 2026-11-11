package Kursavholder;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        KursavholderLogikk logikk = new KursavholderLogikk();

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();
        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        System.out.println("DEBUG - URL: " + url);
        System.out.println("DEBUG - Username: " + username);
        System.out.println("DEBUG - Password finnes?: " + (password != null));

        /*try{
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("Tilkoblet databasen");

            int searchID = 1;
            Kursavholder avholder = logikk.hentKursavholder(connection, searchID);

            if (avholder != null) {
                System.out.println("Kursavholder funnet i databsen: ");
                System.out.println(avholder.toString());
            }else {
                System.out.println("Ingen kursavholder funnet med ID: " + searchID);
            }
        }catch (SQLException ex ) {
            System.out.println("Kunne ikke koble til databasen" + ex.getMessage());
        }*/

        try{
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Tilkoblet databasen");
            System.out.println("Bruker denne URL-en: " + url);

            List<Kursavholder>  alleAvholdere = logikk.hentAlleKursavholdere(connection);
            System.out.println("----------Oversikt over alle kursavholdere----------");

            if (alleAvholdere.isEmpty()) {
                System.out.println("Det har ikke blitt funnet kursavholdere i databasen");
            }
            else {
                for (Kursavholder kursavholder : alleAvholdere) {
                    System.out.println("ID: " + kursavholder.Kursavholder_id() +
                            "| Navn: " + kursavholder.getNavn() +
                            "| Adresse: " + kursavholder.getAdresse_husflidslag());
                }
            }
        }
        catch (SQLException ex ) {
            System.err.println("Kunne ikke koble til eller hente fra databasen: " + ex.getMessage());

            /*
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("Feilkode: " + ex.getErrorCode());
            ex.printStackTrace(); // Dette viser nøyaktig hvor i koden det krasjer. brukes til feilsøking (debugging).
            //Når en feil (SQLException) oppstår i programmet, fanger catch-blokken den opp,
            //og disse tre linjene hjelper deg med å finne ut hva som gikk galt og hvor i koden feilen skjedde.

             */
        }

    }
}
