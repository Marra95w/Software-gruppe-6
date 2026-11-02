package nettside.Logikk;

import nettside.Klasser.husflidslag_kurs;
import nettside.Klasser.adresse_husflidslag;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.SQLException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
//import java.util.List;

/*OBS DENNE KODEN ER MENT FOR BRUK AV ADMIN I ØSTFOLDS HUDSFLISLAG, MEN VED HJELP AV FRONTEND
 KAN NOEN AV DISSE FOR EKSEMPEL SELECECT KURS BLI BRUKT AV MEDLEMMENE/IKKE MEDLEMMER FOR Å FINNE KURS.*/

/*Dette betyr at admin skal kunne håndterer direkte kursene ved bruk av prepared statements
Geeks for Geeks og Programmering 2 koden var en fin kilde for oss å lære disse.
Det som følger er enkel spørringer som oppdaterer, velger, sletter og oppretter kurs
Denne klasssen hådterer logikken for spørringer mellom databasen og mysql spørringer*/

public class Database_Kurs_Logikk {

    //dette er passord, brukernavn og serveradresse for databasen vår, som vi henter fra .env filen vår
    //for å beskytte disse mot uautorisert tilgang, selv om vårt github repo er privat
    Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .ignoreIfMalformed().load();
    String url = dotenv.get("DBA_URL2");
    String brukernavn = dotenv.get("DBA_USERNAME2");
    String passord = dotenv.get("DBA_PASSWORD2");


    //--------------------------------------------------------------------------------------------//

    //HER STARTER FUNKSJONER SÅ VÆR FORSIKTIG ELLERS GÅR ALT NED

    //FUNKSJONER SOM HENTER KURS OG ADRESSE FOR ET KURS.

    //--------------------------------------------------------------------------------------------//



