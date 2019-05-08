//maintain 2 stacks for opstack,numstack
//maintain level of parenthesis
//only push operators of higher level
//note current number is: in[i] - '0' instead of in[i]----it's char
public class Solution {
  public int calculate(String input) {
    if (input == null || input.length() == 0) return 0;
    char[] in = input.toCharArray();
    Deque<Integer> numstack = new ArrayDeque<>();
    Deque<char[]> opstack = new ArrayDeque<>();
    int level = 0, num = 0;
    for (int i = 0; i < in.length; i++) {
      if (Character.isDigit(in[i])){
        num = num * 10 + in[i] - '0';
        if (i + 1 < in.length && !Character.isDigit(in[i + 1]) || i == in.length - 1){//last is a number
          numstack.offerFirst(num);
          num = 0;
        }
      }else if (!Character.isDigit(in[i]) && in[i] != ' '){
        if (in[i] == '(') level++;
        else if (in[i] == ')') level--;
        else {//in[i] == + or -
          char[] cur = opstack.peekFirst();
          while (cur != null && cur[1] - '0' >= level) {//top of stack has higher priority
            evaluate(numstack, opstack);
          }
          //top of stack has lower priority or empty: cur == null || cur[1] - '0' < level
          opstack.offerFirst(new char[]{in[i], (char) level});
        }
      }
    }
    //post-process when ending with')' or a number
    while (!opstack.isEmpty()) {
      evaluate(numstack, opstack);
    }
    return numstack.peekFirst();
  }
  private void evaluate(Deque<Integer> numstack, Deque<char[]> opstack) {
    char[] curop = opstack.pollFirst();
    Integer num1 = numstack.pollFirst();
    Integer num2 = numstack.pollFirst();
    Integer temp = (curop[0] == '+' ? num1 + num2 : num2 - num1);
    numstack.offerFirst(temp);
  }
}
