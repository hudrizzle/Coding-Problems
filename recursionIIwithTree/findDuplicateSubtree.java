//from bottom to top: ask left, right their serialization
//this level: combine to get: root + left + right -> preorder serialization, put into map<String, count>
//return the combined serialization to parent
/*
Time complexity:  O(N^2). 
For every subtree, string operation O(n), look in Map O(n)
Space complexity: O(N^2) 
hashMap size n x string.length()
*/
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> duplicate = new ArrayList<>();
        Map<String, Integer> occur = new HashMap<>();
        findDuplicateRec(root, duplicate, occur);
        return duplicate;
    }
    private String findDuplicateRec(TreeNode root, List<TreeNode> duplicate,                                                Map<String, Integer> occur) {
        //base case
        if (root == null) return "#";
        
        String left = findDuplicateRec(root.left, duplicate, occur);
        String right = findDuplicateRec(root.right, duplicate, occur);
        //this level
        String preorder = String.valueOf(root.val) + "," + left + "," + right;
        Integer count = occur.get(preorder);
        if (count == null) {
            occur.put(preorder, 1);
        }else {
            occur.put(preorder, count + 1);
            if (count == 1 ) {
                duplicate.add(root);
            }
        }
        return preorder;
    }
}
