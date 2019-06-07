/*clarify: result must be strictly smaller than target
trick - only update res when going to right
if always going to left, meaning all the time root.key >= target, so can't find valid result
*/
public class Solution {
  public int largestSmaller(TreeNode root, int target) {
    //corner cases
    if (root == null || target <= 1) return Integer.MIN_VALUE;
    //initialize optimal value, need to check if res < target!
    int res = root.key;
    while (root != null) {
      //-wrong: don't check and update res here
      //int curdiff = target - root.key;
      //if (curdiff > 0 && curdiff < target - res) res = root.key;
      if (root.key >= target) root = root.left;
      else if (root.key < target) {//going right, find a potential result
        res = root.key;
        root = root.right;
      }
    }
    return res < target ? res : Integer.MIN_VALUE;
  }
}

//recommended, initliaze as a TreeNode
public class Solution {
  public int largestSmaller(TreeNode root, int target) {
    //initialize optimal value, need to check if res < target!
    TreeNode res = null;
    while (root != null) {
      if (root.key >= target) root = root.left;
      else if (root.key < target) {//going right, find a potential result
        res = root;
        root = root.right;
      }
    }
    return res == null ? Integer.MIN_VALUE : res.key;
  }
}
