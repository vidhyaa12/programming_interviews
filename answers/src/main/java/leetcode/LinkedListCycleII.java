package leetcode;

public class LinkedListCycleII {
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null) {
            if (fast.getNext() != null) {
                fast = fast.getNext().getNext();
            } else {
                return null;
            }

            if (slow == null || fast == null) { // if either hits null..no loop.
                return null;
            }

            slow = slow.getNext();

            if (fast.getValue() == slow.getValue() && fast.getNext() == slow.getNext()) {
                fast = head;
                while (fast.getValue() != slow.getValue() && fast.getNext() != slow.getNext()) {
                    slow = slow.getNext();
                    fast = fast.getNext();
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        ListNode<Integer> head = new ListNode<Integer>();
//        head.setValue(1);
//
//        ListNode<Integer> node2 = new ListNode<Integer>();
//        node2.setValue(2);
//
//        head.setNext(node2);
//
//        ListNode<Integer> node3 = new ListNode<Integer>();
//        node3.setValue(3);
//
//        node2.setNext(node3);
//
//        ListNode<Integer> node4 = new ListNode<Integer>();
//        node4.setValue(4);
//
//        node3.setNext(node4);
//
//        ListNode<Integer> node5 = new ListNode<Integer>();
//        node5.setValue(5);
//
//        node4.setNext(node5);
//
//        node5.setNext(node3);

        ListNode<Integer> head = new ListNode<Integer>();
        head.setValue(3);

        ListNode<Integer> node2 = new ListNode<Integer>();
        node2.setValue(2);

        head.setNext(node2);

        ListNode<Integer> node3 = new ListNode<Integer>();
        node3.setValue(0);

        node2.setNext(node3);

        ListNode<Integer> node4 = new ListNode<Integer>();
        node4.setValue(-4);
        node4.setNext(node2);

        node3.setNext(node4);

        ListNode node =  detectCycle(head);
        System.out.println(node.getValue());
    }
}
