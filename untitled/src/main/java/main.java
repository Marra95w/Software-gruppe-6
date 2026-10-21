import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DBA_URL");
        String bruker = dotenv.get("DBA_USERNAME");
        String passord = dotenv.get("DBA_PASSWORD");

        finnMedlemMedId dao = new finnMedlemMedId();

        try (Connection conn = DriverManager.getConnection(url, bruker, passord)) {


            // 1. Slette medlem

            String epostSomSkalSlettes = "stffffuu@epost.no";
            boolean slettet = dao.slettMedlemMedEpost(conn, epostSomSkalSlettes);

            if (slettet) {
                System.out.println("-> Suksess: Medlemmet med e-post '" + epostSomSkalSlettes + "' ble slettet fra databasen!");
            } else {
                System.out.println("-> Fant ingen medlemmer med e-post: " + epostSomSkalSlettes);
            }



            // 2. Legge til et nytt medlem

            /*
            String fornavn = "Hold";
            String etternavn = "Kjeft";
            String epost = "stffffuu@epost.no";
            int medlemStatus = 1;
            int telefonnummer = 41234569;

            boolean lagtTil = dao.leggTilMedlem(conn, fornavn, etternavn, epost, medlemStatus, telefonnummer);

            if (lagtTil) {
                System.out.println("-> Suksess: " + fornavn + " " + etternavn + " ble lagt til i databasen!");
            } else {
                System.out.println("-> Klarte ikke å legge til medlemmet.");
            }
            */



            // 3. Hent ut medlem via id

            /*
            medlem medlem1 = dao.finnMedlemMedId(conn, 1);

            if (medlem1 != null) {
                System.out.println("Hentet ut Medlem 1: " + medlem1.toString());
            } else {
                System.out.println("Fant ikke noe medlem med ID = 1");
            }
            */

        } catch (SQLException e) {
            System.err.println("Kunne ikke koble til databasen: " + e.getMessage());
        }
    }
}