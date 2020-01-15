//quick select for unsorted array: each time find a pivot index, check pivot _ target k
//offline. average: O(n) = n + n /2 + n/4+...   worst: O(n ^ 2) terrible
// xxxxxxx
 //   t  m 
 //duplicate?
//other solutions: maxHeap, minheap, sort then take first k
public class Solution {
  public int[] kSmallest(int[] array, int k) {
    if (array == null || array.length == 0 || k <= 0 || k > array.length) return new int[]{};
    quickSelect(array, 0, array.length - 1, k - 1);
    //at this time, [0, 1, ... k - 1] are guaranteed to be smaller than kth.
    int[] result = Arrays.copyOf(array, k);
    Arrays.sort(result);
    return result;
  }
  //recursively find the first k smallest elements in place by partition. [0, k - 1] 
  private void quickSelect(int[] array, int left, int right, int target) {
    int mid = partition(array, left, right);
    if (mid == target) {
      return;
    }else if (mid < target) {
      quickSelect(array, mid + 1, right, target);
    }else {
      quickSelect(array, left, mid - 1, target);
    }
  }
  //find the index pivot s.t. left of pivot are smaller than array[pivot] and right are larger
  private int partition(int[] array, int start, int end) {
    //directly consider righmost index to be randomly picked pivot
    int i = start, j = end - 1;
    while (i <= j) {
      if (array[i] < array[end]) i++;
      else if (array[j] > array[end]) j--;
      else swap(array, i++, j--);
    }
    swap(array, i, end);
    return i;
  }

  private void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }
}
