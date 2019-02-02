//method2- 2pointers
//time: O(mlogm + nlogn), space: O(1),assume length of a and b are m,n
//assumption: 1. unsorted, 2. not null 3.no duplicates
//return in increasing order
public class Solution {
  public List<Integer> common(List<Integer> a, List<Integer> b) {
    List<Integer> commonSet = new ArrayList<>();
    //sort first so can use 2 pointers
    Collections.sort(a);
    Collections.sort(b);
    int i = 0, j = 0;
    while (i < a.size() && j < b.size()) {
      if (a.get(i) == b.get(j)) {
        commonSet.add(a.get(i));
        i++;
        j++;
      }else if(a.get(i) < b.get(j)) {
        i++;
      }else{
        j++;
      }
    }
    //one of them goes beyond the end
    return commonSet;
  }
}

//method1- hashset
//time: O(mlogm + nlogn), space: O(min(m,n)),assume length of a and b are m,n
//assumption: 1. unsorted, 2. not null 3.no duplicates
//return in increasing order
public class Solution {
  public List<Integer> common(List<Integer> a, List<Integer> b) {
    List<Integer> commonSet;
    Set<Integer> set;
    //assign which put into set according to length
    if (a.size() <= b.size()) {
      set = getSet(a);
      commonSet = findCommon(set, b);
    }else {
      set = getSet(b);
      commonSet = findCommon(set, a);
    }
    Collections.sort(commonSet);
    return commonSet;
  }
  
  private Set<Integer> getSet(List<Integer> x) {
    Set<Integer> set = new HashSet<>();
    for (Integer cur : x) {
      set.add(cur);
    }
    return set;
  }
  
  private List<Integer> findCommon(Set<Integer> set, List<Integer> x) {
    List<Integer> common = new ArrayList<>();
    for (Integer cur : x) {
      if (set.contains(cur))  common.add(cur);
    }
    return common;
  }
}
