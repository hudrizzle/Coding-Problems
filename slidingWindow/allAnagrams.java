//Time: O(n), n-length of long string

public class Solution {
  public List<Integer> allAnagrams(String sh, String lo) {
    List<Integer> anagramPos = new ArrayList<>();
    if (sh == null || lo == null || sh.length() == 0 || sh.length() > lo.length()) return anagramPos;
    Map<Character, Integer> freq = getFrequency(sh);//to record frequency of each letter in sh
    int matched = 0;//target: let matched == freq.size() -> found one result
    int end = 0;
    //while traversing, update delta -> add right, remove left
    while (end < lo.length()){
      //adding right side
      Integer cnt = freq.get(lo.charAt(end));
      if (cnt != null) {
        freq.put(lo.charAt(end), cnt - 1);
        if (cnt == 1) matched++;
      }
      //removing left side: end - sh.length() + 1
      if (end - sh.length() + 1 > 0){
        Integer leftCnt = freq.get(lo.charAt(end - sh.length()));
        if (leftCnt != null) {
          if (leftCnt == 0) matched--;
          freq.put(lo.charAt(end - sh.length()), leftCnt + 1);
        }
      }
      //check matched result
      if (matched == freq.size()) anagramPos.add(end - sh.length() + 1);
      end++;
    }
    return anagramPos;
  }
  private Map<Character, Integer> getFrequency(String str) {
    Map<Character, Integer> freq = new HashMap<>();
    for (char ch : str.toCharArray()){
      Integer cnt = freq.get(ch);
      if (cnt == null) freq.put(ch, 1);
      else freq.put(ch, cnt + 1);
    }
    return freq;
  }
}
