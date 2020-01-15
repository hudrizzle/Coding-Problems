//evaluate the given reverse polish notation: array of String containing only numbers or +-*/
public class Solution {
  public int evalRPN(String[] tokens) {
    if (tokens == null || tokens.length < 3) return -1;
    Deque<Integer> stack = new ArrayDeque<>();
    for (String next : tokens) {
      if (Character.isDigit(next.charAt(0))) {//next is a digit
        stack.offerFirst(Integer.valueOf(next));
      }else {//next is an operator
        Integer num1 = stack.pollFirst();
        Integer num2 = stack.pollFirst();
        switch(next) {
          case "+":
            stack.offerFirst(num1 + num2);break;
          case "-":
            stack.offerFirst(num2 - num1);break;
          case "*":
            stack.offerFirst(num1 * num2);break;
          case "/":
            stack.offerFirst(num2 / num1);break;           
        }
      }
    }
    return stack.pollFirst();
  }
}
//The break and default keywords are optional



//clarify: if tokens are not valid- not ending with a operand ? assume all tokens are valid
public class Solution {
  public int evalRPN(String[] tokens) {
    //corner： assume return max integer
    //if (tokens == null) return Integer.MAX_VALUE；

    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < tokens.length; i++) {
      String cur = tokens[i];
      if (Character.isDigit(cur.charAt(0))) {//when meet a number, push into stack
        stack.offerFirst(getNumber(cur));
      }else{//when meet an operand, pop two from stack and calculate, then push back result.
        Integer num1 = stack.pollFirst();
        Integer num2 = stack.pollFirst();
        switch(cur){
          case "+":
            stack.offerFirst(num1 + num2);break;
          case "-":
            stack.offerFirst(num2 - num1);break;
          case "*":
            stack.offerFirst(num1 * num2);break;
          case "/":
            stack.offerFirst(num2 / num1);break;
        }
      }
    }
    return stack.pollFirst();
  }
  private Integer getNumber(String str) {
    Integer number = 0;
    for (int i = 0; i < str.length(); i++) {
      number = number * 10 + Integer.valueOf(str.charAt(i));
    }
    return number;
  }
}
