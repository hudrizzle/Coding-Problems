/**Time: O(n), n-length of long string
 * window moves 1 step forward:
 * 1. add new element, update matched if necessary
 * 2. remove leftmost element if it's already out of bound. update matched if necessary
 * 3. check matched == 0 => put into result list
 */
**/
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


/** my solution -> 不够简洁： don't need to update matched when cnt becomes negative!
window moves, each time:
        check new element from right, if its in map: update count, update matched if necessary.
        check matched == target.length() ? if so, put the leftmost index into result.
        when i - k + 1 >= 0: check the leftmost element, move it out: if its in map: update count, update mached.
**/
public List<Integer> allAnagrams(String target, String source) {
  final List<Integer> anagrams = new ArrayList<>();
  int matched = 0;
  final Map<Character, Integer> frequency = new HashMap<>();
  for (int i = 0; i < target.length(); i++) {
    final Integer cnt = frequency.get(target.charAt(i));
    if (cnt == null) {
      frequency.put(target.charAt(i), 1);
    }
    else {
      frequency.put(target.charAt(i), cnt + 1);
    }
  }
  // sliding window to look at source
  for (int i = 0; i < source.length(); i++) {
    final int leftIndex = i - target.length() + 1;
    if (frequency.containsKey(source.charAt(i))) {
      final Integer cnt = frequency.get(source.charAt(i));
      frequency.put(source.charAt(i), cnt - 1);
      if (cnt == 1) {
        matched++;
      }
      if (cnt == 0) {//don't  need to
        matched--;
      }
      if (matched == frequency.size()) {
        anagrams.add(leftIndex);
      }
    }
    // remove the leftmost: may need to update matched!
    if (leftIndex >= 0 && frequency.containsKey(source.charAt(leftIndex))) {
      final Integer cnt = frequency.get(source.charAt(leftIndex));
      if (cnt == 0) {
        matched--;
      }
      if (cnt == -1) {
        matched++;
      }
      frequency.put(source.charAt(leftIndex), cnt + 1);
    }
  }
  return anagrams;
}