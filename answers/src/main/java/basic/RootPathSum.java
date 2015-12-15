package basic;

/**
 * Count the number of paths from the root to any node that sum to a given value
 */
public class RootPathSum {
    public RootPathSum(Node n, int sum) {

    }

    public static int countPaths(Node n, int sum, int count) {
        if (n != null) {
            sum = sum - n.value;

            if (sum == 0) {
                count ++;
            }
            count += countPaths(n.left, sum, 0);
            count += countPaths(n.right, sum, 0);
        }

        return count;
    }

    public static Node getTestTree(int levelsToLeaf) {
        Node leftNode = null;
        Node rightNode = null;
        if (levelsToLeaf > 1) {
            leftNode = getTestTree(levelsToLeaf - 1);
            rightNode = getTestTree(levelsToLeaf - 1);
        }
        return new Node(1, leftNode, rightNode);
    }

    public static void testCountPaths() {
        Node testTreeWithOneLevel = getTestTree(1);
        int p1 = countPaths(testTreeWithOneLevel, 2, 0);
        int p2 = countPaths(testTreeWithOneLevel, 1, 0);
        System.out.println("p1 = " + p1 + " ,p2 = " + p2);

        Node testTreeWithFourLevels = getTestTree(4);
        int p3 = countPaths(testTreeWithFourLevels, 4, 0);
        int p4 = countPaths(testTreeWithFourLevels, 5, 0);
        System.out.println("p3 = " + p3 + " ,p4 = " + p4);
    }

    public static void main(String[] args) {
        testCountPaths();
    }
}
