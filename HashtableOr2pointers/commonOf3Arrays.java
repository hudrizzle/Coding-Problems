//assumption: not null, duplicates, sorted -> 2 pointers
//time: O(m + n + q)    space: O(1)

public class Solution {
  public List<Integer> common(int[] a, int[] b, int[] c) {
    List<Integer> commonEle = new ArrayList<>();
    int i = 0, j = 0, k = 0;
    while (i < a.length && j < b.length && k < c.length) {
      if (a[i] == b[j] && b[j] == c[k]) {
        commonEle.add(a[i]);
        i++;
        j++;
        k++;
      }else {
        int max = Math.max(a[i], Math.max(b[j], c[k]));
        i += (max == a[i] ? 0:1);
        j += (max == b[j] ? 0:1);
        k += (max == c[k] ? 0:1);
      }
    }
    return commonEle;
  }
}
