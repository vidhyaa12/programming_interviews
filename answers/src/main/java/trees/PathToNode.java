package trees;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathToNode {

    private static boolean hasPath(Node root, Node target, List<Integer> path){
        if (root == null) return false;
        if (root == target || hasPath(root.left, target, path) || hasPath(root.right, target, path)) {
            //System.out.print("  " + root.data);
            path.add(root.value);
            return true;
        }
        return false;
    }

    public static List<Integer> getPathFromRootToTarget(Node root, Node target, List<Integer> path) {
        hasPath(root, target, path);
        Collections.reverse(path);
        return path;
    }

    public static void main (String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        Node n1 = new Node(4);
        root.left.left = n1;
        root.left.right = new Node(5);
        Node n2 = new Node(8);
        root.left.right.right = n2;
        root.left.right.left = new Node(7);

        List<Integer> path = getPathFromRootToTarget(root, n2, new ArrayList<Integer>());
        System.out.println(" Path " + path);
    }
}