//assume: no duplicate combinations, factors not include 1 and itself
//levels: number of factors
//each node: check how many of the same facter 
//clarify: output order in each list? 
public class Solution {
  public List<List<Integer>> combinations(int target) {
    List<List<Integer>> result = new ArrayList<>();
    if (target <= 1) return result;
    List<Integer> cur = new ArrayList<>();
    List<Integer> factors = getFactors(target);
    combinationsRec(target, result, cur, factors, 0);
    return result;
  }
  private void combinationsRec(int target, List<List<Integer>> result, List<Integer> cur, List<Integer> factors, int level) {
    //base case
    if (level == factors.size()) {
      if (target == 1) {
        result.add(new ArrayList<Integer>(cur));
      }
      return;
    }
    //recursion rule: how many factors.get(level) can be added to cur
    //include 0, directly go to next level
    combinationsRec(target, result, cur, factors, level + 1);
    //traverse by loop, otherwise need to know the limit- factor^i < target
    int factor = factors.get(level);
    int count = 0;// need to know how many factors added into cur, for removing and backtracking
    while (target % factor == 0) {
      count++;
      cur.add(factor);
      target /= factor;
      combinationsRec(target, result, cur, factors, level + 1);
    }
    //prepare for backtracking
    for (int i = 0; i < count; i++) {
      cur.remove(cur.size() - 1);
    }
  }
  //resulting list starting from largest factor to smallest
  private List<Integer> getFactors(int target) {
    List<Integer> factors = new ArrayList<>();
    for (int i = target / 2; i > 1; i--) {
      if (target % i == 0) {
        factors.add(i);
      }
    }
    return factors;
  }
}
