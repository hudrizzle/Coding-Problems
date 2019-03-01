//2 pointers with non-fixed size
public class Solution {
  public String longest(String input, int k) {
    if (input == null || input.length() == 0) return input;
    if (k == 0) return "";//|| input.length() < k
    //slow:1st letter of substring    fast: 1st letter to be checked
    int slow = 0, fast = 0;
    int[] maxPos = new int[]{0,0};
    Map<Character, Integer> freq = new HashMap<>();
    while (fast < input.length()) {
      Integer cnt = freq.get(input.charAt(fast));
      //divide to: 1. move right, add to map  2. move left(when cnt == null && matched == k)
      if (cnt == null && freq.size() == k){//encouter a new char and already matched k letters
        char left = input.charAt(slow);
        if (freq.get(left) == 1) freq.remove(left);
        else freq.put(left, freq.get(left) - 1);
        slow++;
      }else {//move right, add to map
        if (cnt == null) freq.put(input.charAt(fast), 1);
        else freq.put(input.charAt(fast), cnt + 1);
        fast++;
      }
      //check if need to update maxPos
      if (freq.size() == k && fast - 1 - slow > maxPos[1] - maxPos[0]){
        maxPos[0] = slow;
        maxPos[1] = fast - 1;
      }
    }
    return input.substring(maxPos[0], maxPos[1] + 1);
  }
}
