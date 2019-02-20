//右小段：starting from index j to index i. j from 0 to i
//M[i] = 0 if [0..i] isPalin or min(all j in [1,i]: M[i - 1] + 1 |if sub[j...i] is palin)
//time: O(n^3)

public class Solution {
  public int minCuts(String input) {
    if (input == null || input.length() == 0) return 0;
    char[] in = input.toCharArray();
    int[] M = new int[in.length];
    //M[0] = 0;
    for (int i = 0; i < in.length; i++) {
      if (isPalin(in, 0, i)) {
        M[i] = 0;
        continue;
      }
      //set initial M[i] value -> maximum of it: i
      M[i] = i;
      //try each right part
      for (int j = 1; j <= i; j++){//sub->[j....i]
        if (isPalin(in, j, i))  M[i] = Math.min(M[j - 1] + 1, M[i]); 
      }
    }
    return M[input.length() - 1];
  }
  private boolean isPalin(char[] in, int l, int r) {
    while (l < r)
      if (in[l++] != in[r--]) return false;
    return true;
  }
}
