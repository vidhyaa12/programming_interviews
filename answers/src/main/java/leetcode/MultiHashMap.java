package leetcode;

import java.util.*;

public class MultiHashMap {

    Map<String, Map<Long, Integer>> mapping;
    Map<Long, Integer> globalValueFrequencies;

    public MultiHashMap() {
        mapping = new HashMap<String, Map<Long, Integer>>();
        globalValueFrequencies = new HashMap<Long, Integer>();
    }

    /* insert a value into multihashmap - duplicate values allowed*/
    public void put(String key, Long value) {
        if (!mapping.containsKey(key)) {
            mapping.put(key, new HashMap<Long, Integer>());
        }

        Map<Long, Integer> valueMapForKey = mapping.get(key);
        Integer frqForKey = valueMapForKey.containsKey(value) ? valueMapForKey.get(value) : 0;
        valueMapForKey.put(value, frqForKey + 1);

		/*update global frequency for value*/
        Integer globalFrqForValue = globalValueFrequencies.containsKey(value) ? globalValueFrequencies.get(value) : 0;
        globalValueFrequencies.put(value, globalFrqForValue + 1);
    }

    /**/
    public void delete(String key, Long value) {
        Map<Long, Integer> valueMapForKey = mapping.get(key);

		/*Signal no-op if required , exceptions etc*/
        if (valueMapForKey == null) {
            return;
        }

        Integer frqForKey = valueMapForKey.containsKey(value) ? valueMapForKey.get(value) : 0;
        if (frqForKey == 1) {
            valueMapForKey.remove(value);
        } else {
            valueMapForKey.put(value, frqForKey - 1);
        }

        //Throw exceptions if required
        //ideally 2 data-structures should be consistent
		/*update global frequency for value*/
        Integer globalFrqForValue = globalValueFrequencies.containsKey(value) ? globalValueFrequencies.get(value) : 0;
        if (globalFrqForValue == 1) {
            globalValueFrequencies.remove(value);
        } else {
            globalValueFrequencies.put(value, globalFrqForValue - 1);
        }
    }

    public Map<Long, Integer> get(String key) {
        return mapping.get(key);
    }

    public boolean containsValue(Long v) {
        return globalValueFrequencies.containsKey(v);
    }

    public static void runTest1() {
        MultiHashMap multiHashMap = new MultiHashMap();
        multiHashMap.put("K1", 1L);
        multiHashMap.put("K1", 1L);
        multiHashMap.put("K1", 1L);
        multiHashMap.put("K1", 2L);
        multiHashMap.put("K1", 3L);

        for (long i=0;i<5;i++) {
            System.out.println("multiHashMap.containsValue(" + i + ") = " + multiHashMap.containsValue(i));
        }

        multiHashMap.put("K2", 3L);
        multiHashMap.put("K2", 3L);
        multiHashMap.put("K2", 5L);

        multiHashMap.delete("K1", 1L);
        System.out.println("multiHashMap.containsValue(1L) = " + multiHashMap.containsValue(1L));
        multiHashMap.delete("K1", 1L);
        System.out.println("multiHashMap.containsValue(1L) = " + multiHashMap.containsValue(1L));
        multiHashMap.delete("K1", 1L);
        System.out.println("multiHashMap.containsValue(1L) = " + multiHashMap.containsValue(1L));
        multiHashMap.delete("K1", 1L);
        System.out.println("multiHashMap.containsValue(1L) = " + multiHashMap.containsValue(1L));


    }

    public static void main(String args[]) {
        runTest1();
    }
}

