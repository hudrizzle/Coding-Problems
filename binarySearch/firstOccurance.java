public class firstOccurance {
	public static void main(String[] args) {
		int[] array = {1, 1,1,1,1,1,1,2, 2, 2, 3};
		int ans = firstOccur(array, 2);
		System.out.println(ans);
	}
	
  	public static int firstOccur(int[] array, int target) {
    	if (array == null || array.length == 0) {
      		return -1;
    	}
    	int left = 0, right = array.length - 1;
    	while (left + 1 < right) {//leave 2 elements
    		int mid = left + (right - left) / 2;
      		if (array[mid] >= target) {//go to left
        		right = mid;
      		}else{
        		left = mid;
      		}	
    	}
    //check if last 2 contains target
    	if (array[left] == target) {
      		return left;
    	}else if (array[right] == target){
      		return right;
    	}else {
      		return -1;
    	}
	}
}