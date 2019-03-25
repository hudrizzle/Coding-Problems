//thought: tree must satisfy: 1. no cycle, 2. all components connected
//therefore, can use bfs/dfs to check if can traverse the graph with all nodes && check if edge == vertices - 1
//additional: use dfs visited and previous to check cycle (memorization, dedup)

//method 1:
//a tree must satisfy: 1. no cycle 2. all nodes connected
//for cycle detection: tree-> edges = nodes - 1; for connection: bfs + set
public class Solution {
  public boolean validTree(int n, int[][] edges) {
    //check if satisfy tree property: tree-> edges = nodes - 1
    if (edges.length != n - 1) return false;
    //build graph according to edges
    Map<Integer, List<Integer>> graph = buildGraph(edges);
    Set<Integer> visited = new HashSet<>();
    //then check if its all connected
    Deque<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    visited.add(0);
    while (!queue.isEmpty()) {
      Integer cur = queue.poll();
      for (Integer child : graph.get(cur)) {
        if (!visited.contains(child)){
          queue.offer(child);
          visited.add(child);
        }
      }
    }
    return visited.size() == n;
  }
  private Map<Integer, List<Integer>> buildGraph(int[][] edges) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < 5; i++) graph.put(i, new ArrayList<Integer>());
    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
    return graph;
  }
}


//method2: also bfs, use a int array "visited" show status of nodes:
//visited == 1: is generated, to be expanded; visited == 0: not found; visited == 2: finish expanding
//if when expanding, found visited == 1, then return false;
//finally check visited array, if found 0 -> isolated block ->false
public class Solution {
  public boolean validTree(int n, int[][] edges) {
    //build graph according to edges
    Map<Integer, List<Integer>> graph = buildGraph(edges);
    int[] visited = new int[n];
    //then check if its all connected
    Deque<Integer> queue = new ArrayDeque<>();
    queue.offer(0);
    visited[0] = 1;//is being visited
    while (!queue.isEmpty()) {
      Integer cur = queue.poll();
      for (Integer child : graph.get(cur)) {
        if (visited[child] == 1) {return false;}
        if (visited[child] == 0) {
          queue.offer(child);
          visited[child] = 1;
        }
      }
      visited[cur] = 2;//finish visiting
    }
    for (int i : visited) {
      if (i == 0) return false;
    }
    return true;
  }
}

//method3:dfs use curpath check cycle, use visited set to check if all connected
//only those who doesn't have cycles(pass the containsCycle check) in its subgraph can be added to visited set
public class Solution {
  public boolean validTree(int n, int[][] edges) {
    //build graph according to edges
    Map<Integer, List<Integer>> graph = buildGraph(edges);
    Set<Integer> visited = new HashSet<>();
    Set<Integer> curPath = new HashSet<>();
    //starting from only 1 node because they're supposed to be all connected
    //int prev used to label the node which generates start node, -1 means null
    if (containsCycle(-1, 0, graph, visited, curPath)) return false;
    if (visited.size() != n) return false;
    return true;
  }
  private boolean containsCycle(int prev, int start, Map<Integer, List<Integer>> graph,
                               Set<Integer> visited, Set<Integer> curPath) {
    if (visited.contains(start)) return false;
    curPath.add(start);
    for (Integer child : graph.get(start)) {
      if (child == prev) continue;//since undirected graph will go back to previous node
      if (curPath.contains(child)) return true;
      if (containsCycle(start, child, graph, visited, curPath)) return true;
    }
    curPath.remove(start);
    visited.add(start);
    return false;
  }
  private Map<Integer, List<Integer>> buildGraph(int[][] edges) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < 5; i++) graph.put(i, new ArrayList<Integer>());
    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }
    return graph;
  }
}

//method3.2:dfs use only visited to check cycle, also need prev to help
 public boolean validTree(int n, int[][] edges) {
    //build graph according to edges
    Map<Integer, List<Integer>> graph = buildGraph(edges);
    Set<Integer> visited = new HashSet<>();
    //starting from only 1 node because they're supposed to be all connected
    //int prev used to label the node which generates start node, -1 means null
    if (containsCycle(-1, 0, graph, visited)) return false;
    if (visited.size() != n) return false;
    return true;
  }
  private boolean containsCycle(int prev, int start, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
    visited.add(start);
    for (Integer child : graph.get(start)) {
      if (child == prev) continue;//since undirected graph will go back to previous node
      if (visited.contains(child) || containsCycle(start, child, graph, visited)) return true;
    }
    return false;
  }

//method 4. used dfs to traverse the graph and put all traversed nodes into "visited" set, check if set.size() == n && edges == vertices - 1

