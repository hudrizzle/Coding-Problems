//time- o(N)   space - O(n)
public class Solution {
  //there're empty spaces inside input
  public int calculate(String input) {
    //corner case: invalid input
    if (input == null || input.length() == 0) return 0;
    
    char[] in = input.toCharArray();
    Deque<Integer> stack = new ArrayDeque<>();
    Character operator = null;//used to record last operator
    for (int i = 0; i < in.length; i++) {
      //in[i] is an operator, update operator
      if (!Character.isDigit(in[i]) && in[i] != ' '){
        operator = in[i];
      }else if (Character.isDigit(in[i])){//in[i] is a number, find this number and put into stack
        int num = in[i] - '0';
        while (i + 1 < in.length && Character.isDigit(in[i + 1])){
          num = num * 10 + in[i + 1] - '0';
          i++;
        }
        if (operator == null || operator == '+') stack.offerFirst(num);
        else if (operator == '-') stack.offerFirst(0 - num);
        else if (operator == '*') stack.offerFirst(stack.pollFirst() * num);
        else stack.offerFirst(stack.pollFirst() / num);
      }
    }
    //post processing: add all number in the stack
    int res = 0;
    while (!stack.isEmpty()) {
      res += stack.pollFirst();
    }
    return res;
  }
}
