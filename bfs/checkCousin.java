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

----------------------------- METHOD2-> refer to LCA
//tree recursion -> LCA 3 steps:
//ask children: level where found a/b (if not found, return -1)
//this level: check left, right-> update res if necessary(to true) -> when left == right and left - curDep > 1
//return the one that's not -1, or return max(left, right), why? necessary to continue? 
//time: O(n), space: O(height)
public class Solution {
  public boolean isCousin(TreeNode root, int a, int b) {
    boolean[] res = new boolean[]{false};//global res indicating if a and b are cousin
    isCousinRec(root, a, b, 0, res);
    return res[0];
  }
  private int isCousinRec(TreeNode root, int a, int b, int curDep, boolean[] res){
    //base case
    if (root == null) return -1;
    if (root.key == a || root.key == b) return curDep;
    //ask children return depth of a or b if found, or return -1 if not
    int left = isCousinRec(root.left, a, b, curDep + 1, res);
    int right = isCousinRec(root.right, a, b, curDep + 1, res);
    //in this level, check if need to update res to true, only when:
    if (left == right && left - curDep > 1) res[0] = true;
    //return depth value to root's parent
    return Math.max(left, right);
  }
}
