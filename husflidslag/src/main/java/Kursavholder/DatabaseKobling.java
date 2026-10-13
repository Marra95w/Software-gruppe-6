package Kursavholder;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseKobling {

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

    String url = dotenv.get("DB_URL");
    String username = dotenv.get("DB_USERNAME");
    String password = dotenv.get("DB_PASSWORD");

    public static Connection FaaTilkoblingen() throws SQLException {
        return FaaTilkoblingen();
    }
}
