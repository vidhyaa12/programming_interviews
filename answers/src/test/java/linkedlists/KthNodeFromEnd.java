package linkedlists;

/**
 * Find the k-th node from the end of a singly linked list
 */
public class KthNodeFromEnd<T extends Comparable<T>> {

    public SinglyLinkedListNode<T> findKthNodeFromEnd(SinglyLinkedList<T> singlyLinkedList, int k) {
        if (singlyLinkedList == null || k > singlyLinkedList.size()) { // safe to ensure k is smaller than the length of the list
            return null;
        }

        SinglyLinkedListNode<T> fast = singlyLinkedList.get(0);

        for (int i = 0; i < k; i++) {
            fast = fast.getNext();
        }

        SinglyLinkedListNode<T> slow = singlyLinkedList.get(0);
        while (fast != null) {
            slow = slow.getNext();
            fast = fast.getNext();
        }
        return slow;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode node = new SinglyLinkedListNode<Integer>();
        node.setValue(1);
        node.setNext(null);

        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<Integer>(node);
        singlyLinkedList.add(2);
        singlyLinkedList.add(3);
        singlyLinkedList.add(4);
        singlyLinkedList.add(5);
        singlyLinkedList.add(6);

        KthNodeFromEnd<Integer> kthNodeFromEnd = new KthNodeFromEnd<Integer>();

        int k = 3;

        SinglyLinkedListNode result = kthNodeFromEnd.findKthNodeFromEnd(singlyLinkedList, k);
        System.out.println("k = " + k + ", k-th node from the end has value = " + result.getValue());
    }
}

