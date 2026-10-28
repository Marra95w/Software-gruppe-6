import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Hovedklassen som starter programmet og tester databaseoperasjonene
public class main {
    public static void main(String[] args) {

        // Laster inn sikkerhetsinformasjon og tilkoblingsdetaljer fra .env-filen
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DBA_URL");
        String bruker = dotenv.get("DBA_USERNAME");
        String passord = dotenv.get("DBA_PASSWORD");

        // Lager et objekt av Medlemslogikk slik at vi kan kalle på database-metodene
        Medlemslogikk dao = new Medlemslogikk();

        // Oppretter en sikker tilkobling til databasen (lukkes automatisk etter bruk)
        try (Connection conn = DriverManager.getConnection(url, bruker, passord)) {



            // 1. SLETT MEDLEM BASERT PÅ ID


            int idSomSkalSlettes = 1; // ID-en til medlemmet du ønsker å slette
            boolean slettet = dao.slettMedlemMedId(conn, idSomSkalSlettes);

            if (slettet) {
                System.out.println("-> Suksess: Medlemmet med ID " + idSomSkalSlettes + " ble slettet fra databasen!");
            } else {
                System.out.println("-> Fant ingen medlemmer med ID: " + idSomSkalSlettes);
            }



            // 2. LEGG TIL NYTT MEDLEM

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



            // 3. HENTE UT MEDLEM VIA ID

            /*
            System.out.println("--------------------------------------------------");
            medlem medlem1 = dao.finnMedlemMedId(conn, 1);

            if (medlem1 != null) {
                System.out.println("Hentet ut Medlem 1: " + medlem1.toString());
            } else {
                System.out.println("Fant ikke noe medlem med ID = 1");
            }
            */



            // 4. OPPDATER MEDLEM

            /*
            System.out.println("--------------------------------------------------");
            int idSomSkalOppdateres = 1;
            String nyttFornavn = "Ola (Oppdatert)";
            String nyttEtternavn = "Nordmann";
            String nyEpost = "ny.epost@nordmail.no";
            int nyMedlemStatus = 1;
            int nyttTelefonnummer = 99999999;

            boolean oppdatert = dao.oppdaterMedlem(conn, idSomSkalOppdateres, nyttFornavn, nyttEtternavn, nyEpost, nyMedlemStatus, nyttTelefonnummer);

            if (oppdatert) {
                System.out.println("-> Suksess: Medlem med ID " + idSomSkalOppdateres + " ble oppdatert!");
            } else {
                System.out.println("-> Klarte ikke å oppdatere medlemmet.");
            }
            */

        } catch (SQLException e) {
            // Viser feilmelding i konsollen hvis tilkoblingen til databasen feiler
            System.err.println("Kunne ikke koble til databasen: " + e.getMessage());
        }
    }
}