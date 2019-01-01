public class Solution {
  public int[] search(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return new int[]{-1, -1};
    }
  //consider matrix as an flattened array
    int row = matrix.length, col = matrix[0].length;
    int left = 0, right = row * col - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int r = mid / col, c = mid % col;
      if (matrix[r][c] == target) {
        return new int[]{r, c};
      }else if (matrix[r][c] < target) {
        left = mid + 1;
      }else {
        right = mid - 1;
      }
    }
    return new int[]{-1, -1};
  }
}
