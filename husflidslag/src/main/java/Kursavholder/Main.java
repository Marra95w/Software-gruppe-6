package Kursavholder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

        try{
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
        }
    }
}
