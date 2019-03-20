//no duplicates, The smallest larger node is first candidate after deletion
//delete node with key in subtree starting with root. return the updated subtree root
//T and S: O(height)
public class Solution {
  //first search-> if in tree, delete; if not in tree, remain same
  public TreeNode deleteTree(TreeNode root, int key) {
    //corner case
    if (root == null) return root;
    
    if (root.key < key) {//go right
      root.right = deleteTree(root.right, key);
    }else if (root.key > key) {//go left
      root.left = deleteTree(root.left, key);
    }else {//found the key: root.key == key
      if (root.left == null) {//root has no left child(right child might be null or not)
        return root.right;
      }else if (root.right == null){
        return root.left;
      }
      //else : root has both left and right child -> find the successor: leftmost of the right subtree
      TreeNode successor = findSuccessor(root.right);
      //first delete successor from right subtree
      root.right = deleteTree(root.right, successor.key);
      //then replace the root value with successor.key
      root.key = successor.key;        
    }
    return root;
  }
  //find the leftmost of subtree
  private TreeNode findSuccessor(TreeNode root) {
    TreeNode leftmost = null;
    while (root != null) {
      leftmost = root;
      root = root.left;
    }
    return leftmost;
  }
}
