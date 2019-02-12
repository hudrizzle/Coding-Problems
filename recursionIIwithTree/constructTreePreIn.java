public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//assume no duplicate keys in the binary tree
//given arrays have the same length, not null
//recursion on tree: reconstruct whole tree -> reconstruct left subtree & right subtree
//time：O（n）、space complexity：O（height + n）？
public class Solution {
  public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
    Map<Integer, Integer> index = new HashMap<>();
    //use a map to record the indices of inOrder elements
    for (int i = 0; i < inOrder.length; i++){
      index.put(inOrder[i], i);
    }
    return reconstructRec(inOrder, 0, inOrder.length - 1, preOrder, 0, preOrder.length - 1, index);
  }
  //inputs must be consistent with subproblems
  private TreeNode reconstructRec(int[] inOrder, int inLeft, int inRight,
                                 int[] preOrder, int preLeft, int preRight,
                                 Map<Integer, Integer> map) {
    //base: empty arrays
    if (inLeft > inRight) return null;
    //recursive rule: what to do at this level?
    TreeNode root = new TreeNode(preOrder[preLeft]);
     //need to use O(1) time to find out index of root in inOrder 
     //so to know left part index: inLeft~(idx - 1), right part index: (idx + 1)~inRight
    int idx = map.get(root.key);
    int leftSize = idx - inLeft;
    int rightSize = inRight - idx;
    root.left = reconstructRec(inOrder, inLeft, idx - 1, preOrder, preLeft + 1, preLeft + leftSize, map);
    root.right = reconstructRec(inOrder, idx + 1, inRight, preOrder, preRight - rightSize + 1, preRight, map);
    return root;
  }
}


//method2--------------------------------------------
//time：O（n）、space complexity：O（height）？
public class Solution {
  public TreeNode reconstruct(int[] inOrder, int[] preOrder) {
    int[] preIndex = new int[]{0};
    int[] inIndex = new int[]{0};
    return reconstructRec(inOrder, preOrder, preIndex, inIndex, Integer.MAX_VALUE);
  }
  //inputs must be consistent with subproblems
  private TreeNode reconstructRec(int[] in, int[] pre, int[] preIdx, int[] inIdx, int target) {
    if (inIdx[0] >= in.length || in[inIdx[0]] == target) return null;
    TreeNode root = new TreeNode(pre[preIdx[0]++]);
    root.left = reconstructRec(in, pre, preIdx, inIdx, root.key);
    inIdx[0]++;
    root.right = reconstructRec(in, pre, preIdx, inIdx, target);
    return root;
  }
}
