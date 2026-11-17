package nettside.Service;

import nettside.Modell.kurs_paamelding;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Database_paamelding_logikkTest {

    @Test
    public void melde_opp_i_kurs(){

        Database_paamelding_Service teste_oppmelding = new Database_paamelding_Service();

        kurs_paamelding variabel_testing = teste_oppmelding.registrer_kunde_i_kurs(3,4,3200);

        assertEquals(4,variabel_testing.getKursId());
        assertEquals(3,variabel_testing.getKundeId());
        assertEquals(3200,variabel_testing.getPris_fakturert());
    }

}