//assume there're duplicates in input, wanna get non-repeated outputs.
public class Solution {
  public List<String> permutations(String input) {
    List<String> result = new ArrayList<>();
    char[] in = input.toCharArray();
    dfs(result, in, 0);
    return result;
  }
  private void dfs(List<String> result, char[] in, int level) {
    //base case
    if (level == in.length) {
      result.add(new String(in));
      return;
    }
    //recursion rule
    Set<Character> used = new HashSet<>();
    for (int i = level; i < in.length; i++) {
      if (used.add(in[i])) {//returns true meaning in[i] was not in set
        swap(in, i, level);
        dfs(result, in, level + 1);
        swap(in, i ,level);
      }
    }
  }
  private void swap(char[] in, int i, int j) {
    char temp = in[i];
    in[i] = in[j];
    in[j] = temp;
  }
}
