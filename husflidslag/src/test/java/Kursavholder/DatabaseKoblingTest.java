package Kursavholder;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class DatabaseKoblingTest {
    @Test
    public void testDatabaseKoblingen() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

        String url = dotenv.get("DB_URL");
        String username = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection(url, username, password)){

            assertNotNull(connection, "Tilkoblingen er null eller skal være null");
            assertFalse(connection.isClosed(), "Tilkoblingen er akriv og åpen ");

            System.out.println("Vellykket test: Koblet til databasen");
        }
        catch (SQLException ex) {
            fail("Klarte ikke å koble til databasen, sjekk om VPN er på: " + ex.getMessage());
        }
    }
}
