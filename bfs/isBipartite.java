//T: O(V+E),S:O(V)
class Solution {
      public boolean isBipartite(int[][] graph) {
        //record the visited nodes, Integer: 0 || 1
        Map<Integer, Integer> colored = new HashMap<>();
        //starting from each node from list because there might be isolated nodes
        for (int cur = 0; cur < graph.length; cur++) {
          if (!colored.containsKey(cur)) {
            colored.put(cur, 0);
            if (!isBipartite(cur, colored, graph)) return false;
          }
        }
        return true;
      }
      public boolean isBipartite(int node, Map<Integer, Integer> colored, 
                                 int[][] graph) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
          Integer cur = queue.poll();
          //current group of cur node
          Integer group = colored.get(cur);
          for (int neighbor : graph[cur]) { 
            if (!colored.containsKey(neighbor)) {
            //those who are not visited: give them color, put into queue to be expanded
              colored.put(neighbor, 1 - group);
              queue.offer(neighbor);
            }else {// those are visited and have color, check the color
              if(colored.get(neighbor) == group) return false;       
            }
          }      
        }
        return true;
      }
}
