public class Solution {
  public int searchInsert(int[] input, int target) {
    // input.length == 0 -> return 0
    if (input == null || input.length == 0) {
      return 0;
    }
    //find the first element that < target, then pos shoud be that + 1
    int left = 0, right = input.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (input[mid] >= target) {
        right = mid;
      }else {//can't do mid + 1 because you might miss the element
        left = mid;
      }
    }
    //check situations
    if (input[right] < target) {
      return right + 1;
    }else if (input[left] < target) {//input[left] < target <= input[right] 
      return right;
    }else {//input[left] >= target
      return 0;
    }
  }
}
