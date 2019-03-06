//the first node n we encounter with value between n1 and n2, i.e., n1 < n < n2 or same as one of the n1 or n2
//is LCA of n1 and n2 (assuming that n1 < n2). 
//So just recursively traverse the BST in, if node’s value is greater than both n1 and n2 then our LCA lies in left side of the node, 
//if it’s is smaller than both n1 and n2, then LCA lies on right side. 
//Otherwise root is LCA (assuming that both n1 and n2 are present in BST)
//time: O(height), space:O(height) 

public class Solution {
  public TreeNode lca(TreeNode root, int p, int q) {
    //corner case
    if (root == null) return root;

    if (root.key < p && root.key < q) return lca(root.right, p, q);
    if (root.key > p && root.key > q) return lca(root.left, p, q);
    //if root.key is in between of or equals to p, q -> found root to be lca
    return root;
  }
}
