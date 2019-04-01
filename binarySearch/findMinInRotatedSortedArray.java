//3 methods: 1. sort and return 1st; 2. traverse
//3.property of rotated sorted array: half is sorted and the other half is not
//divide and conquer, find recursively. T:O(logn)
public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        //corner case
        if (nums == null || nums.length == 0) {
            return -1; 
        }
        return findMin(nums, 0, nums.length - 1);
    }
    public int findMin(int[] nums, int left, int right) {
        int size = right - left + 1;
        //base case
        if (size == 1) return nums[left];
        if (size == 2) return Math.min(nums[left], nums[right]);
        
        //recursion: divide and conquer
        if (nums[left] <= nums[right]) return nums[left];//sorted 
        else {
            int mid = left + (right - left) / 2;
            int res = Math.min(findMin(nums, left, mid), findMin(nums, mid + 1, right));
            return res;
        }
    }
}
