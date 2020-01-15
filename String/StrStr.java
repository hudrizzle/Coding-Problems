// xxxxx
// yy yy
//last index to be checked is: large.length() - small.length() 
//m-large length, n-small length
//total time: O(mn) worst case
public class Solution {
  public int strstr(String large, String small) {
    //corner cases
    if (small == null || large == null || small.length() > large.length()) return -1;
    if (small.isEmpty()) return 0;
    //O(m)
    for (int i = 0; i <= large.length() - small.length(); i++) {
      if (compare(large, small, i)) return i;
    }
    return -1;
  }
  //O(n)
  private boolean compare(String large, String small, int offset) {
    for (int i = 0; i < small.length(); i++) {
      if (small.charAt(i) != large.charAt(i + offset)) return false;
    }
    return true;
  }
}
