public class Solution {
  public int maxProfit(int[] array) {
    if (array == null || array.length < 2) return 0;
    int maxprofit = 0;
    for (int i = 1; i < array.length; i++) {
      if (array[i] > array[i - 1]) maxprofit += array[i] - array[i - 1];
    }
    return maxprofit;
  }
}
