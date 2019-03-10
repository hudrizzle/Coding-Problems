//better way to do is by In-order traversal
//as long as output of in-order is increasing, it's good
public class Solution {
  public boolean isBST(TreeNode root) {
    boolean[] isBST = new boolean[]{true};
    int[] prev = new int[]{Integer.MIN_VALUE};
    inOrder(root, prev, isBST);
    return isBST[0];
  }
  private void inOrder(TreeNode root, int[] prev, boolean[] isBST){
    if (root == null || !isBST[0]) return;
    inOrder(root.left, prev, isBST);
    //root itself
    if (root.key <= prev[0]) {
      isBST[0] = false;
      return;
    }
    prev[0] = root.key;
    inOrder(root.right, prev, isBST);   
  }
}

//pass max,min from top to downside, but cannot work if tree contains Integer.MAX_VALUE or Integer.MIN_VALUE
public class Solution {
  public boolean isBST(TreeNode root) {
    return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  private boolean isBST(TreeNode root, int min, int max){
    if (root == null) return true;
    if (root.key >= max || root.key <= min) {
      return false;
    }
    return isBST(root.left, min, root.key) && isBST(root.right, root.key, max);
  }
}
