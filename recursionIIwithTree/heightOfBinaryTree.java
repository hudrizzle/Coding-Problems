//recursion
//height: how many levels from this node to deepest leaf node?
public class Solution {
  public int findHeight(TreeNode root) {
    if (root == null) return 0;
    int left = findHeight(root.left);
    int right = findHeight(root.right);
    return Math.max(left, right) + 1;
  }
}
