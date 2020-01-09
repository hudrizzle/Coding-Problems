//assume: l,m,n >= 0, l +m+n >0
//hard part: how to traverse all the choices in each level? How to record number of parentheses left?
//array to record {l,l,m,m,n,n}, even index reprents left bracket, odd index represents right bracket
public class Solution {
  private static final char[] PS = new char[]{'(',')','<','>','{','}'};
  public List<String> validParentheses(int l, int m, int n) {
    List<String> result = new ArrayList<>();
    //corner
    if (l <0 || m<0 || n<0) return result;
    int[] remaining = new int[]{l,l,m,m,n,n};
    Deque<Character> stack = new ArrayDeque<>();//record the path of left bracket
    //levels to be recursed/positions to be filled, each node has at most 6 choices
    int levels = 2 * (l + m + n);
    StringBuilder cur = new StringBuilder();//record the path
    dfs(cur, remaining, result, stack, levels);
    return result;
  }
  private void dfs(StringBuilder cur, int[] remaining, List<String> result, Deque<Character> stack, int levels) {
    //base case
    if (cur.length() == levels) {
      result.add(cur.toString());
      return;
    }
    //recursion rule: check all of 6 choices in remaining, with different restrictions based on odd/even index(left/right bracket)
    for (int i = 0; i < remaining.length; i++) {
      if (i % 2 == 1) {//odd index, right parenthesis: > left, match the top of stack, pop the stack
        if (remaining[i] > remaining[i - 1] && !stack.isEmpty() && stack.peekFirst() == PS[i - 1]) {
          cur.append(PS[i]);
          stack.pollFirst();
          remaining[i]--;
          dfs(cur, remaining, result, stack, levels);
          //for backtracking preparation
          remaining[i]++;
          stack.offerFirst(PS[i - 1]);
          cur.deleteCharAt(cur.length() - 1);
        }
      }else{ //even index, left parenthesis: >0, put into stack
        if (remaining[i] > 0) {
          cur.append(PS[i]);
          stack.offerFirst(PS[i]);
          remaining[i]--;
          dfs(cur, remaining, result, stack, levels);
          //backtracking beforehand
          remaining[i]++;
          stack.pollFirst();
          cur.deleteCharAt(cur.length() - 1);
        }
      }
    }
  }
}

