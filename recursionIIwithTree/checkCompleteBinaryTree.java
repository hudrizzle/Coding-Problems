//once there's a null, no more numbers to come
//O(n) time, O(n) space -> max number of nodes on each level
public class Solution {
  public boolean isCompleted(TreeNode root) {
    if (root == null) {
      return true;
    }
    //only LinkedList can contain null, ArrayDeque cannot
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    boolean hasNull = false;
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      if (hasNull && cur != null) return false;//meet another null
      if (cur == null) {
        hasNull = true;
        continue;
      }
      queue.offer(cur.left);
      queue.offer(cur.right);
    }
    return true;
  }
}
