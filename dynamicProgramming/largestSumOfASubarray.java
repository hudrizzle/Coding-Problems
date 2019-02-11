//return int[] containing its maxSum, left and right indices of it.
public class Solution {
  public int[] largestSum(int[] array) {
    if (array == null || array.length == 0) {
      return array;
    }
    //M[i]: from 0th to ith the largest sum so far
    int[] M = new int[array.length];
    M[0] = array[0];
    int maxStart = 0, maxEnd = 0, maxSum = array[0];
    int currentStart = 0;//update when you start again
    for (int i = 1; i < array.length; i++) {
      if (M[i - 1] >= 0){
        M[i] = M[i - 1] + array[i];
      }else {
        M[i] = array[i];
        currentStart = i;
      }
      if (M[i] > maxSum) {
        maxSum = M[i];
        maxStart = currentStart;
        maxEnd = i;
      }
    }
    return new int[]{maxSum, maxStart, maxEnd};
  }
}

//only returns the max sum value
//M[i] represents the largest sum from 0th to ith element
//M[i] must include ith element since subarray is contiguous!!!
//improve space to O(1)
public class Solution {
  public int largestSum(int[] array) {
    int M = 0;
    int max = Integer.MIN_VALUE;
    if (array == null || array.length == 0) return max;
    for (int i = 0; i < array.length; i++) {
      if (M < 0) M = array[i];
      else M += array[i];
      max = Math.max(max, M);
    }
    return max;
  }
}
