package basic;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class PairWithSumTest {
    @Test
    public void testFindPairs() {
        List<Integer> numbers = Arrays.asList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
        PairWithSum p = new PairWithSum();
        p.findPairs(numbers, 100);
    }
}
