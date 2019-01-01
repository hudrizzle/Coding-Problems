public class Solution {
  public int closest(int[] arr, int target) {
    if (arr == null || arr.length == 0) {
	    return -1;
    }
    int left = 0, right = arr.length - 1;
    //leave 2 elements inside
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] == target) return mid;
      else if (arr[mid] < target) left = mid;
      else right = mid;
    }
    return Math.abs(arr[left] - target) <= Math.abs(arr[right] - target) ? left : right;
  }
}
