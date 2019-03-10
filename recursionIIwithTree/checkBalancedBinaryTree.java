//return: if balanced, return height; if not balanced, return -1
//O(n) time, O(height) space
public class Solution {
  public boolean isBalanced(TreeNode root) {
    return getHeight(root) == -1 ? false : true;
  }
  private int getHeight(TreeNode root) {
    if (root == null) return 0;
    int left = getHeight(root.left);
    int right = getHeight(root.right);
    if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
    return Math.max(left, right) + 1;
  }
}
