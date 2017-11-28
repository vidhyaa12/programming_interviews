package basic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vidhyaa on 28/11/17.
 */
public class LongestSubarrayWithDistinctEntries {
    public static int longestSubarrayWithDistinctEntries(List<Integer> A) {
        Map<Integer, Integer> numToMostRecentOccurrenceIdx = new HashMap<>();
        int longestDupFreeSubarrayStartIdx = 0, result = 0;

        for (int i = 0; i < A.size(); i++) {
            Integer dupIdx = numToMostRecentOccurrenceIdx.put(A.get(i), i);

            if (dupIdx != null) { // A.get(i) appeared before. Did it appear in the longest current subarray?
                if (dupIdx >= longestDupFreeSubarrayStartIdx) {
                    result = Math.max(result, i - longestDupFreeSubarrayStartIdx);
                    longestDupFreeSubarrayStartIdx = dupIdx + 1;
                }
            }
        }
        return Math.max(result, A.size() - longestDupFreeSubarrayStartIdx);
    }
}
