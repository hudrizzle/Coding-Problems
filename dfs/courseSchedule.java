//dfs method: starting from each node to check if any cycle exist in graph
//time: O(v^2)
//space:O(3v + level) -> level is number of callstack, could be v at most

public class Solution {
  public boolean canFinish(int num, int[][] pre) {
    if (pre == null || num == 0 || pre.length == 0 || pre[0].length == 0) return true;
    //first, get graph.
    Map<Integer, List<Integer>> map = getGraph(num, pre);
    //use 2 sets to check each time if this node is visited or exist in the curPath.
    Set<Integer> visited = new HashSet<>();
    Set<Integer> curPath = new HashSet<>();
    //starting from each node to do bfs since there might be isolated blocks in graph
    for (int i = 0; i < num; i++) {
      if (cycleExist(visited, curPath, map, i)) return false;     
    }
    return true;
  }
  //return true if any cycle exist; visited records all visited nodes; 
  //curPath records the nodes on currently traversing path, cannot encounter duplicates!
  private boolean cycleExist(Set<Integer> visited, Set<Integer> curPath, 
                             Map<Integer, List<Integer>> map, int start) {
    //base check:
    if (visited.contains(start)) return false;//visited node, no need to traverse again
    if (curPath.contains(start)) return true;//curPath comes back to nodes traversed before
    //induction rule: check all neighbors of start nodes, go deeper
    curPath.add(start);
    for (Integer neighbor : map.get(start)){
      if (cycleExist(visited, curPath, map, neighbor)) return true;
      visited.add(neighbor);//add to visited after traversing all its children nodes!
    }
    curPath.remove(start);
    return false;
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
}
