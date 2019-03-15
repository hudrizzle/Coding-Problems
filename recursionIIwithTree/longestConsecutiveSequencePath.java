//find longest path from parent to child with increasingly consecutive keys
//1st method: 3 steps, pass values from bottom upwards
public class Solution {
  public int longestConsecutive(TreeNode root) {
    int[] longest = new int[]{0};
    if (root == null) return longest[0];
    findlongest(root, longest);
    return longest[0];
  }
  public int findlongest(TreeNode root, int[] longest) {
    //base
    if (root == null) return 0;
    if (root.left == null && root.right == null) return 1;//can be ignored
    //ask children
    int left = findlongest(root.left, longest);
    int right = findlongest(root.right, longest);
    //this level, make decision
    int cur = 1;// if none of below work, will return 1 to root's parent starting from current root
    //only work when 3 sitautions: left child can connect with root || right child can connect || both can
    if (left != 0 && root.key + 1 == root.left.key){
      //root has left child and satisfy increasing order
      cur = Math.max(cur, left + 1);
    }
    if (right != 0 && root.key + 1 == root.right.key){
      cur = Math.max(cur, right + 1);
    }
    //if neither one of the children satisfy consecutive order -> cur is still 1
    longest[0] = Math.max(longest[0], cur);  
    return cur;
  }
}
