package basic;

import org.testng.Assert;
import org.testng.annotations.Test;

public class StringShuffleTest {
    @Test
    public void testSimpleValidShuffleIsDetected() {
        Assert.assertTrue(new StringShuffle().isValidShuffle("ab", "a", "b"));
    }

    @Test
    public void testInvalidShuffleIsDetected() {
        Assert.assertFalse(new StringShuffle().isValidShuffle("ab", "ac", "b"));
    }

    @Test
    public void testInvalidShuffleDueToMissingCharacterIsDetected() {
        Assert.assertFalse(new StringShuffle().isValidShuffle("ababd", "ab", "ab"));
    }

    @Test
    public void testValidShuffle_OfTwoStringsOfDifferentLengths_IsDetected() {
        Assert.assertTrue(new StringShuffle().isValidShuffle("ababd", "ab", "abd"));
    }

    @Test
    public void testValidShuffle_OfTwoStringsOfDifferentLengths_IsDetected_whenInputOrderIsChanged() {
        Assert.assertTrue(new StringShuffle().isValidShuffle("ababd", "abd", "ab"));
    }
}
