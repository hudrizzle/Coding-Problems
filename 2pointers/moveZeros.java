//classic 2 pointers to the same direction
//overwrite slow with fast

public class Solution {
  public int[] moveZeroes(int[] nums) {
    int slow = 0, fast = 0;
    while(fast < nums.length) {
      if (nums[fast] != 0){
        nums[slow++] = nums[fast];
      }
      fast++;
    }
    //also remember to post processing the nums
    while (slow < nums.length) {
      nums[slow++] = 0;
    }
    return nums;
  }
}
