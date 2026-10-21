import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class finnMedlemMedId {

    // Metode for å hente et medlem basert på ID
    public medlem finnMedlemMedId(Connection conn, int idMedlem) {
        String sql = "SELECT * FROM kunde_tabell WHERE id = ?";

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

                    return new medlem(id, navn, etternavn, epost, medlemStatus, telefonnummer);
                }
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av medlem: " + e.getMessage());
        }

        return null;
    }

    // Metode for å LEGGE TIL nytt medlem (id genereres automatisk av tabellen)
    public boolean leggTilMedlem(Connection conn, String navn, String etternavn, String epost, int medlemStatus, int telefonnummer) {
        String sql = "INSERT INTO kunde_tabell (Navn, Etternavn, e_post, Medlem, Telefonnummer) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, navn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, medlemStatus);
            pstmt.setInt(5, telefonnummer);

            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved lagring av nytt medlem: " + e.getMessage());
            return false;
        }
    }

    // Metode for å slette et medlem basert på e-post
    public boolean slettMedlemMedEpost(Connection conn, String epost) {
        String sql = "DELETE FROM kunde_tabell WHERE e_post = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, epost);

            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0; // Returnerer true hvis noe ble slettet

        } catch (SQLException e) {
            System.err.println("Feil ved sletting av medlem: " + e.getMessage());
            return false;
        }
    }

} // <--- Denne lukker hele klassen, og må stå helt nederst!