package nettside.Database;

import io.github.cdimascio.dotenv.Dotenv;

/*Dette er bare selve koblingen av databasen med Java koden, men mye av dette blir implemetert i Database_Kurs_Logikk
* klassen og Database_paamelding_logikk klassen. */

public class Database_Connection {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed().load();
        String url = dotenv.get("DBA_URL2");
        String brukernavn = dotenv.get("DBA_USERNAME2");
        String passord = dotenv.get("DBA_PASSWORD2");

    }

   }
