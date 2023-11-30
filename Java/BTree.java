class BNode {
    private int val;
    private BNode left, right;

    public BNode(int v) {
        left = null;
        right = null;
        val = v;
    }

    public int getVal() {
        return val;
    }

    public BNode getLeft() {
        return left;
    }

    public BNode getRight() {
        return right;
    }

    public void setVal(int v) {
        val = v;
    }

    public void setLeft(BNode lf) {
        left = lf;
    }

    public void setRight(BNode r) {
        right = r;
    }
}


public class BTree {
    private BNode root;
    public static final int IN = 1;
    public static final int PRE = 2;
    public static final int POST = 3;



    public int depth(int value) {
        return depth(root, value, 0);
    }

    private int depth(BNode node, int value, int currentDepth) {
        if (node == null) {
            return -1;
        }

        if (node.getVal() == value) {
            return currentDepth;
        }

        int leftDepth = depth(node.getLeft(), value, currentDepth + 1);
        int rightDepth = depth(node.getRight(), value, currentDepth + 1);

        return Math.max(leftDepth, rightDepth);
    }

    public void display() {
        display(root, IN);
    }

    public void display(int order) {
        display(root, order);
    }

    private void display(BNode node, int order) {
        if (node != null) {
            if (order == IN) {
                display(node.getLeft(), order);
                System.out.print(node.getVal() + " ");
                display(node.getRight(), order);
            } else if (order == PRE) {
                System.out.print(node.getVal() + " ");
                display(node.getLeft(), order);
                display(node.getRight(), order);
            } else if (order == POST) {
                display(node.getLeft(), order);
                display(node.getRight(), order);
                System.out.print(node.getVal() + " ");
            }
        }
    }

    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(BNode node) {
        if (node == null) {
            return 0;
        }

        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        } else {
            return countLeaves(node.getLeft()) + countLeaves(node.getRight());
        }
    }

    public int height() {
        return height(root);
    }

    private int height(BNode node) {
        if (node == null) {
            return 0;
        }

        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    public boolean isAncestor(int u, int v) {
        return isAncestor(root, u, v);
    }

    private boolean isAncestor(BNode node, int u, int v) {
        if (node == null) {
            return false;
        }

        if (node.getVal() == u) {
            return isAncestor(node.getLeft(), u, v) || isAncestor(node.getRight(), u, v);
        } else if (node.getVal() == v) {
            return true;
        } else {
            return isAncestor(node.getLeft(), u, v) || isAncestor(node.getRight(), u, v);
        }
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private BNode delete(BNode node, int value) {
        if (node == null) {
            return null;
        }

        if (value < node.getVal()) {
            node.setLeft(delete(node.getLeft(), value));
        } else if (value > node.getVal()) {
            node.setRight(delete(node.getRight(), value));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                node.setVal(findMin(node.getRight()));
                node.setRight(delete(node.getRight(), node.getVal()));
            }
        }
        return node;
    }

    private int findMin(BNode node) {
        if (node.getLeft() == null) {
            return node.getVal();
        } else {
            return findMin(node.getLeft());
        }
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(BNode node) {
        if (node == null) {
            return true;
        }

        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());

        if (Math.abs(leftHeight - rightHeight) <= 1
                && isBalanced(node.getLeft())
                && isBalanced(node.getRight())) {
            return true;
        }

        return false;
    }

    public void add(BTree otherTree) {
        add(root, otherTree.root);
    }

private void add(BNode node, BNode otherNode) {
    if (otherNode == null) {
        return;
    }

    add(node, otherNode); // Add the entire otherNode, not just its value
    add(node, otherNode.getLeft());
    add(node, otherNode.getRight());
    }
}
