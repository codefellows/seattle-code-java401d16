package ZorksPettingZoo;

import ZorksPettingZoo.animals.Goat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoatTest {
    @Test void goatCreationTest(){
        // Arrange
        Goat newGoat = new Goat(false, 2, 3, "Larry", "brown");
        // Act
        // Assert
        assertSame("Larry", newGoat.getName());
    }
}
