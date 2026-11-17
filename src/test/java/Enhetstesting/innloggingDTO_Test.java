package Enhetstesting;

import nettside.DTO.Innlogging_DTO;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class innloggingDTO_Test {

    @Test
    public void innloggingTest(){

        Innlogging_DTO test = new Innlogging_DTO();

        test.setEpost("mika@gmail.com");
        test.setPassord("1234ABC!");

        assertEquals("mika@gmail.com",test.getEpost());
        assertEquals("1234ABC!",test.getPassord());

    }
}
