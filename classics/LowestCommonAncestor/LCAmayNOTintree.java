public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//assume a,b may not in the tree, return null if not exist
//2 relationship between a,b: 1. a/b is in the subtree of the other; 2. they are in seperate subtrees
//note if situation 1, need to check again if the other is in the subtree
//time: O(n)   space: O(height)
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
    TreeNode lca = lowestCommonAncestorRec(root, one, two);
    if (lca == one || lca == two){//check again for situation 2
      TreeNode rootNode = (lca == one ? one : two);
      TreeNode toCheck = (lca == one ? two : one);
      TreeNode checkLCA = lowestCommonAncestorRec(rootNode, toCheck, toCheck);
      if (checkLCA == null) return null;//toCheck is not in the tree
    }
    return lca;
  }
  private TreeNode lowestCommonAncestorRec(TreeNode root,
      TreeNode one, TreeNode two) {
    //base
    if (root == null || root == one || root == two) return root;
    //ask children
    TreeNode left = lowestCommonAncestorRec(root.left, one, two);
    TreeNode right = lowestCommonAncestorRec(root.right, one, two);
    //decide in this level
    if (left != null && right != null) return root;//situation 2
    return left == null? right : left;//situation 1, need to check
  }
}
