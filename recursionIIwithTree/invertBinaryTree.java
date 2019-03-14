public class Solution {
  public TreeNode invertTree(TreeNode root) {
    //basic cases
    if (root == null || root.left == null && root.right == null) return root;
    //this level(swap left and right child)
    TreeNode left = invertTree(root.left);
    root.left = invertTree(root.right);
    root.right = left;
    return root;
  }
}
