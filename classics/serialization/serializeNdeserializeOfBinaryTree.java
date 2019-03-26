 //use preorder traversal
public class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    static String SPLITER = ",";
    static String NULL = "#";
    
    public String serialize(TreeNode root) {
        //corner case
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        build(root, sb);
        return sb.toString();
    }
    private void build(TreeNode root, StringBuilder sb) {
        //base case
        if (root == null) {
            sb.append(NULL);
            sb.append(SPLITER);
            return;
        }
        //this level, add root.key to result array, and a SPLITER
        sb.append(root.val);
        sb.append(SPLITER);
        build(root.left, sb);
        build(root.right, sb);
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        //corner case
        if (data == "") return null;
        String[] input = data.split(SPLITER);
        int[] index = new int[]{0};
        //build the subtree with root of input[index[0]];
        return recover(index, input);
    }
    private TreeNode recover(int[] index, String[] input) {
        //base case
        if (input[index[0]].equals(NULL)){
            index[0]++;
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(input[index[0]]));
        index[0]++;
        root.left = recover(index, input);
        root.right = recover(index, input);
        return root;
    }
}

 //use level order traversal: 
 //note if encountered "#" dont put null into queue, only put those need to be expanded later
public class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    static String SPLITER = ",";
    static String NULL = "#";
    
    public String serialize(TreeNode root) {
        //corner case
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        sb.append(root.val);
        sb.append(SPLITER);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null){
                queue.offer(cur.left);
                sb.append(cur.left.val);
            }else {
                sb.append(NULL);
            }
            sb.append(SPLITER);
            if (cur.right != null){
                queue.offer(cur.right);
                sb.append(cur.right.val);
            }else {
                sb.append(NULL);
            }
            sb.append(SPLITER);
        }
        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        //corner case
        if (data == "") return null;
        String[] input = data.split(SPLITER);
        TreeNode root = new TreeNode(Integer.valueOf(input[0]));
        //put those nodes to be updated into queue
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int idx = 1;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (!input[idx].equals(NULL)){
                TreeNode left = new TreeNode(Integer.valueOf(input[idx]));
                queue.offer(left);
                cur.left = left;
            }
            idx++;
            if (!input[idx].equals(NULL)) {
                TreeNode right = new TreeNode(Integer.valueOf(input[idx]));
                queue.offer(right);
                cur.right = right;
            }
            idx++;
        }
        return root;
    }
}
