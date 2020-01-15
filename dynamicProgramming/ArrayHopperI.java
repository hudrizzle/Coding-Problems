public class Solution {
  public boolean canJump(int[] array) {
    if (array == null || array.length < 2) return true;
    //if can jump to index i
    boolean[] canJump = new boolean[array.length];
    //base case:
    canJump[0] = true;
    for (int i = 1; i < array.length; i++) {
      for (int j = 0; j <= i - 1; j++) {
        if (canJump[j] && array[j] >= i - j) {
          canJump[i] = true;
          break;
        }
      }
    }
    return canJump[canJump.length - 1];
  }
}

