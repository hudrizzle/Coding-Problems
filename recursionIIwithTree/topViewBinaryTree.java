public class Solution {
  public class ColNode{//wrapper class: to record the column of each node on the tree
    public int column;
    public TreeNode node;
    public ColNode(TreeNode node, int col) {
      this.node = node;
      this.column = col;
    }
  }
  public List<Integer> topView(TreeNode root) {
    List<Integer> topview = new ArrayList<>();
    if (root == null) return topview;
    //make sure nodes added to queue are not null
    Deque<ColNode> queue = new ArrayDeque<>();
    //put <key, value> -> <col, node.key>
    Map<Integer, Integer> occur = new HashMap<>();
    queue.offer(new ColNode(root, 0));
    occur.put(0, root.key);
    int leftmost = 0, rightmost = 0;
    //traverse the dynamic growing list until the last element
    while (!queue.isEmpty()) {
      ColNode cur = queue.poll();
      TreeNode curnode = cur.node;
      //add cur's left child, right child if not null
      if (curnode.left != null) {
        queue.offer(new ColNode(curnode.left, cur.column - 1));
        //only when first time coming across this column should we add it to occurance map
        if (!occur.containsKey(cur.column - 1)) {
          occur.put(cur.column - 1, curnode.left.key);
          leftmost = Math.min(leftmost, cur.column - 1);
        }
      }
      if (curnode.right != null) {
        queue.offer(new ColNode(curnode.right, cur.column + 1));
        if (!occur.containsKey(cur.column + 1)) {
          occur.put(cur.column + 1, curnode.right.key);
          rightmost = Math.max(rightmost, cur.column + 1);
        }
      }
    }
    //traverse the map to put the values into res list according to columns
    for (int i = leftmost; i <= rightmost; i++) {
      topview.add(occur.get(i));
    }
    return topview;
  }
}
