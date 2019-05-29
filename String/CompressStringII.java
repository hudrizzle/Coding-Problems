//time:O(n) space: O(n). StringBuilder helps concat chars while traversing

public class Solution {
  public String compress(String input) {
    //corner case
    if (input == null || input.length() == 0) return input;
    StringBuilder sb = new StringBuilder();
    int count = 1;
    char last = input.charAt(0);
    for (int i = 1; i < input.length(); i++) {
        //encounter a different char: update count,last char, populate sb
        if (input.charAt(i) != last) {
            sb.append(last);
            sb.append(count);
            count = 1;
            last = input.charAt(i);
        }else {//same value as previous
            count++;
        }
    }
    //post processing: caution last char populate
    sb.append(last);
    sb.append(count);
    return sb.toString();
  }
}
