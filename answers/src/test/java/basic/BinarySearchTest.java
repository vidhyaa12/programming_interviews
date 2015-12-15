package basic;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BinarySearchTest {

    @Test
    public void testBinarySearch() {

        int[] numberArray = new int[50];
        for (int i = 0; i < 50; i++) {
            numberArray[i] = i;
        }

        int idx = BinarySearch.search(numberArray, 0, 50 - 1, 25);
        Assert.assertEquals(idx, 25);
    }
}