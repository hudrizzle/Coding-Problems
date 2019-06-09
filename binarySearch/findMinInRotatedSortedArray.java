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


public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     * draw plot to help. nums[0] or nums[nums.length - 1] can be virtual target
     * to decide where mid is, and then rule out half
     * time: O(logn), space: O(1)
     */
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        
        // 如果最后一个数>=第一个数说明数组没有翻转，直接返回第一个数
        if (nums[nums.length-1] >= nums[0])
            return nums[0];
            
        // 通过判断属于rotation的左边或者右边来决定 往左或者往右搜索
        int start = 0, end = nums.length-1;
        while (start+1 < end) {
            int mid = start + (end-start)/2;
            //大于等于第一个数说明在左边，往右搜索
            if (nums[mid] >= nums[0])
                start = mid;
            else //小于第一个数说明在右边，往左搜索
                end = mid;
        }
        
        return Math.min(nums[start],nums[end]);
    }
}
