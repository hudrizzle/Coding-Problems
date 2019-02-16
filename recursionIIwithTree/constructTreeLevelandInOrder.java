public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//binary tree, arrays not null, same length, no duplicates in tree
//need a map of (inOrder element, its index) since need to retrieve the index each time
//time: n^2,  space: height * n??
public class Solution {
  public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
    Map<Integer, Integer> map = new HashMap<>();
    List<Integer> level = new ArrayList<>();
    for (int i = 0; i < inOrder.length; i++) map.put(inOrder[i], i);
    for (int cur : levelOrder) level.add(cur);
    //actually with map, you dont need inOrder array any more!
    //don't need levelOrderIndex since you pass the updated list of levelOrder instead of the whole levelOrder array!
    return reconstructRec(level, map);
  }
  private TreeNode reconstructRec(List<Integer> level, Map<Integer, Integer> map) {
    //base:level empty
    if (level.isEmpty()) return null;
    
    TreeNode root = new TreeNode(level.remove(0));//not using level.get(0) since later need iterating through
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    //scan level list to populate left and right subtree level order
    for (int num : level) {
      if (map.get(num) < map.get(root.key)) left.add(num);
      else right.add(num);
    }
    root.left = reconstructRec(left, map);
    root.right = reconstructRec(right, map);
    return root;
  }
}
