//each node has at most n - 1 children, level could be at most n/2 levels.  -> O(n^(n/2)) time complexity
public class Solution {
  public boolean canWin(String input) {
    List<String> next = generateNextMoves(input);
    //base case
    if (next == null) return false;
    //as long as there's only 1 next string that can garantee the other person lose, then I'll win
    for (String ne : next) {
      if (!canWin(ne)) return true;
    }
    return false;
  }
  private List<String> generateNextMoves(String input) {
    List<String> allres = new ArrayList<>();
    if (input == null || input.length() < 2) return allres;
    char[] in = input.toCharArray();
    for (int i = 0; i < in.length - 1; i++) {
      if (in[i] == '+' && in[i + 1] == '+') {
        in[i] = '-';
        in[i + 1] = '-';
        allres.add(new String(in));
        in[i] = '+';
        in[i + 1] = '+';
      }
    }
    return allres;
  }
}

//with momerization, dedup process, improve time. improved code
public class Solution {
  public boolean canWin(String input) {
    Map<String, Boolean> memo = new HashMap<>();
    return canWin(input, memo);
  }
  public boolean canWin(String input, Map<String, Boolean> memo) {
    List<String> next = generateNextMoves(input);
    //base case
    if (next == null) {
      memo.put(input, false);
      return false;
    }
    //as long as there's only 1 next string that can garantee the other person lose, then I'll win
    for (String ne : next) {
      if (memo.get(ne) != null && memo.get(ne) == false || !canWin(ne)) {
        memo.put(ne, true);
        return true;
      }
    }
    memo.put(input, false);
    return false;
  }
  private List<String> generateNextMoves(String input) {
    List<String> allres = new ArrayList<>();
    if (input == null || input.length() < 2) return allres;
    for (int i = 0; i < input.length() - 1; i++) {
      if (input.charAt(i) == '+' && input.charAt(i + 1) == '+') {
        String temp = input.substring(0, i) + "--" + input.substring(i + 2);
        allres.add(temp);
      }
    }
    return allres;
  }
}
