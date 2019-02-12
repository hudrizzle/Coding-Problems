public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
//for BST, in-order is not need since can get from sorting other order
//assume no duplicates
//post order array is composed as: [leftSubTree][rightSubTree][root]
//traverse from last to start of post array, set a min value
//T: O（n）  S:O(height)
public class Solution {
  public TreeNode reconstruct(int[] post) {
    int[] postIndex = new int[]{post.length - 1};
    return reconstructRec(post, postIndex, Integer.MIN_VALUE);
  }
  private TreeNode reconstructRec(int[] post, int[] postIndex, int min) {
    //base: 1.process ends-postIndex == -1; 2.node processing shouldn't be here
    if (postIndex[0] < 0 || post[postIndex[0]] <= min) return null;
    
    TreeNode root = new TreeNode(post[postIndex[0]--]);
    root.right = reconstructRec(post, postIndex, root.key);
    root.left = reconstructRec(post, postIndex, min);
    return root;
  }
}
