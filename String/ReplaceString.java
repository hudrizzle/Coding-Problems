/**2 pointers with 2 situations:
1. source longer than target -> from left to right
2. source shorter than target-> extend the size of original, then from right to left

student  den -> xx
   xx

studentdent den -> xxxx
   xxxxtxxxx

assume given strings are not null, not empty
**/
public class Solution {
  public String replace(String input, String source, String target) {
    char[] array = input.toCharArray();
    char[] s = source.toCharArray();
    char[] t = target.toCharArray();
    if (source.length() >= target.length()) return replaceFromLeft(array, s, t);
    else return replaceFromRight(array, s, t);
  }
//2 pointers in place 
  private String replaceFromLeft(char[] array, char[] s, char[] t) {
    int fast = 0;
    int slow = 0;
    while (fast < array.length) {
      if (fast <= array.length - s.length && isSubstring(array, s, fast)){//found the substring, and replace it
        copyString(array, t, slow);
        slow += t.length;
        fast += s.length;
      }else {//copy the rest of characters 
        array[slow++] = array[fast++];
      }
    }
    return new String(array, 0, slow);
  }
  //2 pointers 
  private String replaceFromRight(char[] array, char[] s, char[] t) {
    //get all matches end positions
    ArrayList<Integer> matches = getMatches(array, s);
    char[] result = new char[array.length + (t.length - s.length) * matches.size()];
    int fast = array.length - 1;
    int slow = result.length - 1;
    int lastIndex = matches.size() - 1;
    while (fast >= 0) {
      if (lastIndex >= 0 && fast == matches.get(lastIndex)) {//found a match and copy
        copyString(result, t, slow - t.length + 1);
        slow -= t.length;
        fast -= s.length;
        lastIndex--;
      }else{
        result[slow--] = array[fast--];
      }
    }
    return new String(result);
  }
  //return if souce is the substring of array starting from index
  private boolean isSubstring(char[] array, char[] source, int index) {
    for (int i = index; i < source.length + index; i++) {
      if (array[i] != source[i - index]) return false;
    }
    return true;
  }
  //copy the string t to array starting from index slow
  private void copyString(char[] array, char[] t, int index) {
    int i = 0;
    while (i < t.length) {
      array[index++] = t[i++];
    }
  }
  //from right to left, find all last indexes of matched substrings.
  private ArrayList<Integer> getMatches(char[] array, char[] s) {
    ArrayList<Integer> matches = new ArrayList<>();
    int i = 0;
    while (i <= array.length - s.length) {
      if (isSubstring(array, s, i)) {
        matches.add(i + s.length - 1);
        i += s.length;
      }else {
        i++;
      }
    }
    return matches;
  }
}
