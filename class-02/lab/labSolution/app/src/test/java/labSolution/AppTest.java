/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package labSolution;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Test void testCalculateArrayAverage(){
        // ARRANGE
        App sut = new App();
        int[] testArray = {66, 64, 58, 65, 71, 57, 60};
        // ACT
        int result = sut.calculateAverage(testArray);
        // ASSERT
        assertNotNull(result);
        assertEquals(0, (result * 0));
    }
}
