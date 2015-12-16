package basic;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Find all pairs of numbers that sum to a given value
 */
public class PairWithSum {
    public Map<Integer, Integer> findPairs(List<Integer> numbers, int sum) {
        if (numbers == null || numbers.isEmpty()) {
            return null;
        }

        Map<Integer, Integer> numberToReqNum = Maps.newHashMapWithExpectedSize(numbers.size());

        for (Integer number : numbers) {
            numberToReqNum.put(number, sum - number);
        }

        Map<Integer, Integer> result = Maps.newHashMapWithExpectedSize(numbers.size());

        for (Integer number : numbers) {
            if (numberToReqNum.containsKey(numberToReqNum.get(number))) {
                result.put(number, numberToReqNum.get(number));
            }
        }
        return result;
     }
}
