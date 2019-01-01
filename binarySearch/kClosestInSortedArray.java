public class Solution {
  public int[] kClosest(int[] arr, int target, int k) {
    int[] ans = new int[k];
    if (arr == null || arr.length == 0 || k == 0 || arr.length < k) {
      return ans;
    }
    //first find the element index that is the largest <= target
    //let left = this index, right = this index + 1
    //two pointers
    int left = findSmallerEqual(arr, target);
    int right = left + 1;
    //populate the ans array, advance either one of the pointers
    for (int i = 0; i < k; i++) {
      //advance left pointer if: 1.right out of bound(left is the last)
      //or 2.right,left not out of bound and left is closer
      if (right >= arr.length || (left >= 0 && target - arr[left] <= arr[right] - target)) {
        ans[i] = arr[left--];
      }else {
        //advance right pointer if: left out of bound; both in bound and right is closer
        ans[i] = arr[right++];
      }
    }
    return ans;
  }
  private int findSmallerEqual(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    //must jump out of while with only 2 elements inside
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (arr[mid] <= target) {
        left = mid;
      }else {
        right = mid;
      }
    }
    //3 areas to check: 1. target larger than right->return right; 
    //                  2. target>= left && target < right -> return left
    //   3.there's chance that target is smaller than any element, you can't find the one smaller or equal to target
    if (target >= arr[right]) {
      return right;
    }else if (target >= arr[left]){
      return left;
    }else {// sitaution3, can't find any one
      return -1;
    }
  }
}
