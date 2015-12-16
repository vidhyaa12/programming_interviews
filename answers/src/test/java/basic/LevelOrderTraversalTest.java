package basic;

import org.testng.annotations.Test;
import trees.LevelOrderTraversal;
import trees.Node;
import trees.SortedArrayToBST;

public class LevelOrderTraversalTest {
    @Test
    public void testLevelOrderTraversal() {
        int[] arr = {2, 3, 6, 7, 8, 9, 12, 15, 16, 18, 20};
        SortedArrayToBST s = new SortedArrayToBST();
        Node node = s.convert(arr, 0, arr.length - 1);

        LevelOrderTraversal levelOrderTraversal = new LevelOrderTraversal();
        levelOrderTraversal.printLevelOrder(node);
    }
}
