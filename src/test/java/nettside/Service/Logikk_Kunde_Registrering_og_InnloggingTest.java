package nettside.Service;

import nettside.DTO.Innlogging_DTO;
import nettside.Modell.medlem;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Logikk_Kunde_Registrering_og_InnloggingTest {

    //----------------------------------Integrasjonstesting---------------------------------//


    @Test
    public void teste_legg_medlem(){

        Kunde_Registrering_og_Innlogging_Service testing = new Kunde_Registrering_og_Innlogging_Service();

        medlem variabel_testing = testing.leggTilMedlem("Johan","Granch","johangra@me.no",2,93051804,"passord12!");

        assertNotNull(variabel_testing);
        assertEquals("Johan",variabel_testing.getNavn());
        assertEquals("Granch",variabel_testing.getEtternavn());
        assertEquals("johangra@me.no",variabel_testing.getEpost());


    }


    @Test
    public void teste_sjekk_opplysninger(){

        Kunde_Registrering_og_Innlogging_Service testing = new Kunde_Registrering_og_Innlogging_Service();

        Innlogging_DTO variabel_testing = testing.sjekk_opplysninger("johangra@me.no","passord12!");

        assertEquals("johangra@me.no",variabel_testing.getEpost());

        /*Grunnen jeg satt null, er fordi den er også null i logikken. grunnen er for å ikke returnere passordet
        * ekstra sikkerhet*/
        assertEquals(null,variabel_testing.getPassord());

    }




}