public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//from leaf to leaf, may or may not go through root ("人")
//can be leaf node itself???- assume cannot -> note to check if the root is a full node->return differently
//might contain negative nodes! 
//Time: O(n), space: O(height)
public class Solution {
  public int maxPathSum(TreeNode root) {
    int[] max = new int[]{Integer.MIN_VALUE};
    if (root == null) return max[0];
    SumRec(root, max);
    return max[0];
  }
  //returns a max sum of a straight path from root to one of its leaf
  private int SumRec(TreeNode root, int[] max) {
    //base
    if (root == null) return 0;
    //ask children to return max sum subpath straight
    int left = SumRec(root.left, max);
    int right = SumRec(root.right, max);
    //this level candidate, update max if this root has full 2 children
    if (root.left != null && root.right != null){
      max[0] = Math.max(max[0], left + right + root.key);
      return Math.max(left, right) + root.key;
    }
    //else(either child or both are null)-> return a max sum of subpath to root's parent
    return root.right == null ? left + root.key : right + root.key;
  }
}
