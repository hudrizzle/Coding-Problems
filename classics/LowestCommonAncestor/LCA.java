public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//assume one,two are all in tree
//the relationship they might be: 1. one is on the subtree of another; 2.they are not on the subtree of each other's.
//three steps: Step 1. root asks children to return A/B if found one, or return root itself if found both
//Step 2.in this level, do: check left, right: if one of them == null, return not null; if both not null, return root
//Step 3.return according to step 2
//Time: O(n), space: O(height)
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
    //base case
    if (root == null || root == one || root == two) {
      return root;
    }
    //this level
    TreeNode left = lowestCommonAncestor(root.left, one, two);
    TreeNode right = lowestCommonAncestor(root.right, one, two);
    if (left != null && right != null) return root;
    return left == null ? right:left;
  }
}
