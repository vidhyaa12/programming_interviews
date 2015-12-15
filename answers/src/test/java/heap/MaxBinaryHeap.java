package heap;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Max heap implementation
 */
public class MaxBinaryHeap<T extends Comparable<T>> {
    private static final int DEFAULT_HEAP_CAPACITY = 128;
    private ArrayList<T> nodes;
    private int heapSize;

    public MaxBinaryHeap() {
        this(DEFAULT_HEAP_CAPACITY);
    }

    public MaxBinaryHeap(int heapCapacity) {
        nodes = new ArrayList<T>(heapCapacity);
        heapSize = 0;
    }

    public void heapifyUp(int index) {
        int parentIndex = (int) Math.floor((index - 1) / 2);
        boolean isHeapPropertySatisfied = false;

        while (!isHeapPropertySatisfied && parentIndex < index) {
            T currentNode = nodes.get(index);
            T parentNode = nodes.get(parentIndex);
            if (currentNode.compareTo(parentNode) > 0) {
                nodes.set(index, parentNode);
                nodes.set(parentIndex, currentNode);
            } else {
                isHeapPropertySatisfied = true;
            }

            index = parentIndex;
            parentIndex = (int) Math.floor((index - 1) / 2);
        }
    }

    public void heapifyDown(int index) {
        if (index >= heapSize) {
            return;
        }
        int leftChildIndex = 2 * index + 1;
        int rightChildIndex = 2 * index + 2;

        T currentNode = nodes.get(index);
        T leftChild = leftChildIndex < heapSize ? nodes.get(leftChildIndex) : null;
        T rightChild = rightChildIndex < heapSize ? nodes.get(rightChildIndex) : null;

        if (rightChild != null && rightChild.compareTo(currentNode) > 0 && rightChild.compareTo(leftChild) > 0) {
            nodes.set(index, rightChild);
            nodes.set(rightChildIndex, currentNode);
            heapifyDown(rightChildIndex);
        } else if (leftChild != null && leftChild.compareTo(currentNode) > 0) {
            nodes.set(leftChildIndex, currentNode);
            nodes.set(index, leftChild);
            heapifyDown(leftChildIndex);
        }
    }

    public T pop() {
        if (heapSize > 0) {
            T lastLeaf = nodes.get(heapSize - 1);
            T root = nodes.get(0);

            nodes.set(0, lastLeaf);
            nodes.remove(heapSize - 1);
            heapSize--;
            heapifyDown(0);
            return root;
        } else {
            return null;
        }
    }

    public void insert(T node) {
        nodes.add(heapSize, node);
        heapifyUp(heapSize - 1);
        heapSize++;
    }

    public void addAll(Collection<? extends T> c) {
        for (T t : c) {
            this.insert(t);
        }
    }

    public int size() {
        return heapSize;
    }

    public void print() {
        for (T node : nodes) {
            System.out.print(node + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaxBinaryHeap<Integer> bh = new MaxBinaryHeap<Integer>();

        for (int i = 10; i > 0; i--) {
            bh.insert(i);
        }

        bh.print();
        while (bh.size() > 0) {
            Integer top = bh.pop();
            System.out.println("Top element " + top + " has been popped , size = " + bh.size());
            bh.print();
        }
    }

    public ArrayList<T> getNodes() {
        return nodes;
    }
}
