//bfs + check if it's last one of this level
public class Solution {
  public List<Integer> rightView(TreeNode root) {
    List<Integer> rightview = new ArrayList<>();
    if (root == null) return rightview;
    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode cur = queue.poll();
        if (i == size - 1){//this is the last one of this level
          rightview.add(cur.key);
        }
        if (cur.left != null) queue.offer(cur.left);
        if (cur.right != null) queue.offer(cur.right);
      }
    }
    return rightview;
  }
}
