package Kursavholder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;


public class KursavholderLogikk {

    public  Kursavholder hentKursavholder (Connection connection, int Kursavholder_id){

        String sql = "SELECT kursavholder.kursavholder_id, kursavholder.Navn, adresse_husflidslag.id, adresse_hisflidslag.Adresse_Kursvavholder_Id, adresse_husflidslag.Adresse_husflidslag  " +
                "FROM kursavholder " +
                "JOIN adresse_husflidslag ON kursavholder.id adresse_husflidslag. " +
                "WHERE Kursavholder_id = ? ";





        try(PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setInt(1,Kursavholder_id);
            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    int hentKursavholder_id = rs.getInt("Kursavholder_id");
                    String hentNavn = rs.getString("Navn");
                    //int hentAdresse_Kursvavholder_Id = rs.getInt("Adresse_Kursvavholder_Id");
                    //String hentAdresse_husflidslag =  rs.getString("Adresse_husflidslag");

                    return new Kursavholder(hentKursavholder_id, hentNavn);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Feil ved henting av database:  " + ex.getMessage());
        }
        return  null;
    }
}
