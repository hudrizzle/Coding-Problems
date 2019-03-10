//review BST iterative traversal, push left function- push left subtree into stack
public class Solution {
  public List<Integer> inOrder(TreeNode root) {
    List<Integer> inorder = new ArrayList<>();
    Deque<TreeNode> stack = new ArrayDeque<>();
    pushLeft(root, stack);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pop();
      inorder.add(cur.key);
      if (cur.right != null) {
        pushLeft(cur.right, stack);
      }
    }
    return inorder;
  }
  //push the left subtree into stack starting from root node
  private void pushLeft(TreeNode root, Deque<TreeNode> stack) {
    while(root != null) {
      stack.push(root);
      root = root.left;
    }
  }
}

//recursive method traversal
public class Solution {
  public List<Integer> inOrder(TreeNode root) {
    List<Integer> inorder = new ArrayList<>();
    inOrder(root, inorder);
    return inorder;
  }
  private void inOrder(TreeNode root, List<Integer> res) {
    if (root == null) return;
    inOrder(root.left, res);
    //root itself
    res.add(root.key);
    inOrder(root.right, res);
  }
}
