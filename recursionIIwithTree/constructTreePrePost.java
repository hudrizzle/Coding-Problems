/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//note there's one corner case in recursion: a subtree may not have right or left child, which lead to a invalid start,end subset
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> postidx = new HashMap<>();
        //Map<Integer, Integer> preidx = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            postidx.put(post[i], i);
        }
        //for (int i = 0; i < pre.length; i++) {
        //    preidx.put(pre[i], i);
        //}
        int[] idx = new int[]{0};
        return build(pre, post, postidx, idx, 0, post.length - 1);
    }
    private TreeNode build(int[] pre, int[] post, Map<Integer, Integer> postidx, 
                           int[] idx, int poststart, int postend) {
        //basic cases
        if (postend < poststart) return null;//it's possible when there's no left/right child
        if (postend == poststart) return new TreeNode(pre[idx[0]++]);
        //recursion rule
        int leftrootidx = postidx.get(pre[idx[0] + 1]);
        TreeNode root = new TreeNode(pre[idx[0]++]);
        root.left = build(pre, post, postidx, idx, poststart, leftrootidx);
        root.right = build(pre, post, postidx, idx, leftrootidx + 1, postend - 1);
        return root;
    }
}
