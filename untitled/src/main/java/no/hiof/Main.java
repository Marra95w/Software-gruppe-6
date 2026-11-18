package no.hiof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import config.Databasekobling;
import repositories.Ansattregister;

import java.sql.Connection;
import java.sql.SQLException;

// Forteller Spring Boot at dette er hovedklassen som starter hele applikasjonen
@SpringBootApplication
public class Main {

    // Hovedmetoden (startpunktet) som kjører når programmet starter
    public static void main(String[] args) {

        // Starter selve Spring Boot-serveren (starter innebygd webserver, vanligvis på port 8080)
        SpringApplication.run(Main.class, args);

        System.out.println("Prøver å koble til databasen...");

        // Tester databaseforbindelsen ved oppstart i en try-with-resources-blokk for å lukke den automatisk etterpå
        try (Connection conn = Databasekobling.kobleTil()) {

            // Sjekker om tilkoblingen ikke er null og faktisk er åpen
            if (conn != null && !conn.isClosed()) {
                System.out.println("Vellykket tilkobling til databasen!");
            } else {
                System.out.println("Feil: Kunne ikke opprette forbindelse.");
            }

        } catch (SQLException e) {

            // Fanger opp eventuelle databasefeil som oppstår under tilkoblingstest og skriver ut feilmeldingen
            System.err.println("En databasefeil oppstod under kjøring: " + e.getMessage());
        }
    }
}