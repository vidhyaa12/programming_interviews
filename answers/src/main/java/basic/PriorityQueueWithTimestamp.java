package basic;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Design a priority queue with the property that the element that was inserted first is removed when multiple elements have the same priority
 */
public class PriorityQueueWithTimestamp<T> {
    PriorityQueue<ObjectWithPriority<T>> priorityQueue;

    public PriorityQueueWithTimestamp() {
        priorityQueue = new PriorityQueue<ObjectWithPriority<T>>(new ObjectWithPriorityComparator());
    }

    static class ObjectWithPriority<T> {
        int priority;
        T key;
        long timestamp;

        public ObjectWithPriority(int priority, T key, long timestamp) {
            this.priority = priority;
            this.key = key;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return priority + " " + key + " " + timestamp;
        }
    }

    class ObjectWithPriorityComparator implements Comparator<ObjectWithPriority<T>> {
        @Override
        public int compare(ObjectWithPriority o1, ObjectWithPriority o2) {
            if (o1 == null) {
                return -1;
            }

            if (o2 == null) {
                return 1;
            }

            if (o1.priority == o2.priority) {
               return (int) (o1.timestamp - o2.timestamp);
            }

            return o1.priority - o2.priority;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        PriorityQueueWithTimestamp<String> priorityQueueWithTimestamp = new PriorityQueueWithTimestamp();

        ObjectWithPriority<String> o1 = new ObjectWithPriority(1, "a", System.currentTimeMillis());
        ObjectWithPriority<String> o2 = new ObjectWithPriority(2, "b", System.currentTimeMillis());
        ObjectWithPriority<String> o3 = new ObjectWithPriority(3, "c", System.currentTimeMillis());

        Thread.sleep(1000L);

        ObjectWithPriority<String> o4 = new ObjectWithPriority(1, "a", System.currentTimeMillis());

        priorityQueueWithTimestamp.priorityQueue.add(o1);
        priorityQueueWithTimestamp.priorityQueue.add(o2);
        priorityQueueWithTimestamp.priorityQueue.add(o3);
        priorityQueueWithTimestamp.priorityQueue.add(o4);

        for (ObjectWithPriority objectWithPriority : priorityQueueWithTimestamp.priorityQueue) {
            System.out.println(objectWithPriority.toString());
        }

        System.out.println();

        priorityQueueWithTimestamp.priorityQueue.remove();

        System.out.println("Removed head of priority queue");

        System.out.println();

        for (ObjectWithPriority objectWithPriority : priorityQueueWithTimestamp.priorityQueue) {
            System.out.println(objectWithPriority.toString());
        }
    }
}
