package Database;


import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

//FRA Claude
public class Database_Connection {
    private static final Dotenv dotenv = Dotenv.configure()
        .ignoreIfMissing()
        .ignoreIfMalformed()
        .load();

    public static Connection getConnection() throws SQLException{
        String url = dotenv.get("DBA_URL");
        String brukernavn = dotenv.get("DBA_USERNAME");
        String passord = dotenv.get("DBA_PASSWORD");

        return DriverManager.getConnection(url, brukernavn, passord);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            System.out.println("Tilkobling til database vellykket!");
        } catch (SQLException e) {
            System.err.println("Tilkobling feilet: " + e.getMessage());
        }
    }
}

    //Erling
    // public static void main(String[] args) {

    //     Dotenv dotenv = Dotenv.configure()
    //             .ignoreIfMissing()
    //             .ignoreIfMalformed().load();
    //     String url = dotenv.get("DBA_URL");
    //     String brukernavn = dotenv.get("DBA_USERNAME");
    //     String passord = dotenv.get("DBA_PASSWORD");
    //     try {
    //         Connection connection = DriverManager.getConnection(url, brukernavn, passord);

    //         String sql = " SELECT * FROM kurs_paamelding;";

    //         try (PreparedStatement prepStatement = connection.prepareStatement(sql);
    //              ResultSet resultSet = prepStatement.executeQuery()) {
    //             while (resultSet.next()) {
    //                 int kunde_id = resultSet.getInt("Kunde_id");
    //                 int kurs_id = resultSet.getInt("Kurs_id");
    //                 System.out.println("Kunde Id: " + kunde_id + " | Kurs Id: " + kurs_id);
    //             }
    //         }        catch (Exception e) {
    //             System.err.println(e.getMessage());
    //         }
    //     }catch (Exception e){
    //         System.err.println(e.getMessage());
    //     }
    // }
//}



