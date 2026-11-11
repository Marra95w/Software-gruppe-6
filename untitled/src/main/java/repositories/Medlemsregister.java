package repositories;

import DTO.MedlemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Medlemsregister {

    // Finner et spesifikt medlem i databasen ved hjelp av ID
    public MedlemDTO finnMedlemMedId(Connection conn, int idMedlem) {
        // SQL-setning for å hente et medlem fra kunde_tabell basert på ID
        String sql = "SELECT * FROM kunde_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn ID-en på plass nr. 1 (?)
            pstmt.setInt(1, idMedlem);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Sjekker om vi fikk et treff i tabellen
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String navn = rs.getString("Navn");
                    String etternavn = rs.getString("Etternavn");
                    String epost = rs.getString("e_post");
                    int medlemStatus = rs.getInt("Medlem");
                    int telefonnummer = rs.getInt("Telefonnummer");
                    String passord = rs.getString("passord");

                    // Oppretter og returnerer et MedlemDTO-objekt med dataene fra databasen
                    return new MedlemDTO(id, navn, etternavn, epost, medlemStatus, telefonnummer, passord);
                }
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av medlem: " + e.getMessage());
        }
        return null;
    }

    // Legger til et nytt medlem i databasen
    public boolean leggTilMedlem(Connection conn, String navn, String etternavn, String epost, int medlemStatus, int telefonnummer, String passord) {
        // SQL-setning for å sette inn et nytt medlem i kunde_tabell
        String sql = "INSERT INTO kunde_tabell (Navn, Etternavn, e_post, Medlem, Telefonnummer, passord) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Fyller inn verdiene i de 6 spørsmålstegnene
            pstmt.setString(1, navn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, medlemStatus);
            pstmt.setInt(5, telefonnummer);
            pstmt.setString(6, passord);

            // Kjører spørringen og returnerer true hvis raden ble lagt til
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved lagring av nytt medlem: " + e.getMessage());
            return false;
        }
    }

    // Sletter et medlem fra databasen basert på ID
    public boolean slettMedlemMedId(Connection conn, int id) {
        // SQL-setning for å slette en rad der ID-en matcher
        String sql = "DELETE FROM kunde_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            // Utfører slettingen og returnerer true om noen rader ble slettet
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved sletting av medlem: " + e.getMessage());
            return false;
        }
    }

    // Oppdaterer informasjonen til et eksisterende medlem
    public boolean oppdaterMedlem(Connection conn, int id, String nyttFornavn, String nyttEtternavn, String nyEpost, int nyMedlemStatus, int nyttTelefonnummer, String nyttPassord) {
        // SQL-setning for å oppdatere feltene i kunde_tabell for en gitt ID
        String sql = "UPDATE kunde_tabell SET Navn = ?, Etternavn = ?, e_post = ?, Medlem = ?, Telefonnummer = ?, passord = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn de nye verdiene på plass 1 til 6, og ID-en på plass 7
            pstmt.setString(1, nyttFornavn);
            pstmt.setString(2, nyttEtternavn);
            pstmt.setString(3, nyEpost);
            pstmt.setInt(4, nyMedlemStatus);
            pstmt.setInt(5, nyttTelefonnummer);
            pstmt.setString(6, nyttPassord);
            pstmt.setInt(7, id);

            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved oppdatering av medlem: " + e.getMessage());
            return false;
        }
    }

    // Henter ut en liste med alle registrerte medlemmer fra databasen
    public List<MedlemDTO> hentAlleMedlemmer(Connection conn) {
        List<MedlemDTO> medlemsListe = new ArrayList<>();
        String sql = "SELECT * FROM kunde_tabell";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Går gjennom alle rader i resultatet fra databasen
            while (rs.next()) {
                int id = rs.getInt("id");
                String navn = rs.getString("Navn");
                String etternavn = rs.getString("Etternavn");
                String epost = rs.getString("e_post");
                int medlemStatus = rs.getInt("Medlem");
                int telefonnummer = rs.getInt("Telefonnummer");
                String passord = rs.getString("passord");

                // Lager et MedlemDTO-objekt for hver rad og legger det til i listen
                MedlemDTO m = new MedlemDTO(id, navn, etternavn, epost, medlemStatus, telefonnummer, passord);
                medlemsListe.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av alle medlemmer: " + e.getMessage());
        }
        return medlemsListe;
    }

    // Sjekker hvilke ID-er som ikke finnes i medlemsregisteret
    public List<Integer> hentIkkeMedlemmer(Connection conn, List<Integer> paameldteIder) {
        List<Integer> ikkeMedlemmer = new ArrayList<>();

        // Går gjennom en liste med ID-er for å sjekke hver enkelt
        for (int id : paameldteIder) {
            MedlemDTO m = finnMedlemMedId(conn, id);
            // Hvis medlemmet ikke finnes (null), legges ID-en til i listen over ikke-medlemmer
            if (m == null) {
                ikkeMedlemmer.add(id);
            }
        }
        return ikkeMedlemmer;
    }
}