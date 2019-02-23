//no need to keep order -> 2 pointers to different direction
public class Solution {
  public int[] moveZero(int[] array) {
    if (array == null || array.length == 0) {
      return array;
    }
    int left = 0, right = array.length - 1;
    while (left < right){
      if (array[left] != 0) left++;
      else if (array[right] == 0) right--;
      else swap(array, left++, right--);
    }
    return array;
  }
  private void swap(int[] array, int index1, int index2){
    int temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
  }
}




//method2 movezeo---->
  public int[] moveZero(int[] array) {
    if (array == null || array.length == 0) {
      return array;
    }
    //elements on left side of left are nonzeros
    //and elements on right side of the right are zeros
    int left = 0, right = array.length - 1;
    while (left < right) {
      if (array[left] == 0) {
        swap(array, left, right--);
      }else {
        left++;
      }
    }
    return array;
  }
