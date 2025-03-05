package oving4.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoffeeCupTest {
    private CoffeeCup cup;

    @BeforeEach
    public void setup(){
        cup= new CoffeeCup();
    }

    @Test
    @DisplayName("Sjekk noe")
    public void sjekkIncreaseCupSize(){
        cup.increaseCupSize(20);
        assertEquals(20,cup.getCapacity());

        assertEquals(0,cup.getCurrentVolume());

        cup.fillCoffee(30);
        assertEquals(30,cup.getCurrentVolume());
        cup.drinkCoffee(20);
        assertEquals(10,cup.getCurrentVolume());




    };

    
}
