//use 1 recursion function to solve: if found, pass back the height, otherwise, return -1
//only when returned to LCA, will get 2 ints both > 0
//time: O(n), space:O(height)
public class Solution {
  public int distance(TreeNode root, int k1, int k2) {
    int[] distance = new int[]{Integer.MIN_VALUE};
    distance(root, k1, k2, distance);
    return distance[0];
  }
  private int distance(TreeNode root, int k1, int k2, int[] distance) {
    //base cases 1. updated final result, no need to continue
    if (root == null || distance[0] != Integer.MIN_VALUE) return -1;
    //recursion calls, goes before basecase2 because 
    //need to go through all nodes to avoid subjection situation
    int left = distance(root.left, k1, k2, distance);
    int right = distance(root.right, k1, k2, distance);

    //determine what to return & if update result in this level
    if (root.key == k1 || root.key == k2) {
        //subjection situation: root is one of k1,k2 and the other is in its subtree
        if (left >= 0 || right >= 0){
          distance[0] = (left >= 0 ? left + 1 : right + 1);
          return Math.max(right, left) + 1; 
      }
      //not subjection, but root is one of the targets
      return 0;
    }
    
    //both of them returns -1
    if (left == -1 && right == -1) return -1;
    
    //if root is lca of k1, k2 -> found both, update final result
    if (left >= 0 && right >= 0) {
      distance[0] = left + right + 2;
      //return Math.max(right, left) + 1 -> found, early return can also work
    } 
    
    //one of them returns -1 and the other returns >= 0
    return Math.max(right, left) + 1;
  }
}
