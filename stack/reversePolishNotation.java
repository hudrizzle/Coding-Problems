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
