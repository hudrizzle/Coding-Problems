public class Solution {
  public int maxProfit(int[] array) {
    int buyidx = 0;
    int maxprofit = 0;
    for (int sellidx = 1; sellidx < array.length; sellidx++) {
      if (array[sellidx] > array[buyidx]) maxprofit = Math.max(maxprofit, array[sellidx] - array[buyidx]);
      if (array[sellidx] < array[buyidx]) buyidx = sellidx;
    }
    return maxprofit;
  }
}
