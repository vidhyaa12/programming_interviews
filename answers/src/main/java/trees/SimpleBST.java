package trees;

public class SimpleBST {

    static class Node {
        public Node left, right;
        public int val;

        Node(int n) {
            this.val = n;
            this.left = this.right = null;
        }

        public String toString() {
            return "[" + this.left + "," + this.val + "," + this.right + "]";
        }
    }

    public SimpleBST() {
        this.root = null;
    }

    private Node root;

    public void insert(Node n) {
        if (root == null) {
            root = n;
        } else {
            Node currentNode = root;
            Node prevNode = null;
            while (currentNode != null) {
                prevNode = currentNode;
                if (currentNode.val > n.val) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }
            }

            if (prevNode.val > n.val) {
                prevNode.left = n;
            } else {
                prevNode.right = n;
            }
        }
    }

    public void delete(Node n) {
        if (root == null) {
            return;
        }

        if (root.val == n.val) {
            if (root.left == null && root.right == null) {
                root = null;
                return;
            } else {
                Node tmp = root;
                root = null;
                insert(tmp.left);
                insert(tmp.right);
                return;
            }
        } else {
            Node current = root;
            Node previous = null;

            while (current != null && current.val != n.val) {
                previous = current;
                if (n.val < current.val) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            Node tmp;
            if (previous.left.val == n.val) {
                tmp = previous.left;
                previous.left = null;
                if (tmp.left != null) {
                    insert(tmp.left);
                }

                if (tmp.right != null) {
                    insert(tmp.right);
                }
            } else if (previous.right.val == n.val) {
                tmp = previous.right;
                previous.right = null;
                if (tmp.left != null) {
                    insert(tmp.left);
                }

                if (tmp.right != null) {
                    insert(tmp.right);
                }
            }
        }
    }

    public void traverse(Node current) {
        if (current != null) {
            traverse(current.left);
            System.out.print(current + "  # ");
            traverse(current.right);
        }
    }

    public Node getRoot() {
        return root;
    }

    public static void main(String[] args) {
        SimpleBST bst = new SimpleBST();
        bst.insert(new Node(8));
        bst.insert(new Node(1));
        bst.insert(new Node(9));
        System.out.println("Root is " + bst.getRoot());
    }
}
