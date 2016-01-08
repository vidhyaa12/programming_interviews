package linkedlists;

import com.google.common.base.Objects;

public class SinglyLinkedList<T> {

    private SinglyLinkedListNode<T> head;
//    private SinglyLinkedListNode<T> tail;

    SinglyLinkedList(SinglyLinkedListNode<T> start) {
        this.head = start;
    }

    public int size() {
        int sz = 0;
        SinglyLinkedListNode<T> node = head;
        while (node != null) {
            sz++;
            node = node.getNext();
        }
        return sz;
    }

    public SinglyLinkedListNode<T> get(int i) {
        int itr = 0;
        SinglyLinkedListNode<T> node = head;
        while (itr < i && node != null) {
            itr++;
            node = node.getNext();
        }
        return node;
    }

    public void add(T element) {

        SinglyLinkedListNode<T> nd = new SinglyLinkedListNode<T>();
        nd.setValue(element);
        System.out.println("Adding: " + element);
        /**
         * check if the list is empty
         */
        if (head == null) {
            //since there is only one element, both head and
            //tail points to the same object.
            head = nd;
        } else {
            SinglyLinkedListNode<T> node = head;
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(nd);
        }
    }

    public void addAfter(T element, T after) {

        SinglyLinkedListNode<T> tmp = head;
        SinglyLinkedListNode<T> refNode = null;
        System.out.println("Traversing to all nodes..");
        /**
         * Traverse till given element
         */
        while (true) {
            if (tmp == null) {
                break;
            }
            if (tmp.compareTo(after) == 0) {
                //found the target node, add after this node
                refNode = tmp;
                break;
            }
            tmp = tmp.getNext();
        }
        if (refNode != null) {
            //add element after the target node
            SinglyLinkedListNode<T> nd = new SinglyLinkedListNode<T>();
            nd.setValue(element);
            nd.setNext(tmp.getNext());
            tmp.setNext(nd);

        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public void deleteFront() {

        if (head == null) {
            System.out.println("Underflow...");
        }
        SinglyLinkedListNode<T> tmp = head;
        head = tmp.getNext();
        System.out.println("Deleted: " + tmp.getValue());
    }

    public void deleteAfter(T after) {

        SinglyLinkedListNode<T> tmp = head;
        SinglyLinkedListNode<T> refNode = null;
        System.out.println("Traversing to all nodes..");
        /**
         * Traverse till given element
         */
        while (true) {
            if (tmp == null) {
                break;
            }
            if (tmp.compareTo(after) == 0) {
                //found the target node, add after this node
                refNode = tmp;
                break;
            }
            tmp = tmp.getNext();
        }
        if (refNode != null) {
            tmp = refNode.getNext();
            refNode.setNext(tmp.getNext());
            System.out.println("Deleted: " + tmp.getValue());
        } else {
            System.out.println("Unable to find the given element...");
        }
    }

    public void traverse() {

        SinglyLinkedListNode<T> tmp = head;
        while (true) {
            if (tmp == null) {
                break;
            }
            System.out.println(tmp.getValue());
            tmp = tmp.getNext();
        }
    }

    public void reverse() {
        if (this == null || this.head == null) {
            return;
        }

        SinglyLinkedListNode prev = null;
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode next = (current != null) ? current.getNext() : null;
        SinglyLinkedListNode nextToNext = (next != null) ? next.getNext() : null;

        while (current != null) {
            current.setNext(prev);
            prev = current;
            current = next;
            next = nextToNext;
            if (nextToNext != null) nextToNext = nextToNext.getNext();
        }
        head = prev;
    }

    public SinglyLinkedList reverse(int start) {
        if (this == null || this.head == null) {
            return null;
        }

        SinglyLinkedListNode prev = null;
        SinglyLinkedListNode current = head;
        SinglyLinkedListNode newTailForOriginalList = null;

        int index = 0;
        while (index < start) {
            newTailForOriginalList = current;
            current = current.getNext();
            index++;
        }

        SinglyLinkedListNode next = (current != null) ? current.getNext() : null;
        SinglyLinkedListNode nextToNext = (next != null) ? next.getNext() : null;

        while (current != null) {
//            System.out.println(print(prev) + " -> " + print(current) + " -> " + print(next) + " -> " + print(nextToNext));
            current.setNext(prev);
            prev = current;
            current = next;
            next = nextToNext;
            if (nextToNext != null) nextToNext = nextToNext.getNext();
        }
        newTailForOriginalList.setNext(null);

        return new SinglyLinkedList(prev);
    }

    String print(SinglyLinkedListNode node) {
        if (node != null) return "" + node.getValue();
        return "NULL";
    }

    public boolean isPalindrome() {
        if (this == null || this.head == null || this.head.getNext() == null) {
            return true;
        }

        int linkedListSize = size();

        SinglyLinkedListNode current = head;

        int reverseStartPosition;
        int endIndex;
        if (linkedListSize % 2 == 0) {
            reverseStartPosition = linkedListSize / 2;
            endIndex = reverseStartPosition;
        } else {
            reverseStartPosition = (linkedListSize + 1) / 2;
            endIndex = reverseStartPosition - 1;
        }

        SinglyLinkedList reversedSubList = reverse(reverseStartPosition);
        int index = 0;
        current = head;
        SinglyLinkedListNode nodeInReversedSublist = reversedSubList.head;
        while (index < endIndex) {
            // TODO: clean up to use the equals method in SinglyLinkedList to compare the two lists
            if (!current.getValue().equals(nodeInReversedSublist.getValue())) {
                return false;
            }
            current = current.getNext();
            nodeInReversedSublist = nodeInReversedSublist.getNext();
            index++;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinglyLinkedList<?> that = (SinglyLinkedList<?>) o;
        return Objects.equal(head, that.head);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(head);
    }

    public static void main(String a[]) {
        SinglyLinkedList<Integer> sl = new SinglyLinkedList<Integer>(null);
        sl.add(3);
        sl.add(32);
        sl.add(33);
        sl.add(5);
        sl.add(33);
        sl.add(32);
        sl.add(3);
        System.out.println(sl.isPalindrome());
        sl.traverse();

        SinglyLinkedList result = sl.reverse(2);

        System.out.println("LinkList reversed ");
        result.traverse();
    }
}