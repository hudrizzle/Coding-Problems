//binary search recursion. each time eliminate Half of K candidates
//assume: 0 <= k <= a.length + b.length, 
//a and b are sorted, not null and at least one of them not empty. can contain dulicates between a and b
public class Solution {
  public int kth(int[] a, int[] b, int k){
    return kth(a, 0, b, 0, k);
  }
  public int kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
    //base case
    if (aLeft >= a.length) return b[bLeft + k - 1];
    if (bLeft >= b.length) return a[aLeft + k - 1];
    if (k == 1) return Math.min(a[aLeft], b[bLeft]);
    //recursion
    int amid = aLeft + k / 2 - 1;
    int bmid = bLeft + k / 2 - 1;
    //check out of bound: only one of them, make that value to be largest integer
    int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
    int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];

    if (aval <= bval) {
      return kth(a, amid + 1, b, bLeft, k - k / 2);
    }else {
      return kth(a, aLeft, b, bmid + 1, k - k / 2);
    }
  }
}
