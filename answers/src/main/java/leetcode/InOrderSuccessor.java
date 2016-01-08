package leetcode;

import trees.Node;
import trees.SortedArrayToBST;

/**
 * Created by vidhyaa on 1/2/16.
 */
public class InOrderSuccessor {
    public Node inorderSuccessor(Node root, Node p) {
        // return inorderSuccessorRecursive(root, p);
        return inorderSuccessorIterative(root, p);
    }

    public static Node inorderSuccessorIterative(Node root, Node p) {
        if (root == null || p == null) {
            return null;
        }
        Node lastLarger = null;
        while (root != null) {
            if (p.getValue() < root.getValue()) {
                lastLarger = root;
                root = root.getLeft();
            } else {
                root = root.getRight();
            }
        }
        return lastLarger;
    }

    /*
    public Node inorderSuccessorRecursive(Node root, Node p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.val < root.val) {
            Node leftResult = inorderSuccessor(root.left, p);
            return leftResult == null ? root : leftResult;
        }
        return inorderSuccessor(root.right, p);
    }
    */

    public static void main(String args[]) {
        int[] arr = {50, 60, 70, 80, 85, 90, 95, 100, 120};
        SortedArrayToBST s = new SortedArrayToBST();
        Node node = s.convert(arr, 0, arr.length - 1);
        System.out.println("Tree Display : ");
        s.displayTree(node);
        System.out.println();
        System.out.println(inorderSuccessorIterative(node, new Node(95)).getValue());
    }
}
