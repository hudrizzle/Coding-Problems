public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//preorder + DP(max subarray sum)
//T: O(n), space: O(1)
public class Solution {
  public int maxPathSum(TreeNode root) {
    //corner case
    if (root == null) return 0;
    //prevSum used to record the sum till last node
    //List<Integer> prevSum = new ArrayList<>();
    //prevSum.add(0);
    int[] max = new int[]{Integer.MIN_VALUE};
    helper(root, 0, max);
    return max[0];
  }
  private void helper(TreeNode root, int curMaxSum, int[] max) {
    //base
    if (root == null) return;
    //what you do in this level
    if (curMaxSum < 0) curMaxSum = root.key;
    else curMaxSum += root.key;
    max[0] = Math.max(max[0], curMaxSum);
    //recursion call
    helper(root.left, curMaxSum, max);
    helper(root.right, curMaxSum, max);
  }
}
