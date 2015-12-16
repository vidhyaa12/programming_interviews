package basic;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Find all pairs of numbers that sum to a given value
 */
public class PairWithSum {
    public void findPairs(List<Integer> numbers, int sum) {
        if (numbers == null || numbers.isEmpty()) {
            return;
        }

        Map<Integer, Integer> numberToReqNum = Maps.newHashMap();

        for (Integer number : numbers) {
            numberToReqNum.put(number, sum - number);
        }

        for (Integer number : numbers) {
            if (numberToReqNum.containsKey(numberToReqNum.get(number))) {
                System.out.println(number + " " + numberToReqNum.get(number));
            }
        }
     }
}
