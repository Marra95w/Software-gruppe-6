package nettside.Service;

import nettside.Modell.husflidslag_kurs;
import org.junit.Test;

import java.util.List;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/*I denne klassen ønsker jeg å teste funksjonene som vi har klart å koble med frontenden, desverre har vi ikke klart å koble alt
* men det kommer til å bli gjennomført på et senere tidspunkt. Men allikevel tester vi det som funker nå også legger vi til mer testing av de
* andre funksjonene. Viktig å begrunne at dette skjer på grunn av dårlig plannligging nå i sluttfasen og litt dårlig tid.*/

public class Database_Kurs_ServiceTest {


    //------------------------INTEGRASJONSTESTING------------------------------------------//

    // Integrasjontest: hente kurs fra db funskjonen er den som henter alle kursene fra databasen.
    @Test
    public void test_henter_kurs_fra_db(){

        Database_Kurs_Service teste_kurs_henting = new Database_Kurs_Service();

        List<husflidslag_kurs> variabel_for_å_sjekke = teste_kurs_henting.hent_alle_kurs_fra_db();

        assertNotNull(variabel_for_å_sjekke,"Listen skal ikke være null");
        assertFalse(variabel_for_å_sjekke.isEmpty(),"Listen kan ikke være tom");
        assertTrue(variabel_for_å_sjekke.size()>0,"Kurs finnes mer enn 0");

    }

    @Test
    public void test_hent_spesifikk_kurs_fra_db(){
        Database_Kurs_Service teste_enkelt_kurs_henting = new Database_Kurs_Service();

        husflidslag_kurs variabel_for_å_sjekke = teste_enkelt_kurs_henting.hent_kurs_fra_db(1);

        assertNotNull(variabel_for_å_sjekke,"Dette er ikke være null");
        assertTrue(variabel_for_å_sjekke.getId()==1,"Det skal stemme");
        assertFalse(variabel_for_å_sjekke.getTittel().isEmpty(),"Dette skal ikke være tom!");
    }


    @Test
    public void lage_ny_kurs(){

        Database_Kurs_Service teste_innsetting_av_nye_kurs = new Database_Kurs_Service();

        /*Desverre kan vi ikke sette dato og klokkeslett sammen som tallformat siden T brukes for å kombinere det,
        så vi er nødt til å gjøre om til string ifølge Java dokumentasjonen, men dette betyr også at jeg må endre i sett_kurs_i_db() ellers vil admin
         ikke kunne legge kurs*/

        LocalDateTime startdato = LocalDateTime.parse("2026-12-27T10:00:00");
        LocalDateTime sluttdato = LocalDateTime.parse("2026-12-27T14:00:00");


        husflidslag_kurs variabel_for_å_sjekke = teste_innsetting_av_nye_kurs.sett_kurs_i_db("Tittel test",startdato,sluttdato,2,500,800,"Test beskrivelse");

        //pass på med å kjøre den fordi denne eksemplen er allerede registrert i db
        assertEquals("Tittel test",variabel_for_å_sjekke.getTittel());//bruker equals fordi jeg mener at den vil hente titelen noe som betyr at kurset er opprettet
    }





    @Test
    public void oppdatere_en_kurs(){

        Database_Kurs_Service test_oppdatere_kurs = new Database_Kurs_Service();

        LocalDateTime startdato = LocalDateTime.parse("2026-12-27T10:00:00");
        LocalDateTime sluttdato = LocalDateTime.parse("2026-12-27T14:00:00");

        husflidslag_kurs variabel_for_å_sjekke = test_oppdatere_kurs.oppdatere_kurs_i_db("Test test",startdato,sluttdato,3,500,900,"Test",5);


    }

    @Test
    public void slett_en_kurs(){
        Database_Kurs_Service teste_slett_en_kurs = new Database_Kurs_Service();

        husflidslag_kurs variabel_for_å_sjekke = teste_slett_en_kurs.slett_kurs_fra_db("Tittel test");

        //sjekker mot null ettersom kursen blir borte og da vil vi få ingenting og dette vil bekrefte at kursen er slettet
        assertTrue(variabel_for_å_sjekke==null);

    }


}