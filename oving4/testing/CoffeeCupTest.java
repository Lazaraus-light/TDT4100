package oving4.testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CoffeeCupTest {
    private CoffeeCup cup;

    @BeforeEach
    void setUp() {
        
        cup = new CoffeeCup(200.0, 100.0);
    }

    @AfterEach
    void tearDown() {
        cup = null;
    }
    
    @Test
    @DisplayName("Test default konstruktør")
    void testDefaultConstructor() {
        CoffeeCup defaultCup = new CoffeeCup();
        assertEquals(0.0, defaultCup.getCapacity(), "Standardkapasiteten skal være 0.0");
        assertEquals(0.0, defaultCup.getCurrentVolume(), "Standardvolumet skal være 0.0");
    }
    
    @Test
    @DisplayName("Test parameterisert konstruktør med gyldige verdier")
    void testParameterizedConstructorValid() {
        CoffeeCup paramCup = new CoffeeCup(250.0, 150.0);
        assertEquals(250.0, paramCup.getCapacity(), "Kapasiteten skal være 250.0");
        assertEquals(150.0, paramCup.getCurrentVolume(), "Volumet skal være 150.0");
    }
    
    @Test
    @DisplayName("Test parameterisert konstruktør med ugyldig kapasitet")
    void testParameterizedConstructorInvalidCapacity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CoffeeCup(-100.0, 50.0);
        });
        assertEquals("Illegal capacity given.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test parameterisert konstruktør med ugyldig volum")
    void testParameterizedConstructorInvalidVolume() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CoffeeCup(200.0, 250.0);
        });
        assertEquals("Illegal volume given.", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test getCapacity-metoden")
    void testGetCapacity() {
        assertEquals(200.0, cup.getCapacity(), "Kapasiteten skal være 200.0");
    }
    
    @Test
    @DisplayName("Test getCurrentVolume-metoden")
    void testGetCurrentVolume() {
        assertEquals(100.0, cup.getCurrentVolume(), "Volumet skal være 100.0");
    }
    
    @Test
    @DisplayName("Test increaseCupSize-metoden")
    void testIncreaseCupSize() {
        cup.increaseCupSize(50.0);
        assertEquals(250.0, cup.getCapacity(), "Kapasiteten skal økes til 250.0");
    }
    
    @Test
    @DisplayName("Test drinkCoffee-metoden")
    void testDrinkCoffee() {
        cup.drinkCoffee(50.0);
        assertEquals(50.0, cup.getCurrentVolume(), "Volumet skal være 50.0 etter at 50.0 er drukket");
    }
    
    @Test
    @DisplayName("Test drinkCoffee-metoden med for mye kaffe")
    void testDrinkCoffeeTooMuch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cup.drinkCoffee(150.0);
        });
        assertEquals("You can't drink that much coffee!", exception.getMessage());
    }
    
    @Test
    @DisplayName("Test fillCoffee-metoden")
    void testFillCoffee() {
        cup.fillCoffee(50.0);
        assertEquals(150.0, cup.getCurrentVolume(), "Volumet skal være 150.0 etter påfylling");
    }
    
    @Test
    @DisplayName("Test at koppen ikke kan fylles over sin kapasitet")
    void testFillCoffeeTooMuch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cup.fillCoffee(150.0);
        });
        assertEquals("You just poured coffee all over the table. Good job.", exception.getMessage());
    }
}
