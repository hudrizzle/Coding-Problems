//similar to bfs, but use list instead of queue since we wanna traverse treenode with index
//and not poll when generating and mark its children
//time: O(n), space:O(n)
public class Solution {
  public class NodeCol{//wrapper class: to record the column of each node on the tree
    public TreeNode node;
    public int column;
    public NodeCol(int column, TreeNode node) {
      this.column = column;
      this.node = node;
    }
  }
  public List<List<Integer>> verticalPrint(TreeNode root) {
    List<List<Integer>> vertical = new ArrayList<>();
    if (root == null) return vertical;
    //make sure nodes added to queue are not null
    List<NodeCol> queue = new ArrayList<>();
    queue.add(new NodeCol(0, root));
    int it = 0;
    int leftmost = 0, rightmost = 0;
    //traverse the dynamic growing list until the last element
    while (it < queue.size()) {
      NodeCol cur = queue.get(it);
      leftmost = Math.min(leftmost, cur.column);
      rightmost = Math.max(rightmost, cur.column);
      //add cur's left child, right child if not null
      if (cur.node.left != null) queue.add(new NodeCol(cur.column - 1, cur.node.left));
      if (cur.node.right != null) queue.add(new NodeCol(cur.column + 1, cur.node.right));
      it++;
    }
    int range = rightmost - leftmost + 1;
    //traverse the queue list to put generated nodes into vertical list according to their column
    for (int j = 0; j < range; j++) vertical.add(new ArrayList<Integer>());
    for (NodeCol next : queue) {
      vertical.get(next.column - leftmost).add(next.node.key);
    }
    return vertical;
  }
}

//bfs: still use queue, but use another map to record col:[] list of nodes on this column
public class verticalview {
	  public List<List<Integer>> verticalPrint(TreeNode root) {
	    List<List<Integer>> vertical = new ArrayList<>();
	    if (root == null) return vertical;
	    //make sure nodes added to queue are not null
	    Deque<TreeNode> queue = new ArrayDeque<>();
	    Map<Integer, List<Integer>> memo = new HashMap<>();
	    queue.offer(root);
	    root.column = 0;
	    memo.put(root.column, new ArrayList<Integer>());
	    memo.get(root.column).add(root.key);
	    int leftmost = 0, rightmost = 0;
	    //traverse the dynamic growing list until the last element
	    while (!queue.isEmpty()) {
	      TreeNode cur = queue.poll();
	      //add cur's left child, right child if not null
	      if (cur.left != null) {
	        queue.offer(cur.left);
	        cur.left.column = cur.column - 1;
	        leftmost = Math.min(leftmost, cur.column - 1);
	        if (memo.get(cur.left.column) == null) memo.put(cur.left.column, new ArrayList<Integer>());
	        memo.get(cur.left.column).add(cur.left.key);
	      }
	      if (cur.right != null) {
	        queue.offer(cur.right);
	        cur.right.column = cur.column + 1;
	        rightmost = Math.max(rightmost, cur.column + 1);
	        if (memo.get(cur.right.column) == null) memo.put(cur.right.column, new ArrayList<Integer>());
	        memo.get(cur.right.column).add(cur.right.key);
	      }
	    }
	    //traverse the map to put the values into res list according to columns
	    for (int i = leftmost; i <= rightmost; i++) {
	      vertical.add(memo.get(i));
	    }
	    return vertical;
	  }
	}
