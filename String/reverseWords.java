//truncate all heading/trailing/duplicate space 
//time: O(n)
public class Solution {
  public String reverseWords(String input) {
    if (input == null || input.length() < 2) return input;
    String[] in = input.split(" ");
    String result = "";
    for (int i = in.length - 1; i >= 0; i--) { 
      if (in[i].trim().length() == 0) continue;
      result += in[i] + " ";
    } 
    return result.trim();
  }
}
