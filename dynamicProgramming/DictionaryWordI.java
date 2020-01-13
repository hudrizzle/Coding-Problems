//assume: given words, and all words in dict are not null not empty
//clarify: must we break the input? or can have situations like "abc" just in dict?
public class Solution {
  public boolean canBreak(String input, String[] dict) {
    //put given dict into set for convenient look up
    Set<String> lookup = new HashSet<>();
    for (String str : dict) {
      lookup.add(str);
    }
    //this array represents if the word with length i can break or not, so last char index is i - 1
    int len = input.length();
    boolean[] canBreak = new boolean[len + 1];
    //base case:
    canBreak[0] = true;//nothing there in this substring
    //j from 0 to i - 1
    for (int i = 1; i < canBreak.length; i++) {
      for (int j = 0; j <= i - 1; j++) {
        if (canBreak[j] && lookup.contains(input.substring(j, i))) {
            canBreak[i] = true;
            break;
        }
      }
    }
    return canBreak[canBreak.length - 1];
  }
}
