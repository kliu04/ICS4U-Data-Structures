// BTree.java
// Kevin Liu
// Implements the "Binary Tree"

public class BTree {
    private BNode root;

    public static final int PRE = 0; // constants for the different way to display the tree
    public static final int IN = 1;
    public static final int POST = 2;

    public BTree() {
        root = null;
    }

    public void add(int n) {
        if (root == null) { // empty tree
            root = new BNode(n);
        } else { // adds the node to the tree
            add(n, root);
        }
    }

    public void add(BTree tree) {
        BNode N = tree.root;
        if (N == null) { // all values have been added to the tree (base case)
            return;
        }
        add(N.value); // adds the root to the tree
        // gets rid of the root, the left child takes its place by default (Prefix)
        tree.delete(N.value);
        add(tree); // recursive step
    }

    private void add(int n, BNode branch) {
        if (n > branch.value) {
            if (branch.right == null) { // no child to right
                branch.right = new BNode(n); // sets the value as the child
            } else { // if there is already a child, recursively checks that node
                add(n, branch.right);
            }
        } else if (n < branch.value) { // no child to left
            if (branch.left == null) {
                branch.left = new BNode(n);
            } else {
                add(n, branch.left);
            }
        }
    }

    public int depth(int n) { // non-recursive fn, sets up the recursive fn
        return depth(n, root, 1);
    }

    // Returns the depth in the binary tree of a given integer
    private int depth(int n, BNode branch, int depth) {
        if (n == branch.value) { // number found, returns depth of that number
            return depth;
        }
        // moving right thru the tree
        if (n > branch.value && branch.right != null) {
            // increments the depth as we go deeper
            return depth(n, branch.right, depth + 1);
        }
        // moving left thru the tree
        if (n < branch.value && branch.left != null) {
            return depth(n, branch.left, depth + 1);
        }

        return -1; // number not found
    }

    public int countLeaves() {
        return count(root);
    }

    private int count(BNode branch) {
        // counts number of nodes with no children
        if (branch.left == null && branch.right == null) {
            // no children to left nor right
            return 1;
        }
        if (branch.left == null) { // cases where there is only 1 child
            return count(branch.right);
        }
        if (branch.left == null) {
            return count(branch.left);
        }
        // gets number of leaves on left and right recursively
        return count(branch.left) + count(branch.right);
    }

    public int height() {
        return height(root);
    }

    private int height(BNode branch) {
        if (branch.left == null && branch.right == null) {
            // end node
            return 1;
        }
        if (branch.left == null) {
            // cases for 1 child
            return height(branch.right) + 1;
        }
        if (branch.right == null) {
            return height(branch.left) + 1;
        }
        // only want the largest height to avoid counting both branches
        return Math.max(height(branch.left), height(branch.right)) + 1;
    }

    public boolean isAncestor(int ancestor, int child) {
        // finds the ancestor
        BNode A = find(ancestor, root);
        BNode B = null;
        if (A != null) { // A does exist
            B = find(child, A); // searches for child starting from ancestor
        }
        return B != null; // B is found
    }

    private BNode find(int n, BNode branch) {
        // returns the node for a given number (if exists)
        // basically the same concept as add
        if (n == branch.value) {
            return branch;
        }
        if (n > branch.value && branch.right != null) {
            return find(n, branch.right);
        }
        if (n < branch.value && branch.left != null) {
            return find(n, branch.left);
        }

        return null;

    }

    public void delete(int n) {
        BNode N = find(n, root);
        delete(N);
    }

    private void delete(BNode N) {
        if (N.left == null && N.right == null) {
            // deleting the last value
            root = null;
            return;
        } else if (N.left != null) {
            // swaps N with the left value
            BNode L = N.left;
            N.value = L.value;
            if (L.left == null && L.right == null) {
                // deletes the leaf
                N.left = null;
            } else {
                // recursively swaps going toward the leaf
                delete(L);
            }
        } else {
            BNode R = N.right;
            N.value = R.value;
            if (R.left == null && R.right == null) {
                N.right = null;
            } else {
                delete(R);
            }
        }
    }

    public boolean isBalanced() {
        final int height = height();
        return isBalanced(root, height);
    }

    public boolean isBalanced(BNode branch, int height) {
        int depth = depth(branch.value);
        if (height - depth > 1) { // not last 2 depths
            if (branch.left == null || branch.right == null) {
                // if no children, tree is unbalanced
                return false;
            }
        }
        // goes down the tree
        if (branch.left != null) {
            return isBalanced(branch.left, height);
        }
        if (branch.right != null) {
            return isBalanced(branch.right, height);
        }
        return true;
    }

    public void display() {
        System.out.println(this);
    }

    public void display(int choice) {
        String ans = stringify(root, choice);
        if (ans != "") { // gets rid of extra comma
            ans = ans.substring(0, ans.length() - 2);
        }
        System.out.println('<' + ans + '>');
    }

    @Override
    public String toString() {
        String ans = stringify(root, IN);
        if (ans != "") {
            ans = ans.substring(0, ans.length() - 2);
        }
        return '<' + ans + '>';
    }

    private String stringify(BNode branch, int type) {
        if (branch == null) { // empty tree
            return "";
        } else {
            if (type == PRE) { // prefix order
                return branch.value + ", " + stringify(branch.left, type) + stringify(branch.right, type);
            }
            if (type == IN) { // in order
                return stringify(branch.left, type) + branch.value + ", " + stringify(branch.right, type);
            } // post order
            return stringify(branch.left, type) + stringify(branch.right, type) + branch.value + ", ";

        }
    }
}
