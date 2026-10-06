package Logikk;

import Database.Database_Connection;
import Klasser.husflidslag_kurs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database_Kurs_Logikk {

    public List<husflidslag_kurs> hentAlleKurs() {
        List<husflidslag_kurs> kursListe = new ArrayList<>();

        String sql = "SELECT * FROM Kurs";

        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                husflidslag_kurs kurs = new husflidslag_kurs(
                        rs.getString("tittel"),
                        rs.getTimestamp("startDatoTid").toLocalDateTime(),
                        rs.getTimestamp("sluttDatoTid").toLocalDateTime(),
                        rs.getInt("kursavholderId"),
                        rs.getDouble("prisMedlem"),
                        rs.getDouble("prisIkkeMedlem"),
                        rs.getString("beskrivelse"),
                        true,
                        rs.getInt("maksDeltagere")
                );
                kursListe.add(kurs);
            }

        } catch (SQLException e) {
            System.err.println("Feil ved henting av kurs: " + e.getMessage());
        }

        return kursListe;
    }
}

//Erlind
// package Logikk;

// import java.util.List;

// public class Database_Kurs_Logikk {

//     private List<Database_Kurs_Logikk> hente_kurs;


//     public List<Database_Kurs_Logikk> getHente_kurs(){
//         return hente_kurs;
//     }


// }
