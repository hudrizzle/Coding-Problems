/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
/*Assume no duplicate values, values are within valid integer ranges, no Integer.MIN_VALUE or max values
* if there're duplicates, use inOrder traversal to check
*recursion subproblem: if subtree starting with root is BST
*time: O(n), space: O(height), worst O(n)
*/
public class Solution {
  public boolean isBST(TreeNode root) {
    boolean res = isBSTrec(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    return res;
  }
  private boolean isBSTrec(TreeNode root, Integer min, Integer max) {
      //base case
      if (root == null) return true;
      //recursion rule
      if (root.key <= min || root.key >= max) return false;
      return isBSTrec(root.left, min, root.key) && isBSTrec(root.right, root.key, max);
  }
}

//utilize BST property: in order traversal in recursive way(not using global variable)
//time: O(n) worst, may end in advance   Space: O(height) call stack
public class Solution {
  public boolean isBST(TreeNode root) {
    boolean[] res = new boolean[]{true};
    TreeNode[] last = new TreeNode[]{null};
    inorder(root, res, last);
    return res[0];
  }
  private void inorder(TreeNode root, boolean[] res, TreeNode[] last) {
      //base case
      if (root == null || res[0] == false) return;
      //recursion rule
      inorder(root.left, res, last);
      if (last[0] != null && last[0].key >= root.key) res[0] = false;
      last[0] = root;
      inorder(root.right, res, last);
  }
}

//inorder iteratively
public class Solution {
  public boolean isBST(TreeNode root) {
    Deque<TreeNode> stack = new ArrayDeque<>();
    pushLeft(root, stack);
    TreeNode last = null;
    while (!stack.isEmpty()) {
        TreeNode cur = stack.pollFirst();
        if (last != null && last.key >= cur.key) return false;
        last = cur;
        if (cur.right != null) pushLeft(cur.right, stack);
    }
    return true;
  }
  //push all left nodes until null starting from root to stack
  private void pushLeft(TreeNode root, Deque<TreeNode> stack){
    while (root != null) {
        stack.offerFirst(root);
        root = root.left;
    }
  }
