//dfs on binary tree
//caution: not sure if the key is "12" or "1" so use String cur to record the added string size
//base case: must only when you arrive leaf nodes can you add path to list
//root==null is not necessarily the basic case since sometimes root has only one child, this cannot be printed

public class Solution {
  public String[] binaryTreePaths(TreeNode root) {
    List<String> path = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    findpath(root, path, sb);
    String[] res = new String[path.size()];
    res = path.toArray(res);
    return res;
  }
  private void findpath(TreeNode root, List<String> path, StringBuilder sb) {
    //base case
    if (root == null) return;
    if (root.left == null && root.right == null) {//leaf node, return after update res
      String leaf = String.valueOf(root.key);
      sb.append(leaf);
      path.add(sb.toString());
      sb.delete(sb.length() - leaf.length(), sb.length());
      return;
    }
    //recursive rule
    String cur = root.key + "->";
    sb.append(cur);
    findpath(root.left, path, sb);
    findpath(root.right, path, sb);
    sb.delete(sb.length() - cur.length(), sb.length());//remove substring
  }
}
