package trees;

/**
 * Check if a binary tree is a binary search tree
 */
public class BstChecker {
    public boolean isBst(Node n, Integer low, Integer high) {
        if (n == null) {
            return true;
        }

        // start with a range of low = null & high = null which the root would obviously satisfy
        if ((low != null && n.value <= low) || (high != null && n.value > high)) {
            return false;
        }

        // all left nodes must be less than or equal to the current node & all right nodes must be greater
        // than the current node
        if (!isBst(n.left, low, n.value) || !isBst(n.right, n.value, high)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
