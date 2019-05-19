//optimize the brute force method using: 1. hashset for check num; 2. only continuous check when num is a starting point of sequence  -> O(n)
class Solution {
    public int longestConsecutive(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) return 0;
        //1st traversing
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        //2nd traversing: only continue when num is a starting point of sequence
        int maxLen = 1;
        for (int i = 0; i < nums.length; i++) {
            int curnum = nums[i];
            int curLen = 1;
            if (set.contains(curnum - 1)) continue;
            while(set.contains(curnum + 1)) {
                curLen++;
                curnum++;
                maxLen = Math.max(maxLen, curLen);
            }           
        }
        return maxLen;
    }
}
