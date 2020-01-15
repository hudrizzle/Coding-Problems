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

