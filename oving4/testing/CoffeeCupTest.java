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
    @DisplayName("Test getCapacity method")
    void testGetCapacity() {
        assertEquals(200.0, cup.getCapacity(), "Capacity should be 200.0");
    }

    @Test
    @DisplayName("Test getCurrentVolume method")
    void testGetCurrentVolume() {
        assertEquals(100.0, cup.getCurrentVolume(), "Current volume should be 100.0");
    }

    @Test
    @DisplayName("Test increaseCupSize method")
    void testIncreaseCupSize() {
        cup.increaseCupSize(50.0);
        assertEquals(250.0, cup.getCapacity(), "Capacity should be increased to 250.0");
    }

    @Test
    @DisplayName("Test drinkCoffee method")
    void testDrinkCoffee() {
        cup.drinkCoffee(50.0);
        assertEquals(50.0, cup.getCurrentVolume(), "Current volume should be 50.0 after drinking");
    }

    @Test
    @DisplayName("Test drinkCoffee method with excessive amount")
    void testDrinkCoffeeTooMuch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cup.drinkCoffee(150.0);
        });
        assertEquals("You can't drink that much coffee!", exception.getMessage());
    }

    @Test
    @DisplayName("Test fillCoffee method")
    void testFillCoffee() {
        cup.fillCoffee(50.0);
        assertEquals(150.0, cup.getCurrentVolume(), "Current volume should be 150.0 after filling");
    }

    @Test
    @DisplayName("Test overfilling the cup")
    void testFillCoffeeTooMuch() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cup.fillCoffee(150.0);
        });
        assertEquals("You just poured coffee all over the table. Good job.", exception.getMessage());
    }
}
