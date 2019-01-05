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
    int pos = partition(arr, left, right);
    quickSortRec(arr, left, pos - 1);
    quickSortRec(arr, pos + 1, right);
  }
  private int partition(int[] arr, int left, int right) {
    // get a random pivot, swap with rightmost element
    int pivotpos = randomPivot(left, right - left + 1);
    int pivot = arr[pivotpos];
    swap(arr, pivotpos, right);
    //pos- the final position of pivot
    //j left: elements already processed
    //i left: elements  < pivot
    int i = left, j = right - 1;
    while (i <= j) {
      if (arr[i] < pivot) i++;
      else if (arr[j] >= pivot) j--;
      else swap(arr, i, j);
    }
    swap(arr, right, i);
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
