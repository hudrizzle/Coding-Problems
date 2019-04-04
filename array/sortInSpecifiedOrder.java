//1st method: brute force, like selection sort
//time: A1.length = m; A2.length = n  -> O(nm + mlogm)
public class Solution {
  public int[] sortSpecial(int[] A1, int[] A2) {
    //corner
    if (A1 == null || A1.length == 0) return A1;
    //left of next is sorted, right side to be checked
    int next = 0;
    for (int i = 0; i < A2.length; i++) {
      for (int j = next; j < A1.length; j++) {
        if (A1[j] == A2[i]) swap(A1, j, next++);
      }
    }
    Arrays.sort(A1, next, A1.length);
    return A1;
  }
  private void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
}
//2nd method: copy A1 to temp. for each in A2:binary search in temp to copy the targets to A1, then copy those not visited to A1
//time: O(mlogm + nlogm)
public class Solution {
  public int[] sortSpecial(int[] A1, int[] A2) {
    //corner
    if (A1 == null || A1.length == 0) return A1;
    //new a temp array and copy A1 to temp, sort temp:O(m + mlogm)
    int[] temp = Arrays.copyOf(A1, A1.length);
    Arrays.sort(temp);
    //binary search each element in A2, replace A1
    boolean[] visited = new boolean[temp.length];//O(m)
    int index = 0;
    for (int i = 0; i < A2.length; i++) {//O(nlogm + m) at most copy m times
      int pos = binarysearch(A2[i], temp);//find the 1st A2[i] in temp
      while (pos >= 0 && pos < temp.length && temp[pos] == A2[i]) {
        visited[pos] = true;
        A1[index++] = temp[pos++];
      }
    }
    for (int j = 0; j < visited.length; j++) {//o(M)
      if (!visited[j]) A1[index++] = temp[j];
    }
    return A1;
  }
  //helper function aims to find the 1st occurance of target in arr
  private int binarysearch(int target, int[] arr) {
    int l = 0, r = arr.length - 1;
    while(l < r) {
      int mid = l + (r - l) / 2;
      if (arr[mid] >= target) r = mid;//since if arr[mid] == target, mid might be the pos
      else l = mid + 1;
    }
    if (arr[l] == target) return l;
    else if (arr[r] == target) return r;
    else return -1;
  }
}

//3rd method: customize a comparator class based on A2
public class sortInSpecifiedOrder {
    public static void main(String[] args) {
        int[] A1 = new int[]{2, 1, 2, 5, 7, 1, 9, 3};
        int[] A2 = new int[]{2, 1, 3};
        int[] res = sortSpecial(int[] A1, int[] A2);
        System.out.println(res);
    }
    public class MyComparator implements Comparator<Integer> {
        private Map<Integer, Integer> map;//save as<Integer, Index>
        public MyComparator(int[] array) {
            map = new HashMap<>();
            for (int i = 0; i < array.length; i++) {
                map.put(array[i], i);
            }
        }
        @Override
        public int compare (Integer o1, Integer o2) {
            Integer idx1 = map.get(o1);
            Integer idx2 = map.get(o2);
            if (idx1 != null && idx2 != null) {//both are in array
                return idx1.compareTo(idx2);
            }else if (idx1 == null && idx2 == null) {//both are not in array
                return o1.compareTo(o2);
            }else {//only one of them is in array
                return idx1 != null ? -1 : 1;//if o1 is in array, then o1 has higher priority
            }
        }
    }
    private Integer[] intToInteger(int[] array) {
        Integer[] arrayInteger = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayInteger[i] = array[i];
        }
        return arrayInteger;
    }
    public int[] sortSpecial(int[] A1, int[] A2) {
        //corner
        if (A1 == null || A1.length == 0) return A1;
        Integer[] arr = intToInteger(A1);
        Arrays.sort(arr, new MyComparator(A2));
        integerToInt(arr, A1);// O(m)
        return A1;
    }
    private void integerToInt(Integer[] arrayInteger, int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayInteger[i];
        }
    }
}
