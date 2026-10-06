package Database;

import Klasser.husflidslag_kurs;
import Logikk.Database_Kurs_Logikk;
import io.javalin.Javalin;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        app.get("/kurs", ctx -> {
            Database_Kurs_Logikk logikk = new Database_Kurs_Logikk();
            List<husflidslag_kurs> alleKurs = logikk.hentAlleKurs();
            ctx.json(alleKurs);
        });

        System.out.println("Server kjører på http://localhost:7000");
    }
}