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
