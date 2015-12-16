package basic;

import com.beust.jcommander.internal.Maps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PairWithSumTest {
    @Test
    public void testFindPairs() {
        List<Integer> numbers = Arrays.asList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        PairWithSum p = new PairWithSum();

        Map<Integer, Integer> expectedResult = Maps.newHashMap();
        expectedResult.put(0, 100);
        expectedResult.put(10, 90);
        expectedResult.put(20, 80);
        expectedResult.put(30, 70);
        expectedResult.put(40, 60);
        expectedResult.put(50, 50);
        expectedResult.put(60, 40);
        expectedResult.put(70, 30);
        expectedResult.put(80, 20);
        expectedResult.put(90, 10);
        expectedResult.put(100, 0);

        Assert.assertEquals(expectedResult, p.findPairs(numbers, 100));
    }
}