    public List<husflidslag_kurs> hent_alle_kurs_fra_db() {
        List<husflidslag_kurs> alle_kurs = new ArrayList<>();

        String sql = "SELECT kurs_tabell.*,kursavholder.Navn, adresse_husflidslag.Adresse_husflidslag FROM kurs_tabell" +
                " LEFT JOIN kursavholder ON kurs_tabell.Kursavholder_id = kursavholder.Kursavholder_id" +
                " LEFT JOIN adresse_husflidslag ON kursavholder.Kursavholder_id = adresse_husflidslag.Adresse_Kursvavholder_Id";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    //henter alle kurs og  setter dem i husflidslag_kurs tabellen slik blir disse synlig
                    husflidslag_kurs kurs = new husflidslag_kurs();


                    kurs.setTittel(rs.getString("tittel"));
                    kurs.setStartDatoTid(rs.getTimestamp("Start_dato_tid").toLocalDateTime());
                    kurs.setSluttDatoTid(rs.getTimestamp("Slutt_dato_tid").toLocalDateTime());
                    kurs.setKursavholder(rs.getInt("Kursavholder_Id"));
                    kurs.setPrisIkkeMedlem(rs.getDouble("pris_ikke_medlem"));
                    kurs.setPrisMedlem(rs.getDouble("pris_medlem"));
                    kurs.setBeskrivelse(rs.getString("Beskrivelse"));

                    kurs.setAdresse(rs.getString("Adresse_husflidslag"));

                    alle_kurs.add(kurs);
                }
            }
        } catch (Exception e) {
            System.err.println("Databasefeil: " + e.getMessage());
        }

        return alle_kurs;
    }




    //FUNKSJON SOM HENTER Spesifikk KURS FRA DATABASEN
    public husflidslag_kurs hent_kurs_fra_db(int id) {

        String sql =
                "SELECT kurs_tabell.*,kursavholder.Navn, adresse_husflidslag.Adresse_husflidslag FROM kurs_tabell"+
                " LEFT JOIN kursavholder ON kurs_tabell.Kursavholder_id = kursavholder.Kursavholder_id"+
                " LEFT JOIN adresse_husflidslag ON kursavholder.Kursavholder_id = adresse_husflidslag.Adresse_Kursvavholder_Id"+
                " WHERE kurs_tabell.id = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {

                    //her ønsker vi også å gjøre synlig enkelte kurser man velger i frontenden
                    husflidslag_kurs en_kurs = new husflidslag_kurs();


                    en_kurs.setTittel(rs.getString("Tittel"));
                    en_kurs.setStartDatoTid(rs.getTimestamp("Start_dato_tid").toLocalDateTime());
                    en_kurs.setSluttDatoTid(rs.getTimestamp("Slutt_dato_tid").toLocalDateTime());
                    en_kurs.setKursavholder(rs.getInt("Kursavholder_Id"));
                    en_kurs.setPrisIkkeMedlem(rs.getDouble("Pris_ikke_medlem"));
                    en_kurs.setPrisMedlem(rs.getDouble("Pris_medlem"));
                    en_kurs.setBeskrivelse(rs.getString("Beskrivelse"));
                    en_kurs.setAdresse(rs.getString("Adresse_husflidslag"));

                    //viktig  med return kurs for å få faktisk disse verdiene opp, ellers vil det alltid returnere null
                    return en_kurs;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;

    }


    //FUNKSJON SOM HENTER ADRESSEN FRA DATABASEN
    public adresse_husflidslag hent_adresse_fra_db(int id) {


        String sql2 = "SELECT * FROM adresse_husflidslag where id = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt2 = conn.prepareStatement(sql2)) {

            stmt2.setInt(1, id);

            stmt2.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;

    }


    //-------------------------------------------------------------------------------------------//


    //FUNKSJONER SOM SETTER KURS OG ADRESSE FOR KURS I DATABASEN


    //------------------------------------------------------------------------------------------//


    //FUNKSJON SOM SETTER KURS I DATABASEN VÆR FORSIKTIG, IKKE BRUK HVIS DETTE BLIR FORVIRRENDE
    public husflidslag_kurs sett_kurs_i_db(String tittel, LocalDateTime startDatoTid,LocalDateTime sluttDatoTid, int kursavholder, double prisMedlem, double prisIkkeMedlem, String beskrivelse) {
        String sql ="INSERT INTO kurs_tabell (tittel, startDatoTid, sluttDatoTid, Kursavholder_Id, pris_medlem, pris_ikke_medlem, Beskrivelse) VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1,tittel);
            stmt.setObject(2,startDatoTid);
            stmt.setObject(3,sluttDatoTid);
            stmt.setInt(4,kursavholder);
            stmt.setDouble(5,prisMedlem);
            stmt.setDouble(6,prisIkkeMedlem);
            stmt.setString(7,beskrivelse);
            stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //FUNKSJONER FOR Å SETTE ADRESSE OM DETTE SKULLE VÆRE NØDVENDIGE
    //OBS BARE BRUK DENNE I TILFELLE NYTT ADRESSE SKAL SETTES OPP
    public adresse_husflidslag sett_adresse_i_db(int Adresse_Kursvavholder_Id, String Adresse_husflidslag) {

        String sql = "INSERT INTO adresse_husflidslag VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, Adresse_Kursvavholder_Id);
            stmt.setString(2, Adresse_husflidslag);

            stmt.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }


    //-------------------------------------------------------------------------------------------//


    //FUNKSJONER SOM OPPDATERER KURS


    //------------------------------------------------------------------------------------------//


    //FUNKSJON SOM OPPDATERER KURS
    public husflidslag_kurs oppdatere_kurs_i_db(String tittel, LocalDateTime startDatoTid, LocalDateTime sluttDatoTid, int kursavholder, double prisMedlem, double prisIkkeMedlem, String beskrivelse,int id) {

        String sql = "UPDATE kurs_tabell SET tittel = ?, startDatoTid = ?, sluttDatoTid = ?, Kursavholder_Id = ?, pris_Medlem = ?," +
                "pris_Ikke_Medlem = ?, beskrivelse = ? WHERE id = ?";


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

            stmt.executeUpdate();


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;

    }


    //-------------------------------------------------------------------------------------------//


    //FUNKSJONER SOM SLETTER KURS OG ADRESSE FOR KURS I DATABASEN


    //------------------------------------------------------------------------------------------//


    //FUNKSJON SOM SLETTER KURS FRA DATABASEN BASERT PÅ TITTEL
    public husflidslag_kurs slett_kurs_fra_db(String tittel) {

        String sql = "DELETE FROM kurs_tabell WHERE tittel = ?";

        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tittel);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    //FUNKSJON FOR Å SLETTE ADRESSE FRA DATABASEN, ADRESSE TABELLEN FOR Å VÆRE PRESIS
    public adresse_husflidslag slett_adresse_fra_db(String adresse_husflidslag) {

        String sql = "DELETE FROM adresse_husflidslag WHERE Adresse_husflidslag = ?";


        try (Connection conn = DriverManager.getConnection(url, brukernavn, passord);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, adresse_husflidslag);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }



}