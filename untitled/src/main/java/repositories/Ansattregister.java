package repositories;

import DTO.AnsattDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ansattregister {

    // Finner en spesifikk ansatt i databasen ved hjelp av ID
    public AnsattDTO finnAnsattMedId(Connection conn, int idAnsatt) {
        // SQL-setning med et spørsmålstegn (?) som parameter for å forhindre SQL-injeksjon
        String sql = "SELECT * FROM ansatt_tabell WHERE id = ?";

        // Forbereder SQL-setningen trygt ved hjelp av PreparedStatement
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn ID-en på plass nr. 1 (?) i SQL-setningen
            pstmt.setInt(1, idAnsatt);

            // Utfører spørringen og henter resultatet i et ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {
                // Sjekker om vi fant en ansatt med denne ID-en
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String fornavn = rs.getString("fornavn");
                    String etternavn = rs.getString("etternavn");
                    String epost = rs.getString("epost");
                    int telefonnummer = rs.getInt("telefonnummer");
                    String stillingstype = rs.getString("stillingstype");
                    String bilder = rs.getString("bilder");

                    // Oppretter og returnerer et nytt AnsattDTO-objekt med dataene fra databasen
                    return new AnsattDTO(id, fornavn, etternavn, epost, telefonnummer, stillingstype, bilder);
                }
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av ansatt: " + e.getMessage());
        }
        return null; // Returnerer null hvis den ansatte ikke finnes eller det oppstår en feil
    }

    // Legger til en ny ansatt i databasen
    public boolean leggTilAnsatt(Connection conn, String fornavn, String etternavn, String epost, int telefonnummer, String stillingstype, String bilder) {
        // SQL-setning for å sette inn en ny rad med verdier
        String sql = "INSERT INTO ansatt_tabell (fornavn, etternavn, epost, telefonnummer, stillingstype, bilder) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Fyller inn verdier på de 6 plassene (?) i SQL-setningen
            pstmt.setString(1, fornavn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, telefonnummer);
            pstmt.setString(5, stillingstype);
            pstmt.setString(6, bilder);

            // Utfører endringen (INSERT) og sjekker hvor mange rader som ble påvirket
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0; // Returnerer true om raden ble lagt til (mer enn 0 rader påvirket)

        } catch (SQLException e) {
            System.err.println("Feil ved lagring av ny ansatt: " + e.getMessage());
            return false;
        }
    }

    // Sletter en ansatt fra databasen basert på ID
    public boolean slettAnsattMedId(Connection conn, int idAnsatt) {
        // SQL-setning for å slette raden der ID matcher
        String sql = "DELETE FROM ansatt_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn ID-en som skal slettes på plass nr. 1
            pstmt.setInt(1, idAnsatt);

            // Utfører slettingen og returnerer true hvis noen rader ble slettet
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved sletting av ansatt: " + e.getMessage());
            return false;
        }
    }

    // Oppdaterer informasjonen til en eksisterende ansatt
    public boolean oppdaterAnsatt(Connection conn, int idAnsatt, String fornavn, String etternavn, String epost, int telefonnummer, String stillingstype, String bilder) {
        // SQL-setning for å oppdatere feltene for en gitt ID
        String sql = "UPDATE ansatt_tabell SET fornavn = ?, etternavn = ?, epost = ?, telefonnummer = ?, stillingstype = ?, bilder = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn de nye verdiene for feltene
            pstmt.setString(1, fornavn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, telefonnummer);
            pstmt.setString(5, stillingstype);
            pstmt.setString(6, bilder);
            pstmt.setInt(7, idAnsatt); // ID-en som avgjør hvilken rad som skal oppdateres

            // Utfører oppdateringen og sjekker om den var vellykket
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved oppdatering av ansatt: " + e.getMessage());
            return false;
        }
    }

    // Henter ut en liste med alle registrerte ansatte fra databasen
    public List<AnsattDTO> hentAlleAnsatte(Connection conn) {
        // Oppretter en tom liste som skal fylles med ansatte
        List<AnsattDTO> ansattListe = new ArrayList<>();
        String sql = "SELECT * FROM ansatt_tabell";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Går gjennom alle radene som returneres fra databasen én etter én
            while (rs.next()) {
                int id = rs.getInt("id");
                String fornavn = rs.getString("fornavn");
                String etternavn = rs.getString("etternavn");
                String epost = rs.getString("epost");
                int telefonnummer = rs.getInt("telefonnummer");
                String stillingstype = rs.getString("stillingstype");
                String bilder = rs.getString("bilder");

                // Lager et AnsattDTO-objekt for raden og legger det til i listen
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