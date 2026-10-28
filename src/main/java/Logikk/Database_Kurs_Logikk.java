package Logikk;

import Klasser.husflidslag_kurs;
import Klasser.adresse_husflidslag;
import Klasser.kurs_paamelding;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.SQLException;

import java.sql.*;
import java.time.LocalDateTime;
//import java.util.List;

//OBS DENNE KODEN ER MENT FOR BRUK AV ADMIN I ØSTFOLDS HUDSFLISLAG.
//Dette betyr at admin skla kunne håndterer direkte kursene ved bruk av prepared statement
//Det som følger er enkel spørringer som oppdaterer, velger, sletter og oppretter kurs
//Denne klasssen hådterer logikken for spørringer mellom databasen og mysql spørringer

public class Database_Kurs_Logikk {

    // private List<Database_Kurs_Logikk> hente_kurs;
    // public List<Database_Kurs_Logikk> getHente_kurs(){return hente_kurs;}

    // dette er passord, brukernavn og serveradresse for databsen vår, som vi henter
    // fra .env filen vår
    // for å beskytte disse mot uautorisert tilgang, selv om vårt github repo er
    // privat
    Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .ignoreIfMalformed().load();
    String url = dotenv.get("DBA_URL2");
    String brukernavn = dotenv.get("DBA_USERNAME2");
    String passord = dotenv.get("DBA_PASSWORD2");

    // --------------------------------------------------------------------------------------------//

    // HER STARTER METODENE SÅ TRE FORSIKTIG ELLERS GÅR ALT NED

    // METODENE SOM HENTER KURS OG ADRESSE FOR ET KURS

    // --------------------------------------------------------------------------------------------//

