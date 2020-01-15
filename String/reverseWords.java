public class Solution {
  public String reverse(String input) {
    if (input == null || input.length() < 2) return input;
    int i = 0;
    int j = input.length() - 1;
    char[] array = input.toCharArray();
    while (i < j) {
      char temp = array[i];
      array[i] = array[j];
      array[j] = temp;
      i++;
      j--;
    }
    return new String(array);
  }
}

//84. reverse words in a sentence： use the above method
//assume no white spaces
public class Solution {
  public String reverseWords(String input) {
    char[] in = input.toCharArray();
    reverse(in, 0, in.length - 1);
    //reverse each word in the sentence
    int start = 0;
    for (int i = 0; i < in.length; i++) {
      //find the start of the word: first word or words in between
      if (in[i] != ' ' && (i == 0 || in[i - 1] == ' ')) {
        start = i;
      }
      //find the end index of the word: last word or words in between
      if (in[i] != ' ' && (i == in.length - 1 || in[i + 1] == ' ')) {
        reverse(in, start, i);
      }
    }
    return new String(in);
  }
  public void reverse(char[] array, int left, int right) {
    if (array == null || array.length < 2) return;
    while(left < right) {
      char temp = array[left];
      array[left] = array[right];
      array[right] = temp;
      left++;
      right--;
    }
  }
}