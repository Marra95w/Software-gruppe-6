package Database;

import Klasser.husflidslag_kurs;
import Logikk.Database_Kurs_Logikk;
import io.javalin.Javalin;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> {
                    it.anyHost();
                });
            });
        }).start(7001);

        app.get("/kurs_tabell", ctx -> { // java prøvde å finne en kurs tabell som ikke finnes
            Database_Kurs_Logikk logikk = new Database_Kurs_Logikk();
            List<husflidslag_kurs> alleKurs = logikk.hentAlleKurs();
            ctx.json(alleKurs);
        });

        // Fikk dette fra Kunstintelligens. Samme som det som står øverst, men den
        // bruker url og henter et spesific kurs.
        app.get("/kurs/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Database_Kurs_Logikk logikk = new Database_Kurs_Logikk();
            husflidslag_kurs kurs = logikk.hentKursById(id);

            if (kurs == null) {
                ctx.status(404).json(Map.of("feil", "Kurs ikke funnet"));
            } else {
                ctx.json(kurs);
            }
        });

        System.out.println("Server kjører på http://localhost:7001");
    }

}