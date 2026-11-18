package Enhetstesting;

import nettside.Modell.medlem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class medlem_Test {

    @Test
    public void medlemInfoTest(){
        medlem test_medlem = new medlem();

        test_medlem.setNavn("Kari");
        test_medlem.setEtternavn("Nord");
        test_medlem.setEpost("kari@proton.no");



        assertEquals("Kari",test_medlem.getNavn());
        assertEquals("Nord",test_medlem.getEtternavn());
        assertEquals("kari@proton.no",test_medlem.getEpost());
    }

}
