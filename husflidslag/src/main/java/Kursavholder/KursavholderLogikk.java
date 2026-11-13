package Kursavholder;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class KursavholderLogikk {


    //Metode for å hente enkelte kursavholdere
    public KursavholderDTO hentKursavholder( int Kursavholder_id) {
        String sql = "SELECT kursavholder.kursavholder_id, kursavholder.Navn, " +
                "adresse_husflidslag.id, adresse_husflidslag.Adresse_Kursvavholder_Id, " +
                "adresse_husflidslag.Adresse_husflidslag " +
                "FROM kursavholder " +
                "JOIN adresse_husflidslag ON kursavholder.kursavholder_id = adresse_husflidslag.Adresse_Kursvavholder_Id " +
                "WHERE kursavholder.kursavholder_id = ?";
       try (Connection connection = DatabaseKobling.FaaTilkoblingen())  {

           PreparedStatement pstmt = connection.prepareStatement(sql);

           pstmt.setInt(1, Kursavholder_id);
           try (ResultSet rs = pstmt.executeQuery()) {
               if (rs.next()) {
                   int hentKursavholder_id = rs.getInt("Kursavholder_id");
                   String hentNavn = rs.getString("Navn");
                   int hentAdresse_Kursvavholder_Id = rs.getInt("Adresse_Kursvavholder_Id");
                   String hentAdresse_husflidslag = rs.getString("Adresse_husflidslag");

                   return new KursavholderDTO(hentKursavholder_id, hentNavn, hentAdresse_husflidslag, hentAdresse_Kursvavholder_Id );
               }
           }
       }
      catch (SQLException ex) {
            System.out.println("Feil ved henting av database:  " + ex.getMessage());
        }
        return null;
    }
    //Metode som henter ut alle kursavholdere
    public List<KursavholderDTO> hentAlleKursavholdere() {
        List<KursavholderDTO> alleKursavholdere = new ArrayList<>();

        String sqlHentAlleKursavholdere = "SELECT kursavholder.kursavholder_id, kursavholder.Navn, " +
                "adresse_husflidslag.id, adresse_husflidslag.Adresse_Kursvavholder_Id, " +
                "adresse_husflidslag.Adresse_husflidslag " +
                "FROM kursavholder " +
                "JOIN adresse_husflidslag ON kursavholder.kursavholder_id = adresse_husflidslag.Adresse_Kursvavholder_Id ";

        try (Connection connection = DatabaseKobling.FaaTilkoblingen()){
            PreparedStatement pstmt = connection.prepareStatement(sqlHentAlleKursavholdere);
            ResultSet rs = pstmt.executeQuery();

            {
                while (rs.next()){
                    int id = rs.getInt("kursavholder_id");
                    String navn = rs.getString("Navn");
                    int addresseID = rs.getInt("Adresse_Kursvavholder_Id");
                    String adresse = rs.getString("Adresse_husflidslag");

                    //Lager kursavholder-objektet for hver rad og legger det til i listen vår.
                    //Det som skjer er at for hver runde i loopen henter vi ut dataen fra kolonnene,
                    //også deretter lager vi et ferdig Kursavholder- objekt som vi dytter inn i liste arrayen våres
                    KursavholderDTO dto = new KursavholderDTO(id, navn, adresse,addresseID);
                    alleKursavholdere.add(dto);
                }
                return alleKursavholdere;
        }

        } catch (SQLException ex) {
            System.out.println("Feil ved henting av kursavholdere:  " + ex.getMessage());
        }
        return null;
    }

    //Metode for å kunne legge til nye kursavholdere
    public void leggTilKursavholder( String navn, String adresse) {
        String sqlNyKursavholder = "INSERT INTO kursavholder (Navn) VALUES (?) ";
        String sqlAdresse = "INSERT INTO adresse_husflidslag (Adresse_Kursvavholder_Id, Adresse_husflidslag) VALUES (?, ?)";

        try { Connection connection = DatabaseKobling.FaaTilkoblingen();
            connection.setAutoCommit(false);
            int nyKursavholder_id = -1; // start ID som vi bruker for å vente den ekte ID fra databasen,
            // dersom alt går bra og vi får en generet ID og da kan vi oppdatere -1 med den nye kursavholder ID-en

            //Set inn i kursavholder og fanger opp den genererte ID-en
            try (PreparedStatement pstmtKursavholder = connection.prepareStatement(sqlNyKursavholder, Statement.RETURN_GENERATED_KEYS)) {
                pstmtKursavholder.setString(1, navn);
                pstmtKursavholder.executeUpdate();

                try (ResultSet rs = pstmtKursavholder.getGeneratedKeys()) {
                    if (rs.next()) {
                        nyKursavholder_id = rs.getInt(1);
                    }
                }
            }
            //sett in adresseen ved hjelp av den nye ID-en hvis den ble generert
            if (nyKursavholder_id != -1) {
                try (PreparedStatement pstmAdresse = connection.prepareStatement(sqlAdresse)) {
                    pstmAdresse.setInt(1, nyKursavholder_id);
                    pstmAdresse.setString(2, adresse);
                    pstmAdresse.executeUpdate();
                }
            }
            connection.commit();
            System.out.println("Ny kursavholder: " + navn + " ble lagret");
        } catch (SQLException ex) {

            //hvis noe feiler, så vil vi angre alt med Rollback
            try {Connection connection = DatabaseKobling.FaaTilkoblingen();
                connection.rollback();
                System.out.println("Det her gått noe galt, ruller tilbake endringen: " + ex.getMessage());
            } catch (SQLException exRollback) {
                System.out.println("Feil ved Rollback: " + exRollback.getMessage());
            }
        } finally {
            //Set tilkoblingen tilbake til standard
            try {Connection connection = DatabaseKobling.FaaTilkoblingen();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Feil ved tilbakebestilling av autoCommit: " + ex.getMessage());
            }
        }
    }



    //Metode for å kunne Oppdatere
    public void oppdaterKursavholder( int kursavholder_id, String nyttNavn, String nyAdresse) {
        String sqlNavn = "UPDATE kursavholder SET Navn = ? WHERE kursavholder_id = ?";
        String sqlAdresse = "UPDATE adresse_husflidslag SET Adresse_husflidslag = ? WHERE Adresse_Kursvavholder_Id = ?";

        try{Connection connection = DatabaseKobling.FaaTilkoblingen();
            connection.setAutoCommit(false);
        try(PreparedStatement pstmtNavn = connection.prepareStatement(sqlNavn)){
            pstmtNavn.setString(1, nyttNavn);
            pstmtNavn.setInt(2, kursavholder_id);
            pstmtNavn.executeUpdate();
        }
        try(PreparedStatement pstmtAdresse = connection.prepareStatement(sqlAdresse)){
            pstmtAdresse.setString(1,nyAdresse);
            pstmtAdresse.setInt(2,kursavholder_id);
            pstmtAdresse.executeUpdate();
        }
        connection.commit();
            System.out.println("Kursavholder med ID " + kursavholder_id + " ble oppdatert");
        }
        catch (SQLException ex) {
            System.out.println("Feil ved oppdatering av kursavholder, ruller tilbake: " + ex.getMessage());

            try {Connection connection = DatabaseKobling.FaaTilkoblingen();
                connection.rollback();
            } catch (SQLException rollbackEX) {
                System.out.println("Feil ved Rollback" + rollbackEX.getMessage());
            }
        }
        finally {
            try {Connection connection = DatabaseKobling.FaaTilkoblingen();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Feil ved autoCommit: " + ex.getMessage());
            }
        }
    }

    //Metode for sletting av kursavholdere og tilhørende adresse
    public void deleteKursavholder(int kursavholder_id) {
        String sqlDeleteAdresse = "DELETE FROM  adresse_husflidslag WHERE Adresse_Kursvavholder_Id = ?";
        String sqlDeleteKursavholder = "DELETE FROM kursavholder WHERE kursavholder_id = ?";

        try{Connection connection = DatabaseKobling.FaaTilkoblingen();
            connection.setAutoCommit(false);

            try(PreparedStatement ptsmtAdresse = connection.prepareStatement(sqlDeleteAdresse)){
                ptsmtAdresse.setInt(1,kursavholder_id);
                ptsmtAdresse.executeUpdate();
            }

            try(PreparedStatement pstmtKursavholder = connection.prepareStatement(sqlDeleteKursavholder)){
                pstmtKursavholder.setInt(1,kursavholder_id);
                pstmtKursavholder.executeUpdate();
            }

            connection.commit();
            System.out.println("Kursavholder med ID " + kursavholder_id + " Og tilhørende adresse ble slettet");
        }
        catch (SQLException ex) {
            try{Connection connection = DatabaseKobling.FaaTilkoblingen();
                connection.rollback();
                System.out.println("Feil under sletting, ruller tilbake endringen: " + ex.getMessage());
            }catch (SQLException exRollback) {
                System.out.println("Feil under rollback: " + exRollback.getMessage());
            }
        }finally {
            try {
                Connection connection = DatabaseKobling.FaaTilkoblingen();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Feil under autoCommit: " + ex.getMessage());
            }
        }
    }










































/*

    //_________________________________________________________________
    //NY METODE: Henter alle kursavholdere ferdig mappet til DTO
    //_________________________________________________________________
    public List<KursavholderDTO> hentAlleKursavholdereDto() {
        List<Kursavholder> raaData = hentAlleKursavholdere();
        return raaData.stream().map(k -> new KursavholderDTO
                (k.Kursavholder_id(), k.getNavn(), k.getNavn(), k.getAdresse_Kursvavholder_Id())).toList();
    }
    // Metode for å hente én enkelt kursavholder ferdig mappet til DTO
    public KursavholderDTO hentKursavholderDto(Connection connection, int kursavholderId) {
        KursavholderDTO k = hentKursavholder(kursavholderId);
        if (k != null) {
            return new KursavholderDTO(k.getId(), k.getNavn(), k.getAdresse(), k.getAddressId());
        }
        return null; // Returnerer null hvis den ikke finnes
    }

*/

    //_________________________________________________________________________________
    //    Metode for validering av ugyldig input som tomme navn, adresse eller negative ID-er
    //_________________________________________________________________________________

    //Dette er for operasjoner som ikke trenger ID ved oppretting enda, siden databasen lager id automatisk
    public void validerKursavholder( String navn, String adresse) {
        if (navn == null ||navn.isBlank()) {
            throw new IllegalArgumentException("Navnet kan ikke være tomt");
        }if (adresse == null || adresse.isBlank()) {
            throw new IllegalArgumentException("Adressen kan ikke være tomt");
        }
    }
    //Dette er for operasjoner som krever gyldig ID som sletting, henting og oppdatering
    public void validerID(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID må være et positivt tall");
        }
    }
}





