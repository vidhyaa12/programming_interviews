package basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Design a priority queue with the property that the element that was inserted first is removed when multiple elements have the same priority
 */
public class PriorityQueueWithTimestampImpl2<T> {
    Map<Integer, ObjectsWithSamePriority<T>> objectsWithSamePriorityMap;
    ObjectsWithSamePriority<T> topPriorityObjects;

    public PriorityQueueWithTimestampImpl2() {
        objectsWithSamePriorityMap = new HashMap<Integer, ObjectsWithSamePriority<T>>();
    }

    public void add(ObjectWithPriority<T> objectWithPriority) {
        if (objectWithPriority == null) {
            return;
        }

        T key = objectWithPriority.key;
        int priority = objectWithPriority.priority;

        if (!objectsWithSamePriorityMap.containsKey(key)) {
            ObjectsWithSamePriority objectsWithCurrPriority = new ObjectsWithSamePriority<T>(priority, Arrays.asList(objectWithPriority));
            objectsWithSamePriorityMap.put(priority, objectsWithCurrPriority);

            if (topPriorityObjects == null) {
                topPriorityObjects = objectsWithCurrPriority;
            } else if (priority < topPriorityObjects.priority) {
                ObjectsWithSamePriority temp = topPriorityObjects;
                objectsWithCurrPriority.next = temp;
                temp.prev = objectsWithCurrPriority;
                topPriorityObjects = objectsWithCurrPriority;
            } else {
                ObjectsWithSamePriority p = topPriorityObjects;
                ObjectsWithSamePriority prevObjectWithSamePriority = null;
                while (p != null && p.priority < priority) {
                    prevObjectWithSamePriority = p;
                    p = p.next;
                }

                prevObjectWithSamePriority.next = objectsWithCurrPriority;
                objectsWithCurrPriority.prev = prevObjectWithSamePriority;
                objectsWithCurrPriority.next = p;

                if (p != null) {
                    p.prev = objectsWithCurrPriority;
                }
            }
        } else {
            ObjectsWithSamePriority<T> objectsWithSamePriority = objectsWithSamePriorityMap.get(priority);
            objectsWithSamePriority.objectWithPriorityList.add(objectWithPriority);
        }
    }

    public T poll() {
        T topPriorityObject = null;
        if (topPriorityObjects !=null) {
            topPriorityObject = (T) topPriorityObjects.objectWithPriorityList.remove(0);
            if (topPriorityObjects.objectWithPriorityList.size() ==0) {
                objectsWithSamePriorityMap.remove(topPriorityObjects.priority);
                topPriorityObjects = topPriorityObjects.next;
                topPriorityObjects.prev = null;
            }
        }
        return topPriorityObject;
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

    static class ObjectsWithSamePriority<T> {
        int priority;
        List<ObjectWithPriority<T>> objectWithPriorityList;
        ObjectsWithSamePriority prev;
        ObjectsWithSamePriority next;

        // Note: Instead of using a list, we can use a priorityQueue and peek the priority queue.
        // For FIFO timestamp based removal, remove the 1st element in the priority queue if there are elements
        // Otherwise, remove the List<ObjectsWithSamePriority<T>> from the hashmap for the target priority

        public ObjectsWithSamePriority(int priority, List<ObjectWithPriority<T>> objectsWithSamePriorities) {
            this.priority = priority;
            this.objectWithPriorityList = objectsWithSamePriorities;
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

    }
}
