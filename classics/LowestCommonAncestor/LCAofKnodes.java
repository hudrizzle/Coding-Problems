public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 *///assume k nodes are all in the tree
//T: O(n)   S: O(1)
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes) {
    Set<TreeNode> set = new HashSet<>();
    for (TreeNode node : nodes) {
      set.add(node);
    }
    return LCArec(root, set);
  }
  public TreeNode LCArec(TreeNode root, Set<TreeNode> nodes) {
    //base case
    if (root == null || nodes.contains(root)) return root;
    //ask children to return any of K nodes if exist in subtree/null/LCA root
    TreeNode left = LCArec(root.left, nodes);
    TreeNode right = LCArec(root.right, nodes);
    //check which to return to parent
    if (left != null && right != null) return root;
    else return left == null ? right : left;
  }
}
