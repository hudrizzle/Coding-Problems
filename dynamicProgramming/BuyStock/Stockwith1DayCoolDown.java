//assume must have 1 day cool down after selling before buying
//as many transactions as you like
//hold[i] = max(unhold[i - 2] - p[i], hold[i - 1]):max profit at end of day i with stock hold;  
//unhold[i] = max(hold[i - 1] + p[i], unhold[i - 1]):max profit at end of day i without holding stock
//time: O(n)  space: O(n) -> O(1)
public class Solution {
  public int maxProfit(int[] prices) {
    //corner case
    if (prices == null || prices.length < 2) return 0;
    int n = prices.length;
    int[] hold = new int[n];
    int[] unhold = new int[n];
    //basic cases
    hold[0] = -prices[0];
    hold[1] = Math.max(-prices[0], -prices[1]);//buy on day 0 or day 1
    unhold[0] = 0;
    //induction
    for (int i = 1; i < n; i++) {
      unhold[i] = Math.max(hold[i - 1] + prices[i], unhold[i - 1]);
      if (i < 2) continue;
      hold[i] = Math.max(unhold[i - 2] - prices[i], hold[i - 1]);
    }
    return unhold[n - 1];
  }
}

/*brute force method:
use dfs to decide- for each day, choose from 3 options: buy, sell, do nothing
with some restrictions
O(3^n) in time complexity */
