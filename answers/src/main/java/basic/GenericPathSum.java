package basic;

import java.util.Stack;

/**
 * Count the number of paths from any node that sum to a given value
 */
public class GenericPathSum {
    public GenericPathSum(Node n, int sum) {

    }

    public static int countPaths(Node n, int sumFromRoot, int expectedSum , Stack<Node> pathFromRootToCurrNode) {
        int count = 0;
        if (n != null) {
            pathFromRootToCurrNode.push(n);
            sumFromRoot = sumFromRoot + n.value;

            if (sumFromRoot == expectedSum) {
                count ++;
            } else if(sumFromRoot > expectedSum) {
                /*
                Loop from root in this path using pathFromRootToCurNode and see if a subpath can help you achieve the exact sumFromRoot
                Also compute the complexity
              */
                // check if there is a path from any ancestor node of n that ends in n
                int runningSum = 0;
                for (int i = pathFromRootToCurrNode.size() -1; i >= 0 && runningSum <= sumFromRoot; i--) {
                    runningSum += pathFromRootToCurrNode.get(i).getValue();
                    if (runningSum == sumFromRoot) {
                        count++;
                    }
                }
            }

            count += countPaths(n.left, sumFromRoot, expectedSum, pathFromRootToCurrNode);
            count += countPaths(n.right, sumFromRoot, expectedSum, pathFromRootToCurrNode);
            pathFromRootToCurrNode.pop();
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
        Node testTreeWithFourLevels = getTestTree(4);
        int p3 = countPaths(testTreeWithFourLevels, 0, 1, new Stack<Node>());
        System.out.println("p3 = " + p3);
    }

    public static void main(String[] args) {
        testCountPaths();
    }
}
