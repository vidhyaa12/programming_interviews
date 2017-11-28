package hard;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    HashMap<Integer, Integer> cacheKeysToVals;
    HashMap<Integer, Integer> keyToFrequency;
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    int capacity;
    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        cacheKeysToVals = new HashMap<Integer, Integer>();
        keyToFrequency = new HashMap<Integer, Integer>();
        lists = new HashMap<Integer, LinkedHashSet<Integer>>();
        lists.put(1, new LinkedHashSet<Integer>());
    }

    public int get(int key) {
        if (!cacheKeysToVals.containsKey(key)) {
            return -1;
        }

        int frequency = keyToFrequency.get(key);
        keyToFrequency.put(key, frequency + 1);
        lists.get(frequency).remove(key);

        if (frequency == min && lists.get(frequency).size() == 0) {
            min++;
        }

        if (!lists.containsKey(frequency + 1)) {
            lists.put(frequency + 1, new LinkedHashSet<Integer>());
        }

        lists.get(frequency + 1).add(key);
        return cacheKeysToVals.get(key);
    }

    public void set(int key, int value) {
        if (capacity <= 0) {
            return;
        }

        if (cacheKeysToVals.containsKey(key)) {
            cacheKeysToVals.put(key, value);
            get(key);
            return;
        }

        if (cacheKeysToVals.size() >= capacity) {
            int evit = lists.get(min).iterator().next();
            lists.get(min).remove(evit);
            cacheKeysToVals.remove(evit);
        }

        cacheKeysToVals.put(key, value);
        keyToFrequency.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}