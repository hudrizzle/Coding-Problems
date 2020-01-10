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
 //check if left subtree and right subtree are symmetric
 //O(n/2) time and O(height) space
public class Solution {
  public boolean isSymmetric(TreeNode root) {
    //corner case
    if (root == null) return true;
    return isSymmetric(root.left, root.right);
  }
  private boolean isSymmetric(TreeNode one, TreeNode two) {
    //corner cases: any of them == null, value not equal
    if (one == null && two == null) {
      return true;
    }else if (one == null || two == null) {
      return false;
    }else if (one.key != two.key) {
      return false;
    }
    //recursion
    return isSymmetric(one.left, two.right) && isSymmetric(one.right, two.left);
  }
}
