public class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        for (int price: prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, buy1 + price);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, buy2 + price);
        }
        return sell2;
    }
}
/*
卖的时候最开始的利润是0。第一次买的时候，要钱所以是负数。第一次卖的时候的利润就是第一次买的时候付的钱+赚的钱。
第二次买的时候，就是前面赚的钱-第二次买的钱。最后一次卖就是前面剩的钱+利润。
*/
