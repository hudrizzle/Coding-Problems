public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
// Find the maximum possible sum from any node to any node (path can be "人")
//(the start node and the end node can be the same). 
//1.children returns maxPathSum of single path: a, b
//2.this level, update global max - cancel the negative contributions
//3.return to parent: maxPathSum of single path including root's value(important! need to be contiguous)
//T:O(n), S:O(height)
public class Solution {
  public int maxPathSum(TreeNode root) {
    int[] globalMax = new int[]{Integer.MIN_VALUE};
    maxSinglePathSum(root, globalMax);
    return globalMax[0];
  }
  //returns max sum of single path including root itself
  private int maxSinglePathSum(TreeNode root, int[] globalMax) {
    //base
    if (root == null) return 0;
    //ask children to return single path sum max
    int left = maxSinglePathSum(root.left, globalMax);
    int right = maxSinglePathSum(root.right, globalMax);
    //check if update global max
    left = left > 0? left : 0;
    right = right > 0? right : 0;
    globalMax[0] = Math.max(globalMax[0], left + right + root.key);
    //return value to parent
    return Math.max(left, right) + root.key;
  }
}
