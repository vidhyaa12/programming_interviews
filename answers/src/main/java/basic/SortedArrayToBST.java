package basic;

/**
 * http://algorithms.tutorialhorizon.com/sorted-array-to-binary-search-tree-of-minimal-height/
 */

public class SortedArrayToBST {
    public Node convert(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        Node root = new Node(arr[mid]);
        root.left = convert(arr, start, mid - 1);
        root.right = convert(arr, mid + 1, end);
        return root;
    }

    public void displayTree(Node root) {
        if (root != null) {
            displayTree(root.left);
            System.out.print(" " + root.value);
            displayTree(root.right);
        }
    }

    public static void main(String args[]) {
        int[] arr = {2, 3, 6, 7, 8, 9, 12, 15, 16, 18, 20};
        SortedArrayToBST s = new SortedArrayToBST();
        Node node = s.convert(arr, 0, arr.length - 1);
        System.out.println("Tree Display : ");
        s.displayTree(node);
    }
}