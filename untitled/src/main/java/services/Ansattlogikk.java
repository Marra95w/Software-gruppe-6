package services;

import DTO.AnsattDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ansattlogikk {

    // Finner en ansatt basert på ID
    public AnsattDTO finnAnsattMedId(Connection conn, int idAnsatt) {
        String sql = "SELECT * FROM ansatt_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idAnsatt);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fornavn = rs.getString("fornavn");
                    String etternavn = rs.getString("etternavn");
                    String epost = rs.getString("epost");
                    int telefonnummer = rs.getInt("telefonnummer");
                    String stillingstype = rs.getString("stillingstype");
                    String bilder = rs.getString("bilder");

                    return new AnsattDTO(id, fornavn, etternavn, epost, telefonnummer, stillingstype, bilder);
                }
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av ansatt: " + e.getMessage());
        }
        return null;
    }

    // Legger til en ny ansatt i databasen
    public boolean leggTilAnsatt(Connection conn, String fornavn, String etternavn, String epost, int telefonnummer, String stillingstype, String bilder) {
        String sql = "INSERT INTO ansatt_tabell (fornavn, etternavn, epost, telefonnummer, stillingstype, bilder) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fornavn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, telefonnummer);
            pstmt.setString(5, stillingstype);
            pstmt.setString(6, bilder);

            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved lagring av ny ansatt: " + e.getMessage());
            return false;
        }
    }

    // Sletter en ansatt basert på ID
    public boolean slettAnsattMedId(Connection conn, int id) {
        String sql = "DELETE FROM ansatt_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved sletting av ansatt: " + e.getMessage());
            return false;
        }
    }

    // Henter ut en liste med alle ansatte
    public List<AnsattDTO> hentAlleAnsatte(Connection conn) {
        List<AnsattDTO> ansattListe = new ArrayList<>();
        String sql = "SELECT * FROM ansatt_tabell";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String fornavn = rs.getString("fornavn");
                String etternavn = rs.getString("etternavn");
                String epost = rs.getString("epost");
                int telefonnummer = rs.getInt("telefonnummer");
                String stillingstype = rs.getString("stillingstype");
                String bilder = rs.getString("bilder");

                AnsattDTO ansatt = new AnsattDTO(id, fornavn, etternavn, epost, telefonnummer, stillingstype, bilder);
                ansattListe.add(ansatt);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av alle ansatte: " + e.getMessage());
        }
        return ansattListe;
    }
}