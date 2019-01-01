public class Solution {
  public int lastOccur(int[] array, int target) {
    if (array == null || array.length == 0) {
      return -1;
    }
    int left = 0, right = array.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (array[mid] <= target) {
        left = mid;
      }else{
        right = mid;
      }
    }
    
    if (array[right] == target) {
      return right;
    }else if (array[left] == target) {
      return left;
    }else{
      return -1;
    }
  }
}
