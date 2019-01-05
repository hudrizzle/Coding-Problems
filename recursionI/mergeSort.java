//3 parts: main function, recursion function, merge process
//!!process:copy elements from arr to helper, update arr according to helper(last updated result)
public class Solution {
  public int[] mergeSort(int[] arr) {
    int[] helper = new int[arr.length];
    if (arr == null || arr.length == 0) {
      return arr;
    }
    mergeRec(helper, arr, 0, arr.length - 1);
    return arr;
  }
  private void mergeRec(int[] helper, int[] arr, 
                        int start, int end) {
    //base case : only 1 element
    if (start == end) {
      return;
    }
    int mid = start + (end - start) / 2;
    mergeRec(helper, arr, start, mid);
    mergeRec(helper, arr, mid + 1, end);
    merge(helper, arr, start, mid, mid + 1, end);
  }
  private void merge(int[] helper, int[] arr, 
                     int left1, int right1, int left2, int right2) {
    //copy the elements to be processed to helper
    for (int i = left1; i <= right2; i++) {
      helper[i] = arr[i];
    }
    //the reconstructed part - starting index 
    int start = left1;
    //when there's at least one unempty part
    while (left1 <= right1 || left2 <= right2) {
      //take element from part1: 1.part2 empty; 2. part1 not empty&&part1 < part2
      if (left2 > right2 || (left1 <= right1 && helper[left1] <= helper[left2]) ) {
        arr[start++] = helper[left1++];
      }else {
        arr[start++] = helper[left2++];
      }
    }  
  }
}
