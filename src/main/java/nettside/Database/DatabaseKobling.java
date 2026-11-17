package nettside.Database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseKobling {

    static Dotenv dotenv = Dotenv.configure().ignoreIfMissing().ignoreIfMalformed().load();

    static String url = dotenv.get("DB_URL");
    static String username = dotenv.get("DB_USERNAME");
    static String password = dotenv.get("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
