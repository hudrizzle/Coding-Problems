//3 parts to collect borders:
//left border(except root, leaf), leaf nodes, right border(except leaf, root)
public class Solution {
  public List<Integer> borderView(TreeNode root) {
    List<Integer> border = new ArrayList<>();
    if (root == null) return border;
    border.add(root.key);//note that root is already added at first
    //1.left border collection using preorder(root -> left)
    leftborder(root.left, border);
    //2.leaf nodes collection using preorder(left == null && right == null)
    leaf(root.left, border);
    leaf(root.right, border);
    //3.right border collection using postorder(right -> root)
    rightborder(root.right, border);
    return border;
  }
  private void leftborder(TreeNode root, List<Integer> border) {
    //base cases: skip nodes which are null and leaves
    if (root == null || root.left == null && root.right == null) return;
    border.add(root.key);
    //only go to the left child of each node, if no left child, then go right child
    if (root.left != null) {
      leftborder(root.left, border);
    }else {
      leftborder(root.right, border);
    }
  }
  private void leaf(TreeNode root, List<Integer> border) {
    //base
    if (root == null) return;
    if (root.left == null && root.right == null) border.add(root.key);
    leaf(root.left, border);
    leaf(root.right, border);
  }
  private void rightborder(TreeNode root, List<Integer> border) {
    //skip nodes that are null and leaves
    if (root == null || root.left == null && root.right == null) return;
    if (root.right != null) {
      rightborder(root.right, border);
    }else {
      rightborder(root.left, border);
    }
    border.add(root.key);
  }
}
