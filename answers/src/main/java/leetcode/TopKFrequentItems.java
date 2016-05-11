package leetcode;

import java.util.*;

public class TopKFrequentItems {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();

        HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();

        for (int i : nums) {
            if (counter.containsKey(i)) {
                counter.put(i, counter.get(i) + 1);
            } else {
                counter.put(i, 1);
            }
        }

        TreeMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(new ValueComparator(counter));
        sortedMap.putAll(counter);

        int i = 0;
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            result.add(entry.getKey());
            i++;
            if (i == k)
                break;
        }

        return result;
    }
}

class ValueComparator implements Comparator<Integer> {
    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public ValueComparator(HashMap<Integer, Integer> m) {
        map.putAll(m);
    }

    public int compare(Integer i1, Integer i2) {
        int diff = map.get(i2) - map.get(i1);

        if (diff == 0) {
            return 1;
        } else {
            return diff;
        }
    }
}