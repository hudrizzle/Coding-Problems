public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//sol1. linear scan path, for each node: look back to check sums
//sol2. record prefixSum till now, check- if (curSum - target) in set of prefixSum.
//remember to add 0 into set of prefixSum!!! and curSum used to track current sum value
//T:O(n)  S:O(height) call stack and set
public class Solution {
  public boolean exist(TreeNode root, int target) {
    Set<Integer> prefixSum = new HashSet<>();
    prefixSum.add(0);
    return traverse(root, target, prefixSum, 0);  
  }
  private boolean traverse(TreeNode root, int target, 
                           Set<Integer> prefixSum, int curSum) {
    //corner case
    if (root == null) return false;
    //what you do in this level:check if yes- return true; else add to prefixSum set
    curSum += root.key;
    if (prefixSum.contains(curSum - target))  return true;
    
    boolean needRemove = prefixSum.add(curSum);
    if (traverse(root.left, target, prefixSum, curSum)) return true;
    if (traverse(root.right, target, prefixSum, curSum)) return true;
    //remember to remove if need to go back to parent level!
    if (needRemove) {
      prefixSum.remove(curSum);
    }
    return false;
  }
}
