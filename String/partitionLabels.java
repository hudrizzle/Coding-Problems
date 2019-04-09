public class Solution {
    /**
     * @param S: a string
     * @return: a list of integers representing the size of these parts
     * first use map to record the rightmost index of each letter, then traverse again, update the (left, right)
     * for each w in substring: 子串的右边界不能低于最后一个'w'
     **/
    public List<Integer> partitionLabels(String S) {
        List<Integer> res = new ArrayList<>();
        if (S == null || S.length() == 0) {
            res.add(0);
            return res;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] input = S.toCharArray();
        for (int i = 0; i < input.length; i++) map.put(input[i], i);
        
        int left = 0;
        int right = 0;
        for (int i = 0; i < input.length; i++) {
            right = Math.max(right, map.get(input[i]));//guarantee will not out of bound
            if (i == right) {
                res.add(right - left + 1);
                left = i + 1;
            }
        }
        return res;
    }
}
