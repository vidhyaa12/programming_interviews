package basic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by vidhyaa on 24/10/18.
 */
class Item
{
    long ts;
    String name;

    public Item(long ts, String name) {
        this.name  = name;
        this.ts = ts;
    }
}

class ItemAPI
{
    Map<String, Integer> itemIdToFrq;
    Map<String, Long> itemIdToLatestTimestamp;
    PriorityQueue<String> pq;

    public ItemAPI() {
        itemIdToFrq = new HashMap<>();
        itemIdToLatestTimestamp = new HashMap<>();
        pq = new PriorityQueue<>((a, b) -> itemIdToFrq.get(a) ==
                itemIdToFrq.get(b) ? itemIdToLatestTimestamp.get(b) >= itemIdToLatestTimestamp.get(a) ? 1 : -1
                : itemIdToFrq.get(b) - itemIdToFrq.get(a));
    }

    // O(logN) time complexity where N is the number of elements in the heap
    public void put(Item t) {
        String itemName = t.name;
        itemIdToLatestTimestamp.put(t.name, t.ts); // O(1) time complexity
        itemIdToFrq.put(t.name, 1 + itemIdToFrq.getOrDefault(itemName, 0)); // O(1) time complexity
        pq.add(itemName); // time complexity of add is O(logN) where N is the number of elements in the heap
    }

    // O(1) - Time complexity
    public int getFrequency(String s) {
        return itemIdToFrq.get(s);
    }

    // Time complexity - O(1)
    public String top() {
        return pq.peek();
    }

    public static void main(String[] args) {
        // (A,1), (B,2), (C, 10), (A, 11), (B, 13)
        List<Item> items = Arrays.asList(new Item(1, "A"), new Item(2, "B"),
                new Item(10, "C"), new Item(11, "A"), new Item(13, "B"));

        ItemAPI itemAPI = new ItemAPI();

        for (Item item : items) {
            itemAPI.put(item);
        }

        for (String itemName : itemAPI.itemIdToFrq.keySet()) {
            System.out.println(itemName + " " + itemAPI.getFrequency(itemName));
        }

        System.out.println(itemAPI.top());
    }
}

