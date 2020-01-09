//clarify: may contain duplicates, but result should not contain duplicates
//set might be null or empty
//time: O(2^n)
public class Solution {
  public List<String> subSets(String set) {
    List<String> result = new ArrayList<>();
    //corner case
    if (set == null) return result;
    char[] input = set.toCharArray();
    Arrays.sort(input);
    //since results are not in constant length, use a stringbuilder to append
    StringBuilder prefix = new StringBuilder();
    dfs(input, prefix, 0, result);
    return result;
  }
  private void dfs(char[] input, StringBuilder prefix, int level, List<String> result) {
    //base case
    if (level == input.length) {
      result.add(prefix.toString());
      return;
    }
    //recursion rule: choose input[level] or not
    dfs(input, prefix.append(input[level]), level + 1, result);
    prefix.deleteCharAt(prefix.length() - 1);//for backtracking, must recover to original state
    //dedup when not choosing input[level], can directly going down to the level with char not repeated
    while (level < input.length - 1 && input[level + 1] == input[level]){
      level++;
    }
    dfs(input, prefix, level + 1, result);
  }
}
