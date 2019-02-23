//2 pointers to the same direction

public class Solution {
  public int[] dedup(int[] array) {
    if (array == null || array.length == 0) return array;
    int slow = 1, fast = 1;
    while (fast < array.length) {
      if (array[fast] != array[fast - 1]){
        array[slow++] = array[fast++];
      }else{
        fast++;
      }
    }
    //post processing
    int[] ans = new int[slow];
    for (int i = 0; i < slow; i++) {
      ans[i] = array[i];
    }
    return ans;
  }
}
