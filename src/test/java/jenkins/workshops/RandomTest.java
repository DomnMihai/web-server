package jenkins.workshops;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTest {

    @Test
    public void testRandomFail() {
        final Random random = new Random();
        assertTrue(random.nextFloat() < 0.9, "Random fail");
    }
}
