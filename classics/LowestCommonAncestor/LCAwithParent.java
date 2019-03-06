c class TreeNodeP {
 *   public int key;
 *   public TreeNodeP left;
 *   public TreeNodeP right;
 *   public TreeNodeP parent;
 *   public TreeNodeP(int key, TreeNodeP parent) {
 *     this.key = key;
 *     this.parent = parent;
 *   }
 * }
 */
//assume: given nodes are not necessarily in the tree
//adjust the diff to the same level, then move upwards synchronously 
//time: O(height), S:O(1)
public class Solution {
  public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
    int a = getHeight(one);
    int b = getHeight(two);
    TreeNodeP lca;
    if (a <= b) {
      lca = getLCA(one, two, b - a);
    }else {
      lca = getLCA(two, one, a - b);
    }
    return lca;
  }
  //T:O(height)
  private TreeNodeP getLCA(TreeNodeP shorter, TreeNodeP longer, int diff) {
    while (diff > 0) {
      longer = longer.parent;
      diff--;
    }
    while (longer != shorter) {
      longer = longer.parent;
      shorter = shorter.parent;
    }
    return longer;
  }
  //traverse upper until root  - O(height)
  private int getHeight(TreeNodeP node) {
    int height = 0;
    while (node != null) {
      height++;
      node = node.parent;
    }
    return height;//will be 1 at least
  }
}
