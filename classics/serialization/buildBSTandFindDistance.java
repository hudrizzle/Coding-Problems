//construct BST from a given array, return the distance between given 2 nodes
//assume no duplicates in array
public class DistanceOfBstNodes {
    public static void main(String[] args) {
        DistanceOfBstNodes test = new DistanceOfBstNodes();
        int dist = test.findDisTwoNode(new int[]{5,6,3,1,2,4}, 2, 6);

        System.out.println(dist);
    }
    class Node {
        int key;
        Node left;
        Node right;
        public Node(int value) {
            this.key = value;
        }
    }

    //build bst given array, iteratively go left/right
    public int findDisTwoNode(int[] nums, int v1, int v2) {
        //corner
        if (nums == null || nums.length < 2) return 0;

        //build the binary search tree
        Node root = new Node(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            Node curNode = new Node(nums[i]);
            Node pos = root;
            while (pos != null) {
                if (pos.key > curNode.key){//go left
                    if (pos.left == null) {
                        pos.left = curNode;
                        break;//or set pos to be null
                    }else{
                        pos = pos.left;
                    }
                    //pos.left == null ? (pos.left = curNode) : (pos = pos.left);
                }else{
                    if (pos.right == null) {
                        pos.right = curNode;
                        break;
                    }else{
                        pos = pos.left;
                    }
                    //pos.right == null ? (pos.right = curNode) : (pos = pos.right);
                }
            }
        }

        //find distance between two nodes: lca might be null caused by one of them not in tree
        Node lca = findLCA(root, v1, v2);
        System.out.println(lca.key);
        int dist1 = findDist(lca, v1);
        int dist2 = findDist(lca, v2);
        return (dist1 >= 0 && dist2 >= 0 ? dist1 + dist2 : -1);//-1: at least one of the value is not in the tree
    }
    //assume both of them are int the tree
    //ask left, right return the Node if found, or return null
    private Node findLCA(Node root, int v1, int v2) {
        //base case: to the leaf, didn't find v1 or v2
        if (root == null) return null;

        if (root.key == v1 || root.key == v2) return root;//assume the other must in the subtree
        Node left = findLCA(root.left, v1, v2);
        Node right = findLCA(root.right, v1, v2);
        if (left != null && right != null) return root;
        return left == null ? right : left;//one of them or both of them are null
    }
    private int findDist(Node root, int v) {
        //corner case: lca is null OR v is not int the tree
        if (root == null) return -1;
        int dist = 0;
        while (root != null && root.key != v) {
            if (root.key < v) {
                root = root.right;
            }else {
                root = root.left;
            }
            dist++;
        }
        return root == null ? -1 : dist;
    }
}
