/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */


public class BSTIterator {
    /*
    * @param root: The root of binary tree.
    */
    Deque<TreeNode> stack;
    //stack used to save the straight path from root to current node
    public BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        while (root != null) {//all the way go left until null
            stack.push(root);
            root = root.left;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        if (!hasNext()) throw new NoSuchElementException();
        TreeNode next = stack.pop();
        //each time pop one element, check if it has right child: 
        //yes, push its right child's left path into stack; no, continue pop from stack
        TreeNode right = next.right;
        while (right != null) {
            stack.push(right);
            right = right.left;
        }
        return next;
    }
}
