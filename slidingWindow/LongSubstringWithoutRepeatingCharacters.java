/**assume input not null, not empty
Note: left is the left index of the window, right always points to the next element to be explored.
so the window size should be right - left instead of right - left + 1
**/
public class Solution {
  public int longest(String input) {
    int max = 0;
    //corner cases
    if (input == null || input.length() == 0) return max;
    int left = 0; 
    int right = 0;
    Set<Character> occur = new HashSet<>();
    while (right < input.length()) {
      if (!occur.add(input.charAt(right))) {//return false, already in there
        occur.remove(input.charAt(left++));
      }else {//return true, first time occur: add into set
        right++;
      }
      max = Math.max(right - left, max);
    }
    return max;
  }
}
