/*brute force: for each histogram, go for left && right to search for boundaries.
**improved: only put histograms of ascending heights into stack(left boundaries are themselves)
**once "meet a dropdown": keep poping from stack, and calculate max area of each histogram
**right boundary = current index 
**Note: before stack is empty, left boundary of last element should be 0
**time:O(n) space:O(n)
*/
public class Solution {
  public int largest(int[] array) {
    if (array == null || array.length == 0) return 0;
    int maxArea = 0;
    //put indices of histogram into stack
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i <= array.length; i++) {
        int curheight = (i == array.length? 0 : array[i]);
        //when meet a "dropdown", keep poping out elements from stack
        while(!stack.isEmpty() && curheight < array[stack.peekFirst()]) {
            int height = array[stack.pollFirst()];
            int left = (stack.isEmpty()? 0 : stack.peekFirst() + 1);
            maxArea = Math.max(maxArea, (i - left) * height);
        }
        stack.offerFirst(i);
    }
    return maxArea;
  }
}
