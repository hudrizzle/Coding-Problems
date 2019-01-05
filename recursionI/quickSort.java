public class Solution {
  public int[] quickSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return arr;
    }
    quickSortRec(arr, 0, arr.length - 1);
    return arr;
  }
  private void quickSortRec(int[] arr, int left, int right) {
    //base case: only 1 element
    //corner case: index out of bound(eg. pivot is max/min)
    if (left >= right)  return;
    // get a random pivot, swap with rightmost element
    int pivot = randomPivot(left, right - left + 1);
    swap(arr, pivot, right);
    //pos- the final position of pivot
    int pos = partition(arr, left, right);
    quickSortRec(arr, left, pos - 1);
    quickSortRec(arr, pos + 1, right);
  }
  private int partition(int[] arr, int left, int right) {
    //j left: elements already processed
    //i left: elements  < pivot
    int i = left;
    for (int j = left; j < right; j++) {
      if (arr[j] < arr[right]) {
        swap(arr, j, i);
        i++;
      }
    }
    swap(arr, i, right);
    return i;
  }
  private int randomPivot(int start, int range){
    return start + (int) (Math.random() * range);
  }
  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
