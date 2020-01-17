
//non-fixed sized sliding window
//time:O(S) since slow and fast at most go through the S string once respectively
//space:O(T) due to cached T

public class Solution {
  public String minWindow(String source, String target) {
    if(source == null || target == null || source.length() == 0 || target.length() == 0) return "";
    Map<Character, Integer> freq = getFreq(target);
    int slow = 0, matched = 0;
    int minLen = Integer.MAX_VALUE, minstart = 0;//should be updated simutaneously 
    //fast points to last index of current window, slow points to start index of window
    for (int fast = 0; fast < source.length(); fast++) {
      char cur = source.charAt(fast);
      Integer cnt = freq.get(cur);
      if (cnt == null){//not an targeted character, skip
        continue;
      }
      //cnt != null: put into map; check if cnt goes from 1 to 0, matched++
      freq.put(cur, cnt - 1);
      if (cnt == 1){//cnt: 1->0
        matched++;
      }
      
      //check if need to move slow pointer: matched == freq.size()
      //also update global minLen, minstart
      while (matched == freq.size()) {
        if (fast - slow + 1 < minLen){
          minLen = fast - slow + 1;
          minstart = slow;
        }
        char remove = source.charAt(slow++);
        Integer occur = freq.get(remove);
        if (occur == null) {
          continue;
        }
        freq.put(remove, occur + 1);
        if (occur == 0){
          matched--;
        }
      }
    }
    //corner case
    return minLen == Integer.MAX_VALUE ? "" : source.substring(minstart, minstart + minLen);
  }
  private Map<Character, Integer> getFreq(String target) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char ch : target.toCharArray()) {
      Integer cnt = freq.get(ch);
      if (cnt == null) {
        freq.put(ch, 1);
      }else {
        freq.put(ch, cnt + 1);
      }
    }
    return freq;
  }
}

/**
 * 1.16 solution: NOTE THE MOVING THE `fast` and  `slow`
 */

public class Solution {
  public String minWindow(String source, String target) {
    if (source == null || target == null || source.length() == 0 || target.length() == 0) return "";
    Map<Character, Integer> freq = getFreq(target);
    int fast = 0;
    int slow = 0;
    int matched = 0;
    int minLen = Integer.MAX_VALUE, start = 0;
    while (fast < source.length()) {
      Integer cnt = freq.get(source.charAt(fast));
      if (cnt != null) {//found the target character
        freq.put(source.charAt(fast), cnt - 1);
        if (cnt == 1) matched++;
      }
      //check left: need to move slow++, until not satisfying this condition
      while (matched == freq.size()) {
        //update the result if needed
        if (minLen > fast - slow + 1) {
          minLen = fast - slow + 1;
          start = slow;
        }
        Integer con = freq.get(source.charAt(slow));
        if (con != null) {//slow is pointing to a target character
          freq.put(source.charAt(slow), con + 1);
          if (con == 0) matched--;
        }
        slow++;//need to advance slow anyway
      }
      fast++;
    }
    return minLen == Integer.MAX_VALUE ? "" : source.substring(start, start + minLen);
  }

  private Map<Character, Integer> getFreq(String target) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char ch : target.toCharArray()) {
      Integer cnt = freq.get(ch);
      if (cnt == null) {
        freq.put(ch, 1);
      }else {
        freq.put(ch, cnt + 1);
      }
    }
    return freq;
  }
}


