package enterNewLinks.randomGenerator;

import hr.riteh.dominik.internship.util.RandomGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomGeneratorTest {
    @Test
    public void testGetAlphaNumericString() {

        String randomString = RandomGenerator.getAlphaNumericString();
        assertEquals(8, randomString.length());
        assertTrue(randomString.matches("[0123456789abcdefghijklmnopqrstuvwxyz]+"));
    }
}






