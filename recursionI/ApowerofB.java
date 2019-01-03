//O(b) time complexity to O(logb) -> memorize a^(b/2)

public class Solution {
  public long power(int a, int b) {
  //assume a and b are non-negative
    //base
    if (b == 0) return 1;
    //recursion rule
    long temp = power(a, b / 2);
    if (b % 2 == 0) {
      return temp * temp;
    }else{
      return temp * temp * a;
    }
  }
}
