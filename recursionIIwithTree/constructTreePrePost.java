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
//use hashmap since optimizing search complexity to 1.  time:O(n) space:O(n) since used hashmap
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> postidx = new HashMap<>();
        for (int i = 0; i < post.length; i++) postidx.put(post[i], i);
        int[] idx = new int[]{0};
        return build(pre, post, postidx, idx, 0, post.length - 1);
    }
    private TreeNode build(int[] pre, int[] post, Map<Integer, Integer> postidx, 
                           int[] idx, int poststart, int postend) {
        //basic cases
        if (postend < poststart) return null;
        if (postend == poststart) return new TreeNode(pre[idx[0]++]);
        
        //recursion rule
        int leftrootidx = postidx.get(pre[idx[0] + 1]);
        TreeNode root = new TreeNode(pre[idx[0]++]);
        root.left = build(pre, post, postidx, idx, poststart, leftrootidx);
        root.right = build(pre, post, postidx, idx, leftrootidx + 1, postend - 1);
        return root;
    }
}

//brute force searching && (you don't need to, better way to do is to use a pointer simulating copying array)copy pre,post for each recursion: O(n^2)
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        int N = pre.length;
        if (N == 0) return null;
        TreeNode root = new TreeNode(pre[0]);
        if (N == 1) return root;

        int L = 0;
        for (int i = 0; i < N; ++i)
            if (post[i] == pre[1])
                L = i+1;

        root.left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L+1),
                                         Arrays.copyOfRange(post, 0, L));
        root.right = constructFromPrePost(Arrays.copyOfRange(pre, L+1, N),
                                          Arrays.copyOfRange(post, L, N-1));
        return root;
    }
}
