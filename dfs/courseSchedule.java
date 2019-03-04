//dfs method: starting from each node to check if any cycle exist in graph
//time: O(v + e) since visited set works as deduplicate, in total checked v + e times
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


//courseScheduleii: find the order of finishing all given courses
        /* DFS post order traversal
        1. Build a hashMap<Integer, Set<Integer>> to represent the course graph
            {course : prerequisites}
        2. Use a hashSet visited to memorize visited graph nodes. 
            Use a list<Integer> result to store finished courses
        3. Post order traverse the graph
        */
//time: O(v + e) space:O(v)
//use dfs to build res path
public class Solution {
  public int[] findOrder(int num, int[][] pre) {
    int[] order = new int[num];
    if (pre == null || num == 0 || pre.length == 0 || pre[0].length == 0) return order;
    //first, get graph, but note: map<cur, List<preCourses>> 
    //since you need to find the prerest course even though you entered from any node
    Map<Integer, List<Integer>> map = getGraph(num, pre);
    //use visited to dedup, also to record the order of final result!
    //use curPath to check cycle
    Set<Integer> visited = new HashSet<>();
    List<Integer> res = new ArrayList<>();
    Set<Integer> curPath = new HashSet<>();
    //traverse each node, check if there's cycle by dfs
    for (int i = 0; i < num; i++) {
      if (containsCycle(i, visited, res, curPath, map)) return new int[0];
    }
    //order = res.toArray(order); cannot use toArray()??
    for (int i = 0; i < num; i++) {
      order[i] = res.get(i);
    }
    return order;
  }
  //true: invalid, contains cycle
  private boolean containsCycle(int start, Set<Integer> visited, List<Integer> res,
                                Set<Integer> curPath, Map<Integer, List<Integer>> map) {
    //basic case
    if (visited.contains(start)) return false;
    if (curPath.contains(start)) return true;
    //recursion rule: add to curPath, check it's neiboughers if contains cycle->no cycle, add to res, return false
    curPath.add(start);
    for (Integer pre : map.get(start)) {
      if (containsCycle(pre, visited, res, curPath, map)) return true;
    }
    visited.add(start);
    res.add(start);
    curPath.remove(start);
    return false;
  }
  //build graph: <cur, List<pre>>
  private Map<Integer, List<Integer>> getGraph(int num, int[][] pre) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < num; i++) map.put(i, new ArrayList<Integer>());
    for (int i = 0; i < pre.length; i++) {
      int preCou = pre[i][1];
      int postCou = pre[i][0];
      map.get(postCou).add(preCou);
    }
    return map;
  }
}
