public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//assume no duplicates in tree, a and b may not in the tree
//counsin satisfies: 1. same level 2.different parent
//bfs: since it's related to level traversal
//verify if same parent->when expanding a node, if left, right are a,b then false
//time: O(n)  space: O(# of nodes in largest level) -> worst: O(n/2)
public class Solution {
  public boolean isCousin(TreeNode root, int a, int b) {
    if (root == null) return false;
    Queue<TreeNode> levelNodes = new LinkedList<>();
    boolean foundA = false;
    boolean foundB = false;
    levelNodes.offer(root);
    while(!levelNodes.isEmpty()) {
      int size = levelNodes.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = levelNodes.poll();
        TreeNode left = cur.left;
        TreeNode right = cur.right;
        if (left != null) levelNodes.offer(left);
        if (right != null) levelNodes.offer(right);
        //check if left and right respectively is A and B -> if so, they have same parent
        if ((equal(left, a) && equal(right, b)) || (equal(left, b) && equal(right, a))) {
          return false;
        }
        //!!!note: if A or B is already found, then don't update to false!!!
        if (!foundA) foundA = (equal(left, a) || equal(right, a));
        if (!foundB) foundB = (equal(left, b) || equal(right, b));
      }
      //after traversing this level, check if they're in the same level
      if (foundA && foundB) return true;//found in the same level
      if (foundA || foundB) return false;//found in different level
    }
    return false;//both not found
  }
  private boolean equal(TreeNode cur, int a) {
    if (cur == null) return false;
    else if (cur.key == a) return true;
    else return false;
  }
}
