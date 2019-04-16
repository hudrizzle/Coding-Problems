//use stack, prev to determine current direction of moving
public class Solution {
  public List<Integer> postOrder(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    TreeNode prev = null;//only when first time prev == null
    while (!stack.isEmpty()) {
      //peek instead of pop since can't decide right now
      TreeNode cur = stack.peek();
      //if right now going down
      if (prev == null || prev.left == cur || prev.right == cur) {
        //if has left child, push it in first
        if (cur.left != null) stack.push(cur.left);
        //or if has right child, push it in
        else if (cur.right != null) stack.push(cur.right);
        //or it has no children -> leaf -> means finished it's left, right subtree->can pop
        else {
          stack.pop();
          res.add(cur.key);
        }
      }else if (prev == cur.left && cur.right != null) {
        //going up from downside(left) and can go right next
        stack.push(cur.right);
      }else {//going up from left side but no right child OR going up from right side->finished right subtree
        stack.pop();
        res.add(cur.key);
      }
      prev = cur;
    }
    return res;
  }
}

//reverse the revised preOrder-2(root, right, left)
public class Solution {
  public List<Integer> postOrder(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    if (root == null) {
      return ans;
    }
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pop();
      ans.add(cur.key);
      if (cur.left != null) {
        stack.push(cur.left);
      }
      if (cur.right != null) {
        stack.push(cur.right);
      }
    }
    Collections.reverse(ans);
    return ans;
  }
}
//similar to above method
//use stack, but put into list in a reverse order. (root, right, left) reverse, so put left first, then right
public class Solution {
  public List<Integer> postOrder(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    Deque<TreeNode> stack = new ArrayDeque<>();
    stack.offerFirst(root);
    while (!stack.isEmpty()) {
      TreeNode cur = stack.pollFirst();
      res.add(0, cur.key);//always add cur node to the head of list
      if (cur.left != null) stack.offerFirst(cur.left);
      if (cur.right != null) stack.offerFirst(cur.right);
    }
    return res;
  }
}

//not use stack but just a pointer instead?
