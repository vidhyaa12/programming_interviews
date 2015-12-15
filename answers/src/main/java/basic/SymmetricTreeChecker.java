package basic;

public class SymmetricTreeChecker {
    public static boolean isSymmetric(Node n) {
        if (n == null) {
            return true;
        }

        if (n.left == null) {
            return n.right == null;
        }

        if (n.left.value != n.right.value) {
            return false;
        }

        return isSymmetric(n.left) && isSymmetric(n.right);
    }
}
