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
        Assert.assertEquals(new BstChecker().isBst(n1, null, null), false);
    }
}
