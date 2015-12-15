package heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MedianInStream {
    MaxBinaryHeap maxBinaryHeap;
    MinBinaryHeap minBinaryHeap;
    int numOfElements;

    public MedianInStream() {
        maxBinaryHeap = new MaxBinaryHeap();
        minBinaryHeap = new MinBinaryHeap();
    }

    /**
     * Algo source: http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/
     *
     * We will use 2 heaps simultaneously, a max-heap and a min-heap with 2 requirements.
     *
     * The first requirement is that the max-heap contains the smallest half of the numbers and min-heap contains the largest half.
     * So, the numbers in max-heap are always less than or equal to the numbers in min-heap. Let’s call this the order requirement.
     *
     * The second requirement is that, the number of elements in max-heap is either equal to
     * or 1 more than the number of elements in the min-heap.
     * So, if we received 2N elements (even) up to now, max-heap and min-heap will both contain N elements.
     * Otherwise, if we have received 2N+1 elements (odd), max-heap will contain N+1 and min-heap N. Let’s call this the size requirement.
     *
     * The heaps are constructed considering the two requirements above.
     * Then once we’re asked for the median, if the total number of received elements is odd, the median is the root of the max-heap.
     * If it’s even, then the median is the average of the roots of the max-heap and min-heap.
     *
     * Let’s now analyse why this approach works, and how we construct the heaps.
     * We will have two methods, insert a new received number to the heaps and find median.
     * The insertion procedure takes the two requirements into account, and it’s executed every time we receive a new element.
     * We take two different approaches depending on whether the total number of elements is even or odd before insertion.
     *
     * Let’s first analyze the size requirement during insertion.
     * In both cases we insert the new element to the max-heap, but perform different actions afterwards.
     *
     * In the first case, if the total number of elements in the heaps is even before insertion,
     * then there are N elements both in max-heap and min-heap because of the size requirement.
     * After inserting the new element to the max-heap, it contains N+1 elements but this doesn’t violate the size requirement.
     * Max-heap can contain 1 more element than min-heap.
     *
     * In the second case, if the number of elements is odd before insertion,
     * then there are N+1 elements in max-heap and N in min-heap.
     * After we insert the new element to the max-heap, it contains N+2 elements.
     * But this violates the size constraint, max-heap can contain at most 1 more element than min-heap.
     * So we pop an element from max-heap and push it to min-heap. The details will be described soon.
     *
     * Now let’s analyse the order requirement.
     * This requirement forces every element in the max-heap to be less than or equal to all the elements in min-heap.
     * So the max-heap contains the smaller half of the numbers and the min-heap contains the larger half.
     * Note that by design the root of the max-heap is the maximum of the lower half, and root of the min-heap is the minimum of the upper half.
     * Keeping these in mind, we again take two different actions depending on whether the total number of elements is
     * even or odd before insertion.
     *
     * In the even case we just inserted the new element to the max-heap.
     * If the new element is less than all the elements in the min-heap, then the order constraint is satisfied and we’re done.
     * We can perform this check by comparing the new element to the root of the min-heap in O(1) time
     * since the root of the min-heap is the minimum.
     * But if the new element is larger than the root of min-heap then we should exchange those elements to satisfy the order requirement.
     * Note that in this case the root of the max-heap is the new element. So we pop the the root of min-heap and insert it to max-heap.
     * Also pop the root of max-heap and insert it to min-heap.
     * In second case, where the total number of elements before insertion is odd, we inserted the new element to max-heap,
     * then we popped an element and pushed it to the min-heap. To satisfy the order constraint, we pop the maximum element of the max-heap,
     * the root, and insert it to the min-heap. Insertion complexity is O(logN), which is the insertion complexity of a heap.
     * That is exactly how the insertion procedure works. We ensured that both size and order requirements are satisfied during insertion.
     * Find median function works as follows. At any time we will be queried for the median element.
     * If the total number of elements at that time is odd, then the median is the root of the max-heap.

     - See more at: http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/#sthash.Qc2uEj74.dpuf
     */
    public void insert(Integer num) {
        maxBinaryHeap.insert(num);
        if (numOfElements % 2 == 0) {
            if (minBinaryHeap.size() == 0) {
                numOfElements++;
                return;
            } else if (((Integer) maxBinaryHeap.getNodes().get(0)).compareTo((Integer) minBinaryHeap.getNodes().get(0)) > 0) {
                // if the max heap has more elements than the min heap and the root of the max heap is greater than the root of the min heap,
                // then exchange the roots of the max heap and min heap to ensure that all the elements in the max heap are less than the elements
                // in the min heap
                Integer maxHeapRoot = (Integer) maxBinaryHeap.pop();
                Integer minHeapRoot = (Integer) minBinaryHeap.pop();
                maxBinaryHeap.insert(minHeapRoot);
                minBinaryHeap.insert(maxHeapRoot);
            }
        } else {
            minBinaryHeap.insert(maxBinaryHeap.pop());
        }
        numOfElements++;
    }

    public Integer findMedian() {
        if (maxBinaryHeap == null || minBinaryHeap == null || maxBinaryHeap.size() + maxBinaryHeap.size() == 0) {
            throw new RuntimeException("No numbers are available!");
        }

        if (((maxBinaryHeap.size() + maxBinaryHeap.size()) & 1) == 1) {
            return ((Integer) minBinaryHeap.getNodes().get(0));
        } else {
            return (((Integer) maxBinaryHeap.getNodes().get(0)).intValue() +
                    ((Integer) minBinaryHeap.getNodes().get(0)).intValue()) / 2;
        }
    }

    public static int median(List<Integer> numbers) {
        Collections.sort(numbers);
        int middle = numbers.size() / 2;
        if (numbers.size() % 2 == 1) {
            return numbers.get(middle);
        } else {
            return (numbers.get(middle - 1) + numbers.get(middle)) / 2;
        }
    }

    public static void main(String[] args) {
        MedianInStream medianInStream = new MedianInStream();
        List<Integer> numbers = Arrays.asList(30, 14, 12, 20, 4, 100, 45, 500, 10, 2, 60, 25);

        for (Integer number : numbers) {
            medianInStream.insert(number);
        }

        System.out.println(medianInStream.findMedian());
        System.out.println(median(numbers));
    }
}
