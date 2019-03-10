//bottom up, leaf -> returns 1
  public int minDepth(TreeNode root) {
    if (root == null) return 0;
    return findMinDepth(root);
  }
  private int findMinDepth(TreeNode root) {
    if (root == null) return Integer.MAX_VALUE;
    if (root.left == null && root.right == null) return 1;
    return Math.min(findMinDepth(root.left),findMinDepth(root.right)) + 1;
  }

//top down
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return findMinDepth(root, 1);
    }
    private int findMinDepth(TreeNode root, int curDep) {
        //base
        if (root == null) return Integer.MAX_VALUE;
        if (root.left == null && root.right == null) return curDep;
        //recursion rule
        return Math.min(findMinDepth(root.left, curDep + 1), findMinDepth(root.right, curDep + 1));
    }
