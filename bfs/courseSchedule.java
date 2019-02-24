public class Solution {
  public boolean canFinish(int num, int[][] pre) {
    if (pre == null || num == 0 || pre.length == 0 || pre[0].length == 0) return true;
    //first, get in degree and graph.
    Map<Integer, List<Integer>> map = getGraph(num, pre);
    int[] inDegree = getInDegree(num, pre);
    //BFS
    //initialize queue
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < num; i++){
      if (inDegree[i] == 0) q.offer(i);
    }
    while (!q.isEmpty()) {
      Integer finishedCou = q.poll();
      List<Integer> dependents = map.get(finishedCou);
      for (int cur : dependents) {
        if (--inDegree[cur] == 0) q.offer(cur);
      }
    }
    for (int degree : inDegree){
      if (degree != 0) return false;
    }
    return true;
  }
  //build graph: <pre, List<cur>>
  private Map<Integer, List<Integer>> getGraph(int num, int[][] pre) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < num; i++) map.put(i, new ArrayList<Integer>());
    for (int i = 0; i < pre.length; i++) {
      int preCou = pre[i][1];
      int postCou = pre[i][0];
      map.get(preCou).add(postCou);
    }
    return map;
  }
  //calculate indegree in map
  private int[] getInDegree(int num, int[][] pre) {
    int[] inDegree = new int[num];
    for (int i = 0; i < pre.length; i++) {
      inDegree[pre[i][0]]++;
    }
    return inDegree;
  }
}
