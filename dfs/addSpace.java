//dfs- similar to subset - Print all permutations of strings after insertions of empty spaces. 
//time: O(2^n), n - length of input. recursion tree: n levels, each has 2 children
//space: O(n) used by stringbuilder, longest sb should be size 2*n - 1

public List<String> permutation(String str) {
  List<String> res = new ArrayList<>();
  //corner case
  if (str == null || str.length() < 1) {
    return res;
  }
  StringBuilder sb = new StringBuilder();
  findPer(0, res, sb, str);
  return res;
}

private void findPer(int level, List<String> res,
                    StringBuilder sb, String str) {
  //base: stop at last level since don't need to add _ behind the last letter
  if (level == str.length() - 1) {
    sb.append(str.charAt(level))
    res.add(sb.toString());
    sb.deleteCharAt(sb.length() - 1);
    return;
  }
  //not add _
  sb.append(str.charAt(level));
  findPer(level + 1, res, sb, str);

  //add _
  sb.append('_');
  findPer(level + 1, res, sb, str);
  sb.deleteCharAt(sb.length() - 1);  
  sb.deleteCharAt(sb.length() - 1); 
}
