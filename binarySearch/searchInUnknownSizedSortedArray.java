public class Solution {
  public int search(Dictionary dict, int target) {
    //first find the range : left and right
    //then binary search
    if (dict == null) {
      return -1;
    }
    int left = 0, right = 1;
    while (dict.get(right) != null && dict.get(right) < target) {
      left = right;
      right = right * 2;
    }
    return binarySearch(dict, left, right, target);
  }
  //there's chance that dict.get(right) == null
  private int binarySearch(Dictionary dict, int left, int right, int target){
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (dict.get(mid) == null || dict.get(mid) > target) {
        right = mid - 1;
      }else if (dict.get(mid) < target) {
        left = mid + 1;
      }else {
        return mid;
      }
    }
    return -1;
  }
}
