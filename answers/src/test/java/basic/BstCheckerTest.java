package basic;

import junit.framework.Assert;
import org.testng.annotations.Test;

/**
 * Validate if a few test binary trees are binary search trees
 */
public class BstCheckerTest {
    @Test
    public void testBstChecker_returnsFalse_whenAllLeftNodes_AreNot_LessThanAllRightNodes() {
        Node n1 = new Node(10);
        Node n1Left = new Node(5);
        n1.setLeft(n1Left);
        n1Left.setRight(new Node(25));

        n1.setRight(new Node(20));
        Assert.assertFalse(new BstChecker().isBst(n1, null, null));
    }

    @Test
    public void testBstChecker_validates_good_bst() {
        Node n1 = new Node(10);
        Node n1Left = new Node(5);
        n1.setLeft(n1Left);

        Node n1right = new Node(20);
        n1.setRight(n1right);
        n1right.setRight(new Node(25));

        Assert.assertTrue(new BstChecker().isBst(n1, null, null));
    }
}
