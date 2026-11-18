package nettside.Service;

import nettside.DTO.AnsattDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ansattlogikk {

    // Finner en ansatt basert på ID
    public AnsattDTO finnAnsattMedId(Connection conn, int idAnsatt) {
        // SQL-setning for å hente ut en ansatt fra ansatt_tabell basert på ID, med parameter (?)
        String sql = "SELECT * FROM ansatt_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn ID-verdien på plass nummer 1 i SQL-spørringen
            pstmt.setInt(1, idAnsatt);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Sjekker om spørringen returnerte en rad (om den ansatte finnes)
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fornavn = rs.getString("fornavn");
                    String etternavn = rs.getString("etternavn");
                    String epost = rs.getString("epost");
                    int telefonnummer = rs.getInt("telefonnummer");
                    String stillingstype = rs.getString("stillingstype");
                    String bilder = rs.getString("bilder");

                    // Pakker dataene inn i et AnsattDTO-objekt og returnerer det
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
        // SQL-setning for å sette inn en ny ansatt med 6 verdier via parametere (?)
        String sql = "INSERT INTO ansatt_tabell (fornavn, etternavn, epost, telefonnummer, stillingstype, bilder) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Fyller inn verdiene i de 6 spørsmålstegnene
            pstmt.setString(1, fornavn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, telefonnummer);
            pstmt.setString(5, stillingstype);
            pstmt.setString(6, bilder);

            // Kjører oppdateringen og returnerer true hvis det ble påvirket flere enn 0 rader (vellykket lagring)
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved lagring av ny ansatt: " + e.getMessage());
            return false;
        }
    }

    // Sletter en ansatt basert på ID
    public boolean slettAnsattMedId(Connection conn, int id) {
        // SQL-setning for å slette en rad fra ansatt_tabell der ID matcher
        String sql = "DELETE FROM ansatt_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn ID-en som skal slettes på plass nummer 1
            pstmt.setInt(1, id);

            // Utfører slettingen og returnerer true hvis en rad faktisk ble slettet
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved sletting av ansatt: " + e.getMessage());
            return false;
        }
    }

    // Henter ut en liste med alle ansatte
    public List<AnsattDTO> hentAlleAnsatte(Connection conn) {
        // Oppretter en tom liste som skal holde på alle de ansatte
        List<AnsattDTO> ansattListe = new ArrayList<>();
        String sql = "SELECT * FROM ansatt_tabell";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Går gjennom alle rader i resultatet fra databasen én etter én
            while (rs.next()) {
                int id = rs.getInt("id");
                String fornavn = rs.getString("fornavn");
                String etternavn = rs.getString("etternavn");
                String epost = rs.getString("epost");
                int telefonnummer = rs.getInt("telefonnummer");
                String stillingstype = rs.getString("stillingstype");
                String bilder = rs.getString("bilder");

                // Lager et nytt AnsattDTO-objekt for raden og legger det til i listen
                AnsattDTO ansatt = new AnsattDTO(id, fornavn, etternavn, epost, telefonnummer, stillingstype, bilder);
                ansattListe.add(ansatt);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av alle ansatte: " + e.getMessage());
        }
        // Returnerer den ferdige listen med ansatte
        return ansattListe;
    }
}