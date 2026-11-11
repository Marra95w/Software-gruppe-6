package config;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databasekobling {

    // En metode som oppretter og returnerer en tilkobling til databasen
    public static Connection kobleTil() throws SQLException {
        // Laster inn .env-filen slik at vi kan hente sensitive opplysninger skjult
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

        // Henter database-adresse, brukernavn og passord fra .env-filen
        String url = dotenv.get("DBA_URL");
        String bruker = dotenv.get("DBA_USERNAME");
        String passord = dotenv.get("DBA_PASSWORD");

        // Bruker DriverManager for å koble seg til databasen og returnerer tilkoblingen
        return DriverManager.getConnection(url, bruker, passord);
    }
}