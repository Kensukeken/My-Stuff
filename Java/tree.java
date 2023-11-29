import java.util.HashSet;
import java.util.Set;

public class BinaryTree {
    public static class BNode {
        private int val;
        private BNode left;
        private BNode right;

        public BNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public BNode getLeft() {
            return left;
        }

        public void setLeft(BNode left) {
            this.left = left;
        }

        public BNode getRight() {
            return right;
        }

        public void setRight(BNode right) {
            this.right = right;
        }
    }

    private BNode root;

    public void insert(int value) {
        root = insert(root, value);
    }

    private BNode insert(BNode root, int value) {
        if (root == null) {
            return new BNode(value);
        }

        if (value < root.getVal()) {
            root.setLeft(insert(root.getLeft(), value));
        } else if (value > root.getVal()) {
            root.setRight(insert(root.getRight(), value));
        }

        return root;
    }

    public boolean contains(int value) {
        return contains(root, value);
    }

    private boolean contains(BNode root, int value) {
        if (root == null) {
            return false;
        }

        if (value == root.getVal()) {
            return true;
        }

        return value < root.getVal() ? contains(root.getLeft(), value) : contains(root.getRight(), value);
    }

    public void delete(int value) {
        root = delete(root, value);
    }

    private BNode delete(BNode root, int value) {
        if (root == null) {
            return root;
        }

        if (value < root.getVal()) {
            root.setLeft(delete(root.getLeft(), value));
        } else if (value > root.getVal()) {
            root.setRight(delete(root.getRight(), value));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            root.setVal(minValue(root.getRight()));
            root.setRight(delete(root.getRight(), root.getVal()));
        }

        return root;
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(BNode root) {
        if (root == null) {
            return true;
        }

        int leftHeight = height(root.getLeft());
        int rightHeight = height(root.getRight());

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }

        return isBalanced(root.getLeft()) && isBalanced(root.getRight());
    }

    public int countDifferentLeaves() {
        Set<Integer> leaves = new HashSet<>();
        countDifferentLeaves(root, leaves);
        return leaves.size();
    }

    private void countDifferentLeaves(BNode root, Set<Integer> leaves) {
        if (root == null) {
            return;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            leaves.add(root.getVal());
        }

        countDifferentLeaves(root.getLeft(), leaves);
        countDifferentLeaves(root.getRight(), leaves);
    }

    public int lowestCommonAncestor(int root, int target1, int target2) {
        BNode rootNode = get(root);
        if (rootNode == null) {
            return -1;
        }

        while (rootNode != null) {
            if (target1 > rootNode.getVal() && target2 > rootNode.getVal()) {
                rootNode = rootNode.getRight();
            } else if (target1 < rootNode.getVal() && target2 < rootNode.getVal()) {
                rootNode = rootNode.getLeft();
            } else {
                break;
            }
        }

        return rootNode == null ? -1 : rootNode.getVal();
    }

    private BNode get(int value) {
        return get(root, value);
    }

    private BNode get(BNode root, int value) {
        if (root == null) {
            return null;
        }

        if (value == root.getVal()) {
            return root;
        }

        return value < root.getVal() ? get(root.getLeft(), value) : get(root.getRight(), value);
    }

    private int minValue(BNode root) {
        int min = root.getVal();
        while (root.getLeft() != null) {
            min = root.getLeft().getVal();
            root = root.getLeft();
        }
        return min;
    }

    private int height(BNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }
}
