/** Time: O(n) since there's n nodes in recursion tree, space O(height)
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        findLevelOrder(res, root, 0);
        /*
        for (int i = 0; i < res.size(); i++) {
            if (i % 2 == 1) Collections.reverse(res.get(i));--will take O(n)
        }*/
        return res;
    }
    //list.add(0,****);
    private void findLevelOrder(List<List<Integer>> res, TreeNode root, int level) {
        //base case
        if (root == null) return;
        
        //recursion rule: add node.val to res list
         //create new list when there's not
        if (level == res.size()) res.add(new ArrayList<Integer>());
         //add to head of list when level number is odd
        if (level % 2 == 1) res.get(level).add(0, root.val);
        else res.get(level).add(root.val);
        findLevelOrder(res, root.left, level + 1);
        findLevelOrder(res, root.right, level + 1);
    }
}
