package Logikk;

import Database.Database_Connection;
import Klasser.husflidslag_kurs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database_Kurs_Logikk {

    public List<husflidslag_kurs> hentAlleKurs() {
        List<husflidslag_kurs> kursListe = new ArrayList<>();

        String sql = " SELECT * FROM kurs_tabell LEFT JOIN kursavholder ON kurs_tabell.kursavholder_id = kursavholder.kursavholder_id LEFT JOIN adresse_husflidslag ON adresse_husflidslag.Adresse_Kursvavholder_Id = kursavholder.Kursavholder_id";

        try (Connection conn = Database_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                //System.out.println("Fant et kurs"); Brukte dette får å teste 
                husflidslag_kurs kurs = new husflidslag_kurs(
                        rs.getString("tittel"),
                        rs.getTimestamp("start_dato_tid").toLocalDateTime(),
                        rs.getTimestamp("slutt_dato_tid").toLocalDateTime(),
                        rs.getString("Navn"),
                        rs.getDouble("pris_medlem"),
                        rs.getDouble("pris_ikke_medlem"),
                        rs.getString("beskrivelse"),
                        true,
                        0
                        //rs.getInt("maksDeltagere") Dette kolumne finnes ikke i tabellen så jeg endret til en fast verdi. Måtte også endre navnet kolumnene til det som var i databasen.
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