    // METODE SOM HENTER KURS FRA DATABASEN
    public husflidslag_kurs hent_kurs_fra_db(int id) {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .ignoreIfMalformed().load();
        String url = dotenv.get("DBA_URL2");
        String brukernavn = dotenv.get("DBA_USERNAME2");
        String passord = dotenv.get("DBA_PASSWORD2");

        try {
            Connection connection = DriverManager.getConnection(url, brukernavn, passord);
            String sql = "SELECT kurs_tabell.*, adresse_husflidslag FROM kurs_tabell LEFT JOIN adresse_husflidslag ON kurs_tabell.Kursavholder_id = adresse_husflidslag.Adresse_Kursvavholder_Id WHERE kurs_tabell.id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    husflidslag_kurs kurs = new husflidslag_kurs();

                    kurs.setTittel(rs.getString("tittel"));
                    kurs.setKursavholder(rs.getInt("Kursavholder_Id"));
                    kurs.setPrisIkkeMedlem(rs.getDouble("pris_ikke_medlem"));
                    kurs.setPrisMedlem(rs.getDouble("pris_medlem"));
                    kurs.setBeskrivelse(rs.getString("Beskrivelse"));

                    return kurs;
                }
            }
        } catch (Exception e) {
            System.err.println("Databasefeil: " + e.getMessage());
        }
        return null;

    }

    // METODE SOM HENTER ADRESSEN FRA DATABASEN
    public adresse_husflidslag hent_adresse_fra_db(int id) {

        String sql2 = "SELECT * FROM adresse_husflidslag where id = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
                PreparedStatement stmt2 = conn.prepareStatement(sql2)) {

            stmt2.setInt(1, id);

            try (ResultSet rs = stmt2.executeQuery()) {
                if (rs.next()) {

                    int adresse_id = rs.getInt("id");
                    int henter_Adresse_Kursvavholder_Id = rs.getInt("Adresse_Kursvavholder_Id");
                    String henter_Adresse_Husflidslag = rs.getString("Adresse_husflidslag");

                    adresse_husflidslag adresse = new adresse_husflidslag(adresse_id, henter_Adresse_Kursvavholder_Id,
                            henter_Adresse_Husflidslag);

                    adresse.setAdresse_husflidslag(rs.getString("Adresse_husflidslag"));

                    return adresse;

                }
            } catch (SQLException e) {
                System.err.println("Databasefeil: " + e.getMessage());
            }
        } catch (Exception e) {
            System.err.println("Feil: " + e.getMessage());
        }

        return null;

    }

    // -------------------------------------------------------------------------------------------//

    // METODE SOM SETTER KURS OG ADRESSE FOR KURS I DATABASEN

    // ------------------------------------------------------------------------------------------//

    // METODE SOM SETTER KURS I DATABASEN VÆR FORSIKTIG, IKKE BRUK HVIS DETTE BLIR
    // FORVIRRENDE
    public husflidslag_kurs sett_kurs_i_db(String tittel, LocalDateTime startDatoTid, LocalDateTime sluttDatoTid,
            int kursavholder, double prisMedlem, double prisIkkeMedlem, String beskrivelse) {
        String sql = "INSERT INTO kurs_tabell VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 2. SJEKK DISSE: Bokstavene må være HENT IDENTISKE med toppen:
            stmt.setString(1, tittel);
            stmt.setObject(2, startDatoTid);
            stmt.setObject(3, sluttDatoTid);
            stmt.setInt(4, kursavholder);
            stmt.setDouble(5, prisMedlem);
            stmt.setDouble(6, prisIkkeMedlem);
            stmt.setString(7, beskrivelse);

            stmt.executeUpdate();

            // 3. SJEKK SETTERNE: Her bruker du de samme variablene igjen
            husflidslag_kurs lagretKurs = new husflidslag_kurs();
            lagretKurs.setTittel(tittel);
            lagretKurs.setPrisMedlem(prisMedlem);
            lagretKurs.setBeskrivelse(beskrivelse);

            return lagretKurs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // METODE FOR Å SETTE ADRESSE OM DETTE SKULLE VÆRE NØDVENDIGE
    // OBS BARE BRUK DENNE I TILFELLE NYTT ADRESSE SKAL SETTES OPP
    public adresse_husflidslag sett_adresse_i_db(int Adresse_Kursvavholder_Id, String Adresse_husflidslag) {

        String sql = "INSERT INTO adresse_husflidslag VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Adresse_Kursvavholder_Id);
            stmt.setString(2, Adresse_husflidslag);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    // -------------------------------------------------------------------------------------------//

    // METODE SOM OPDATERER KURS

    // ------------------------------------------------------------------------------------------//

    // METODE SOM OPPDATERER KURS
    public husflidslag_kurs oppdatere_kurs_i_db(String tittel, LocalDateTime startDatoTid, LocalDateTime sluttDatoTid,
            int kursavholder, double prisMedlem, double prisIkkeMedlem, String beskrivelse, int id) {

        String sql = "UPDATE kurs_tabell SET tittel = ?, startDatoTid = ?, sluttDatoTid = ?, kursavholder = ?, prisMedlem ?,"
                +
                "prisIkkeMedlem = ?, beskrivelse = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tittel);
            stmt.setObject(2, startDatoTid);
            stmt.setObject(3, sluttDatoTid);
            stmt.setInt(4, kursavholder);
            stmt.setDouble(5, prisMedlem);
            stmt.setDouble(6, prisIkkeMedlem);
            stmt.setString(7, beskrivelse);
            stmt.setInt(8, id);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;

    }

    // -------------------------------------------------------------------------------------------//

    // METODE SOM SLETTER KURS OG ADRESSE FOR KURS I DATABASEN

    // ------------------------------------------------------------------------------------------//

    // METODE SOM SLETTER KURS FRA DATABASEN BASERT PÅ TITTEL
    public husflidslag_kurs slett_kurs_fra_db(String tittel) {

        String sql = "DELETE FROM kurs_tabell WHERE tittel = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tittel);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    // METODE FOR Å SLETTE ADRESSE FRA DATABASEN, ADRESSE TABELLEN FOR Å VÆRE PRESIS
    public adresse_husflidslag slett_adresse_fra_db(String adresse_husflidslag) {

        String sql = "DELETE FROM adresse_husflidslag WHERE Adresse_husflidslag = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, adresse_husflidslag);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}