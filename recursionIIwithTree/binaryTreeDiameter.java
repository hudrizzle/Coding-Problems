//3 steps: ask children: longest straight path
//this level, check and update max path
//return max(left,right) + 1
//special case: only 1 leaf node!v -> should return 0
public class Solution {
  public int diameter(TreeNode root) {
    int[] maxDiameter = new int[]{0};
    diameter(root, maxDiameter);
    return maxDiameter[0];
  }
  private int diameter(TreeNode root, int[] maxDiameter) {
    //base
    if (root == null) return 0;//empty subtree, caution!
    if (root.left == null && root.right == null) return 1;//leaf
    //ask children
    int left = diameter(root.left, maxDiameter);
    int right = diameter(root.right, maxDiameter);
    //this level, check if update global max
    if (left == 0 && right == 0 || left > 0 && right > 0) {//exclude this situation: left == 0 && right > 0 || left > 0 && right == 0
      maxDiameter[0] = Math.max(maxDiameter[0], left + right + 1);
    }
    return Math.max(left, right) + 1;
  }
}
