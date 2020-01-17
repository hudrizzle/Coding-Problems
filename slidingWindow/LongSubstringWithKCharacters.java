//2 pointers with non-fixed size
public class Solution {
  public String longest(String input, int k) {
    if (k == 0 && input.length() == 0) return "";
    if (input == null || input.length() == 0) return null;
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
    return freq.size() < k ? null : input.substring(maxPos[0], maxPos[1] + 1);
  }
}


//2 pointers with non-fixed size
//a little improvement: use treemap to move slow directly to last index of furthest element
//treemap<index, character>   revered to map<character, index>
//T: same -> O(n)
//fast points to the rightmost index of the window, slow points to the last index before the valid sliding window!
public class Solution {
  public String longest(String input, int k) {
    if (k == 0 && input.length() == 0) return "";
    if (input == null || input.length() == 0) return null;
    
    int fast = 0;
    int slow = -1;//always points to the last index before valid sliding window
    int[] maxPos = new int[]{0,0};
    //map records the updated lastest index of the chatacter
    Map<Character, Integer> lastIndex = new HashMap<>();
    TreeMap<Integer, Character> reverse = new TreeMap<>();
    while (fast < input.length()) {
      char ch = input.charAt(fast);
      Integer lastOccur = lastIndex.get(ch);
      //first, update the reverse: lastOccur!=null, the letter occurred.
      if (lastOccur != null){
        reverse.remove(lastOccur);
      }
      reverse.put(fast, ch);
      lastIndex.put(ch, fast);
      //check if need to move slow pointer
      if (lastIndex.size() > k) {
        //look for the last index of furthest letter, remove it from map and treemap
        Integer removeIdx = reverse.firstKey();
        Character removeChar = reverse.get(removeIdx);
        lastIndex.remove(removeChar);
        reverse.remove(removeIdx);
        slow = removeIdx;
      }
      //check if need to update global max position: length = fast - slow
      if (fast - slow > maxPos[1] - maxPos[0] + 1){
        maxPos[0] = slow + 1;
        maxPos[1] = fast;
      }
      fast++;
    }
    return lastIndex.size() < k ? null : input.substring(maxPos[0], maxPos[1] + 1);
  }
}

/** 1.16 solution
 * size = right - left because right points to the element to be explored
 * bug: check map.size() == k to update max and start location!
 * and consider the corner case: "aaaaaa" and k = 2, then map.size() < 2 always, check when returning.
 * improve: can move left faster in one step: need a TreeMap or sth to record the latest index of the characters in the window
 * can be cleaner
 */
public class Solution {
  public String longest(String str, int k) {
//corner cases:
    if (str == null || str.length() == 0) return str;
    Map<Character, Integer> frequency = new HashMap<>();
    int max = 0;
    int start = 0;
    int left = 0;
    int right = 0;
    while (right < str.length()) {
      if (frequency.size() == k && !frequency.containsKey(str.charAt(right))){
        Integer cnt = frequency.get(str.charAt(left));
        if (cnt == 1) frequency.remove(str.charAt(left));
        else frequency.put(str.charAt(left), cnt - 1);
        left++;
      }else {
        Integer cnt = frequency.get(str.charAt(right));
        if (cnt == null) frequency.put(str.charAt(right), 1);
        else frequency.put(str.charAt(right), cnt + 1);
        right++;
      }
      if (max < right - left) {//bug1 -  if (frequency.size() == k && max < right - left)
        max = right - left;
        start = left;
      }
    }
    return str.substring(start, start + max);//bug2 return frequency.size() < k ? null : str.substring(start, start + max);
  }
}

/**
 *
 * 473. Longest Substring with At Most K Distinct Characters
 **/
public class Solution {
  public int lengthOfLongestSubstringKDistinct(String str, int k) {
    //corner cases:
    if (str == null || str.length() == 0) return 0;
    Map<Character, Integer> frequency = new HashMap<>();
    int[] maxPos = new int[2];
    int left = 0;
    int right = 0;
    while (right < str.length()) {
      if (frequency.size() == k && !frequency.containsKey(str.charAt(right))){
        Integer cnt = frequency.get(str.charAt(left));
        if (cnt == 1) frequency.remove(str.charAt(left));
        else frequency.put(str.charAt(left), cnt - 1);
        left++;
      }else {
        Integer cnt = frequency.get(str.charAt(right));
        if (cnt == null) frequency.put(str.charAt(right), 1);
        else frequency.put(str.charAt(right), cnt + 1);
        right++;
      }
      if (maxPos[1] - maxPos[0] + 1 < right - left) {
        maxPos[0] = left;
        maxPos[1] = right - 1;
      }
    }
    return maxPos[1] - maxPos[0] + 1;
  }
}
