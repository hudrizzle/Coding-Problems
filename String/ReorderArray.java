//difficulty is to figure out how to define m, lm, rm based on different situations
//assume array not null. not empty.
//nlogn time, in place so O1 for space
public class Solution {
  public int[] reorder(int[] array) {
    int len = array.length;
    if (len % 2 == 0) convert(array, 0, len - 1);
    else convert(array, 0, len - 2);
    return array;
  }
  //reorder the array in the range of l,r
  private void convert(int[] array, int l, int r) {
    //base case: only 2 elements
    int size = r - l + 1;
    if (size <= 2) return;
    //recursion this level:
    int m = l + size / 2;
    int lm = l + size / 4;
    int rm = l + size * 3 / 4;
    reverse(array, lm, m - 1);
    reverse(array, m, rm - 1);
    reverse(array, lm, rm - 1);
    convert(array, l, l + (lm - l) * 2 - 1);
    convert(array, l + (lm - l) * 2, r);
  }
  private void reverse(int[] array, int left, int right) {
    while(left < right) {
      int temp = array[left];
      array[left] = array[right];
      array[right] = temp;
      left++;
      right--;
    }
  }
}
