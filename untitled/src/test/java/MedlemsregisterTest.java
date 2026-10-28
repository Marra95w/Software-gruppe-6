import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MedlemsregisterTest {

    @Test

//Sjekker at vi kan legge til et medlem i registeret og finne det igjen ved hjelp av ID.

    void testLeggTilOgFinnMedlem() {
        Medlemsregister register = new Medlemsregister();
        medlem m = new medlem(1, "Ola", "Nordmann", "ola@epost.no", 1, 12345678);

        register.leggTilMedlem(m);

        medlem funnet = register.finnMedlemMedId(1);

        assertNotNull(funnet);
        assertEquals("Ola", funnet.getNavn());
    }


    //Sjekker at metoden returnerer en liste med nøyaktig alle medlemmene vi har lagt til.
    @Test
    void testHentAlleMedlemmer() {
        Medlemsregister register = new Medlemsregister();
        medlem m1 = new medlem(1, "Ola", "Nordmann", "ola@epost.no", 1, 12345678);
        medlem m2 = new medlem(2, "Kari", "Hansen", "kari@epost.no", 1, 87654321);

        register.leggTilMedlem(m1);
        register.leggTilMedlem(m2);

        List<medlem> alle = register.hentAlleMedlemmer();

        assertEquals(2, alle.size(), "Listen skal inneholde 2 medlemmer");
        assertTrue(alle.contains(m1));
        assertTrue(alle.contains(m2));
    }


    @Test
    void testFinnMedlemMedIdIkkeFunnet(){
        Medlemsregister register = new Medlemsregister();

        medlem funnet = register.finnMedlemMedId(99);

        assertNull(funnet,"Skal retunerer null hvis medlem ikke finnes");

    }


    @Test
    void testHentIkkeMedlem(){
        Medlemsregister register = new Medlemsregister();
        medlem m3 = new medlem(3, "Martin","Henriksen", "martin@epost.no",3, 74892902);
        register.leggTilMedlem(m3);


        // ID 1 er medlem, men ID 2 og 3 er det ikke
        List<Integer> paameldte = List.of(1, 2, 3);
        List<Integer> ikkeMedlemmer = register.hentIkkeMedlemmer(paameldte);

        assertEquals(2, ikkeMedlemmer.size(), "Skal finne 2 personer som ikke er medlemmer");
        assertTrue(ikkeMedlemmer.contains(2));
        assertTrue(ikkeMedlemmer.contains(3));
        assertFalse(ikkeMedlemmer.contains(1));
    }
}