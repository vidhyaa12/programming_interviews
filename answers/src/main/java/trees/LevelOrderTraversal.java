package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Print a binary tree level by level
 */
public class LevelOrderTraversal {
    public void printLevelOrder(Node node) {
        if (node != null) {
            Queue<Node> nodes = new LinkedList<Node>();
            nodes.offer(node);
            int currentCount = 1, nextCount = 0;
            while (!nodes.isEmpty()) {
                Node currentNode = nodes.poll();
                currentCount -= 1;
                System.out.print(currentNode.value + " ");
                if (currentNode.left != null) {
                    nodes.offer(currentNode.left);
                    nextCount += 1;
                }

                if (currentNode.right != null) {
                    nodes.offer(currentNode.right);
                    nextCount += 1;
                }
                if (currentCount == 0) {
                    System.out.println();
                    currentCount ^= nextCount;
                    nextCount ^= currentCount;
                    currentCount ^= nextCount;
                }
            }
        }

    }
}
