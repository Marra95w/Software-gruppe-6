package services;

import DTO.MedlemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Medlemslogikk {

    // Finner et medlem basert på ID
    public MedlemDTO finnMedlemMedId(Connection conn, int idMedlem) {
        // SQL-setning for å hente ut et spesifikt medlem fra databasen ved hjelp av en trygg parameter (?)
        String sql = "SELECT * FROM kunde_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn ID-verdien på plass nummer 1 i SQL-spørringen
            pstmt.setInt(1, idMedlem);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Sjekker om spørringen returnerte en rad (om medlemmet finnes)
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String navn = rs.getString("Navn");
                    String etternavn = rs.getString("Etternavn");
                    String epost = rs.getString("e_post");
                    int medlemStatus = rs.getInt("Medlem");
                    int telefonnummer = rs.getInt("Telefonnummer");
                    String passord = rs.getString("passord");

                    // Pakker dataene inn i et MedlemDTO-objekt og returnerer det
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
        // SQL-setning for å sette inn et nytt medlem med parametere (?)
        String sql = "INSERT INTO kunde_tabell (Navn, Etternavn, e_post, Medlem, Telefonnummer, passord) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Fyller inn verdiene i de 6 spørsmålstegnene i SQL-setningen
            pstmt.setString(1, navn);
            pstmt.setString(2, etternavn);
            pstmt.setString(3, epost);
            pstmt.setInt(4, medlemStatus);
            pstmt.setInt(5, telefonnummer);
            pstmt.setString(6, passord);

            // Kjører oppdateringen og sjekker om det ble påvirket flere enn 0 rader (vellykket lagring)
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved lagring av nytt medlem: " + e.getMessage());
            return false;
        }
    }

    // Sletter et medlem basert på ID
    public boolean slettMedlemMedId(Connection conn, int id) {
        // SQL-setning for å slette en rad basert på ID
        String sql = "DELETE FROM kunde_tabell WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn ID-en som skal slettes på plass nummer 1
            pstmt.setInt(1, id);

            // Utfører slettingen og returnerer true hvis en rad faktisk ble slettet
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved sletting av medlem: " + e.getMessage());
            return false;
        }
    }

    // Oppdaterer informasjonen til et medlem
    public boolean oppdaterMedlem(Connection conn, int id, String nyttFornavn, String nyttEtternavn, String nyEpost, int nyMedlemStatus, int nyttTelefonnummer, String nyttPassord) {
        // SQL-setning for å oppdatere feltene for en spesifikk ID
        String sql = "UPDATE kunde_tabell SET Navn = ?, Etternavn = ?, e_post = ?, Medlem = ?, Telefonnummer = ?, passord = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setter inn de nye verdiene på plass 1 til 6, og ID-en som avgjør hvem som oppdateres på plass 7
            pstmt.setString(1, nyttFornavn);
            pstmt.setString(2, nyttEtternavn);
            pstmt.setString(3, nyEpost);
            pstmt.setInt(4, nyMedlemStatus);
            pstmt.setInt(5, nyttTelefonnummer);
            pstmt.setString(6, nyttPassord);
            pstmt.setInt(7, id);

            // Utfører oppdateringen og returnerer true om den påvirket rader
            int raderPaverket = pstmt.executeUpdate();
            return raderPaverket > 0;

        } catch (SQLException e) {
            System.err.println("Feil ved oppdatering av medlem: " + e.getMessage());
            return false;
        }
    }

    // Henter ut en liste med alle medlemmer
    public List<MedlemDTO> hentAlleMedlemmer(Connection conn) {
        // Oppretter en tom liste som skal holde på alle medlemmene
        List<MedlemDTO> medlemsListe = new ArrayList<>();
        String sql = "SELECT * FROM kunde_tabell";

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Går gjennom alle rader i resultatet fra databasen én etter én
            while (rs.next()) {
                int id = rs.getInt("id");
                String navn = rs.getString("Navn");
                String etternavn = rs.getString("Etternavn");
                String epost = rs.getString("e_post");
                int medlemStatus = rs.getInt("Medlem");
                int telefonnummer = rs.getInt("Telefonnummer");
                String passord = rs.getString("passord");

                // Lager et nytt MedlemDTO-objekt for raden og legger det til i listen
                MedlemDTO m = new MedlemDTO(id, navn, etternavn, epost, medlemStatus, telefonnummer, passord);
                medlemsListe.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Feil ved henting av alle medlemmer: " + e.getMessage());
        }
        // Returnerer den ferdige listen med medlemmer
        return medlemsListe;
    }
}