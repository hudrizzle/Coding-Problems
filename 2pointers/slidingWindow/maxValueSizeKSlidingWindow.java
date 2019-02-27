/*3 MEHOTDS
method 1. Brute force -> 2 for loops or 1 for loop with pq
method2. 1 for loop using balanced BST (treeset  treemap)
method3. Monotonically decreasing dequeuer
*/

//use maxHeap to put k elements into it
//since remove(Object) costs O(k), time didn't improve, still O(nk)
public class Solution {
  public List<Integer> maxWindows(int[] array, int k) {
    List<Integer> res = new ArrayList<>();
    if (array == null || array.length < k) return res;
    int n = array.length;
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, new Comparator<Integer>(){
      @Override
      public int compare(Integer i, Integer j){
        if (i == j) return 0;
        return i > j ? -1 : 1;
      }
    });
    //traverse array, will have n-k+1 sliding windows
    for (int i = 0; i < array.length; i++) {
      maxHeap.offer(array[i]);
      //when to start update res -> 1st sliding window 
      if (i - k + 1 >= 0) {
        res.add(maxHeap.peek());
        //move left side object out of sliding window
        int left = array[i - k + 1];
        maxHeap.remove(left);
      }
    }
    return res;
  }
}


//use BBST to improve since:
//remove(Object) costs O(logk)->O(nlogk)
//if use treeset need to put newly-built type to wrap index and number into it.
public class Solution {
  public List<Integer> maxWindows(int[] array, int k) {
    List<Integer> res = new ArrayList<>();
    if (array == null || array.length < k) return res;
    int n = array.length;
    //build treemap with num as key and its occurance indices as value(wrapped in a set)
    TreeMap<Integer, Set<Integer>> occurance = new TreeMap<>();
    //traverse array, will have n-k+1 sliding windows
    for (int i = 0; i < array.length; i++) {
      //adding right side element into
      if(occurance.get(array[i]) == null){
        Set<Integer> indexSet = new HashSet<Integer>();
        indexSet.add(i);
        occurance.put(array[i], indexSet);
      }else{
        occurance.get(array[i]).add(i);
      }
      //when to start update res -> 1st sliding window 
      if (i - k + 1 >= 0) {
        res.add(occurance.lastKey());
        //move left side object out of sliding window
        Set<Integer> toremove = occurance.get(array[i - k + 1]);
        if (toremove.size() == 1){
          occurance.remove(array[i - k + 1]);
        }else {
          occurance.get(array[i - k + 1]).remove(i - k + 1);
        }
      }
    }
    return res;
  }
}


//use monotonically decreasing dequeue(put index into it, head: old elements, tail: new elements):
//remove() from head, add to tail, peek from head -> all o(1) AMORTIZED
//TOTAL TIME: O(n)
public class Solution {
  public List<Integer> maxWindows(int[] array, int k) {
    List<Integer> res = new ArrayList<>();
    if (array == null || array.length < k) return res;
    int n = array.length;
    //build deque to save indeces of traversing element
    Deque<Integer> monoDecrease = new LinkedList<>();
    //traverse array, will have n-k+1 sliding windows
    for (int i = 0; i < array.length; i++) {
      //check last element(minimum), discard all those with value < cur array[i]
      while(!monoDecrease.isEmpty() && array[monoDecrease.peekLast()] <= array[i]) {
        monoDecrease.pollLast();
      }
      //check if there's old element out of sliding window to be removed
      if (!monoDecrease.isEmpty() && monoDecrease.peekFirst() < i - k + 1){
        monoDecrease.pollFirst();
      }
      //adding right side element index into deque
      monoDecrease.add(i);
      //when to start update res -> 1st sliding window 
      if (i - k + 1 >= 0) {
        res.add(array[monoDecrease.peekFirst()]);
      }
    }
    return res;
  }
}
