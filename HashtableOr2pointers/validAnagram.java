public class Solution {
  public boolean isAnagram(String source, String target) {
    //corner
    if (source == null || target == null || source.length() != target.length()) return false;
    
    int[] freq = new int[256];
    int n = source.length();
    for (int i = 0; i < n; i++) {
      freq[source.charAt(i)]++;
      freq[target.charAt(i)]--;
    }
    for(int it : freq){
      if (it != 0) return false;
    }
    return true;
  }
}
