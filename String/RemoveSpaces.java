/**
if fast points to '_'：
  if slow == 0, skip;
  else{
    if slow - 1 points to '_', skip;
    else copy that '_';
  }

post processing
**/
public class Solution {
  public String removeSpaces(String input) {
    if (input == null || input.length() == 0) return input;
    char[] array = input.toCharArray();
    int slow = 0;
    int fast = 0;
    while (fast < input.length()) {
      if (array[fast] != ' '){//copy
        array[slow++] = array[fast++];
      }else {//multiple cases to decide
        if (slow == 0 || array[slow - 1] == ' ') fast++;
        else array[slow++] = array[fast++];
      }
    }
    //post processing
    if (slow > 0 && array[slow - 1] == ' ') slow--;
    return new String(array, 0, slow);
  }
}


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


