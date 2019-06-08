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
        if (nums[left] < nums[right]) return nums[left];//sorted 
        else {
            int mid = left + (right - left) / 2;
            int res = Math.min(findMin(nums, left, mid), findMin(nums, mid + 1, right));
            return res;
        }
    }
}

    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    //refer to classic BS, find min value in array, each time rule out half range
    public int findMin(int[] nums) {
        //corner cases: empty array or non-rotated array
        if (nums == null || nums.length == 0) return -1;
        //deal with rotated array
        int l = 0, r = nums.length - 1;
        while (l  + 1 < r) {
            if (nums[l] < nums[r]) return nums[l];
            int mid = l + (r - l) / 2;
            if (nums[mid] < nums[r]) r = mid;
            else if (nums[mid] > nums[l]) l = mid + 1;
        }
        //only 2 elements left to be checked
        return nums[l] < nums[r] ? nums[l] : nums[r];
    }
