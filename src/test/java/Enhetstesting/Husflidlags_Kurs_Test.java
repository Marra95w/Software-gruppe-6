package Enhetstesting;

import nettside.Modell.husflidslag_kurs;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class Husflidlags_Kurs_Test {

    @Test
    public void testIdOgTittel(){
        husflidslag_kurs  husflidslagKurs = new husflidslag_kurs();

        husflidslagKurs.setId(10);
        husflidslagKurs.setTittel("Enhets test");

        assertEquals(10,husflidslagKurs.getId());
        assertEquals("Enhets test",husflidslagKurs.getTittel());
    }


}
