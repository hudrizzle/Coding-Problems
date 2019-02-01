//method1.take or not
public class Solution {
  public List<String> subSets(String set) {
    List<String> result = new ArrayList<>();
    if (set == null) {
      return result;
    }
    char[] charSet = set.toCharArray();
    StringBuilder sb = new StringBuilder();
    dfs1(charSet, 0, sb, result);
    return result;
  }
  
  private void dfs1(char[] set, int index, 
                    StringBuilder prefix, 
                    List<String> result){
    //exit of recursion
    if (index == set.length) {
      result.add(prefix.toString());
      return;
    }
    prefix.append(set[index]);
    dfs1(set, index + 1, prefix, result);
    prefix.deleteCharAt(prefix.length() - 1);
    dfs1(set, index + 1, prefix, result);
  }
}
