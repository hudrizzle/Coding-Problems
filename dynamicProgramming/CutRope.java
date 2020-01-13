/**O(n^2)
*/
public class Solution {
  public int maxProduct(int length) {
    //corner case
    if (length < 2) return 0;
    int[] M = new int[length + 1];
    int curMax = 0;
    //base case:j = 1 is a special case because M[1] not exist, define it as 0
    M[1] = 0;
    for (int i = 2; i <= length; i++) {
      for (int j = 1; j < i; j++) {
        M[i] = Math.max(M[i], Math.max(j, M[j])*(i - j));
      }
    }
    return M[length];
  }
}
