import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Denne klassen håndterer all kommunikasjon med databasen for medlemmene (CRUD-operasjoner)
public class Medlemslogikk {

    // 1. Henter ut ett spesifikt medlem fra databasen ved hjelp av ID
    public medlem finnMedlemMedId(Connection conn, int idMedlem) {
        String sql = "SELECT * FROM kunde_tabell WHERE id = ?";

        // Bruker PreparedStatement for å unngå SQL-injeksjon
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMedlem);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String navn = rs.getString("Navn");
                    String etternavn = rs.getString("Etternavn");
                    String epost = rs.getString("e_post");
                    int medlemStatus = rs.getInt("Medlem");
                    int telefonnummer = rs.getInt("Telefonnummer");

                    // Returnerer et nytt medlem-objekt fylt med data fra databasen
                    return new medlem(id, navn, etternavn, epost, medlemStatus, telefonnummer);
                }
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av medlem: " + e.getMessage());
        }

        return null; // Returnerer null hvis medlemmet ikke ble funnet
    }

    // 2. Legger til et helt nytt medlem i databasen
    public boolean leggTilMedlem(Connection conn, String navn, String etternavn, String epost, int medlemStatus, int telefonnummer) {
        String sql = "INSERT INTO kunde_tabell (Navn, Etternavn, e_post, Medlem, Telefonnummer) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, navn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, medlemStatus);
            pstmt.setInt(5, telefonnummer);

            // Utfører innsettingen og sjekker om rader ble påvirket
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0; // True hvis lagringen gikk bra

        } catch (SQLException e) {
            System.err.println("Feil ved lagring av nytt medlem: " + e.getMessage());
            return false;
        }
    }

    // 3. Sletter et medlem fra databasen basert på ID
    public boolean slettMedlemMedId(Connection conn, int id) {
        String sql = "DELETE FROM kunde_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            // Utfører slettingen og returnerer true hvis raden ble slettet
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved sletting av medlem: " + e.getMessage());
            return false;
        }
    }

    // 4. Oppdaterer informasjonen til et eksisterende medlem basert på ID
    public boolean oppdaterMedlem(Connection conn, int id, String nyttFornavn, String nyttEtternavn, String nyEpost, int nyMedlemStatus, int nyttTelefonnummer) {
        String sql = "UPDATE kunde_tabell SET Navn = ?, Etternavn = ?, e_post = ?, Medlem = ?, Telefonnummer = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nyttFornavn);
            pstmt.setString(2, nyttEtternavn);
            pstmt.setString(3, nyEpost);
            pstmt.setInt(4, nyMedlemStatus);
            pstmt.setInt(5, nyttTelefonnummer);
            pstmt.setInt(6, id);

            // Utfører oppdateringen og returnerer true hvis den lyktes
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved oppdatering av medlem: " + e.getMessage());
            return false;
        }
    }
}