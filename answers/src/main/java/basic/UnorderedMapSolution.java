package basic;

/**
 * Created by vidhyaa on 06/11/18.
 */
/*
 * Implement an associative map container.
 */

class UnorderedMapSolution {
    private static final Integer MAX_CAPACITY = 10000;
    static final Bucket[] buckets = new Bucket[10000];

    static class UnorderedMapImpl<String, Integer> {

        /**
         * Trivial hash function
         * @param key
         * @return hash value
         */
        int hash(String key) {
            return key.hashCode();
        }

        public int getBucketIdx(String key) {
            return hash(key) % buckets.length;
        }

        public ListNode findNode(Bucket bucket, String key) {
            ListNode head = bucket.head;
            ListNode curr = bucket.head; ListNode prev = null;

            while (curr != null && curr.key != key) {
                prev = curr;
                curr = curr.next;
            }
            return prev;
        }

        /**
         * Get value for key, or null if key is missing
         * @param key
         * @return value
         */
        Integer get(String key) {
            int bucketIdx = getBucketIdx(key);
            Bucket bucket = buckets[bucketIdx];

            if (bucket == null) {
                return null;
            }

            ListNode node = findNode(bucket, key);

            if (node.next != null && node.next.key == key) {
                return (Integer) node.next.val;
            }
            return null;
        }

        /**
         * Put key and value in map.
         * @param key
         * @param value
         * @return this
         */
        UnorderedMapImpl<String, Integer> put(String key, Integer value) {
            int bucketIdx = getBucketIdx(key);
            Bucket bucket = buckets[bucketIdx];

            if (bucket == null) {
                bucket = new Bucket();
                buckets[bucketIdx] = bucket;
            }

            ListNode currHead = bucket.head;

            if (currHead.next == null) {
                currHead.next = new ListNode(key, value);
            } else {
                currHead.next.val = value;
            }

            return this;
        }
    }


    /**
     * Testing code
     */
    public static void main(String[] args) {


        System.out.println("Testing java unordered map implmentation");


        UnorderedMapImpl<String, Integer> map = new UnorderedMapImpl<>();

        map.put("one", 1)
                .put("two", 2)
                .put("three", 3);

        System.out.println(map.hash("one"));
        System.out.println(map.get("one"));
    }

    static class Bucket {
        final ListNode head = new ListNode("", -1); // placeholder node - can change value
    }

    // Linked list to help define Bucket
    static class ListNode<String, Integer> {
        String key;
        Integer val;
        ListNode next;

        public ListNode(String key, Integer val) {
//            if (key == null || value == null) {
//                throw new Exception("key & val should not be null");
//            }
            this.key = key;
            this.val = val;
        }
    }
}

