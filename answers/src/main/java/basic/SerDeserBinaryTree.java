package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SerDeserBinaryTree {
    static class TreeNode {
        private int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public String serialize(TreeNode root) {
        Queue<TreeNode> curLevel = new LinkedList<TreeNode>();
        List<TreeNode> levelOrder = new ArrayList<TreeNode>();

        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();
            for (TreeNode currNode : curLevel) {
                levelOrder.add(currNode);
                if (currNode != null) {
                    nextLevel.add(currNode.left);
                    nextLevel.add(currNode.right);
                }
                curLevel = nextLevel;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (TreeNode node  : levelOrder) {
            if (node == null) {
                sb.append("null");
            } else {
                sb.append(node.val);
            }

            if (sb.length() > 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    private TreeNode valToTreeNode(Queue<String> valStrQueue) {
        if (valStrQueue.isEmpty()) {
            return null;
        } else {
            String str = valStrQueue.poll();
            if (str == null || "null".equals(str)) {
                return null;
            }
            return new TreeNode(Integer.parseInt(str));
        }
    }

    public TreeNode deserialize(String str) {
        if (str == null || str.equals("null")) {
            return null;
        }
        // split String by commas
        Queue<String> valStrQueue = new LinkedList<String>(Arrays.asList(str.split(",")));

        TreeNode root = valToTreeNode(valStrQueue);

        if (root == null) {
            return null;
        }

        Queue<TreeNode> curLevel = new LinkedList<TreeNode>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();
            for (TreeNode currNode : curLevel) {
                currNode.left = valToTreeNode(valStrQueue);
                currNode.right = valToTreeNode(valStrQueue);

                if (currNode.left != null) {
                    nextLevel.add(currNode.left);
                }

                if (currNode.right != null) {
                    nextLevel.add(currNode.right);
                }
            }
            curLevel = nextLevel;
        }
        return root;
    }
}
