//k levels: each level consider how to place a pair of numbers, starting from largest number to decrease children
//k should not <= 0
//if cannot find permutations, return null
//there might be multiple valid permutations, only return 1 result.
public class Solution {
  public int[] keepDistance(int k) {
    if (k <= 0) return null;
    int[] result = new int[2 * k];
    if (dfs(result, k)) return result;
    return null;
  }
  private boolean dfs(int[] result, int k) {
    //base case
    if (k == 0) {//all situations goes here will all be valid
      return true;
    }
    //recursion rule
    for (int i = 0; i + k + 1 < result.length; i++) {
      if (result[i] == 0 && result[i + k + 1] == 0) {
        result[i] = k;
        result[i + k + 1] = k;
        if (dfs(result, k - 1)) return true;
        //deeper dfs won't work, so just go to another option in the same level
        result[i] = 0;
        result[i + k + 1] = 0;
      }
    }
    //if all positions won't work for k- impossible
    return false;
  }
}
