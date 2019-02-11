//recursion ii
//1st kind: straight path, complete 
//find max path cost: dfs(preorder traversal, recording the global max)
//time:O(n), space: O(height) call stack

public int maxPath(TreeNode root){
	//List<TreeNode> path = new ArrayList<>();
	int[] globalMax = new int[1];
	globalMax[0] = Integer.MIN_VALUE;
	helper(0, root, globalMax);
	return globalMax[0];
}

//when to update globalMax? - reached the leaf nodes, check if need to be updated
void helper(int sum, TreeNode root, int[] globalMax){
	//base case
	if (root == null) return;

	//recursive rule in this level
	sum += root.value;
	//leaf nodes
	if (root.left == null && root.right == null) {
		globalMax[0] = Math.max(globalMax[0], sum);
		return;
	}
	helper(sum, root.left, globalMax);
	helper(sum, root.right, globalMax);

}
/*small improvement in the code:
originally parse List<TreeNode> path in helper function, but always need to add them up to get the current sum
therefore, just record the current sum, and update it each time.*/
