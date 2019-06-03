/*STEPS: 1. Build map to represent graphs
* 2. calculate in-degree
* 3. topological sorting based on changing in-degree
*NOTE: as we should return the topo order with lexicographical order, we should use PriorityQueue
* instead of a FIFO Queue
*/
public class Solution {
  public String alienOrder(String[] words) {
    if (words == null || words.length == 0) return null;
    Map<Character, Set<Character>> graph = getGraph(words);
    return topologicalSorting(graph);
  }
  //get a graph representation with parent node as key, with children nodes as values
  //note: must contain all avaialable characters
  private Map<Character, Set<Character>> getGraph(String[] words) { 
    Map<Character, Set<Character>> graph = new HashMap<>();
    //initialize the map with empty sets
    for (int i = 0; i < words.length; i++) {
        for (int j = 0; j < words[i].length(); j++) {
            if (!graph.containsKey(words[i].charAt(j))){
                graph.put(words[i].charAt(j), new HashSet<Character>());   
            }
        }
    }
    //populate map according to words orders
    for (int c = 1; c < words.length; c++) {
        int it = 0;
        while (it < words[c].length() && it < words[c - 1].length()) {
            //only find the first non-equal character in these two strings
            if (words[c].charAt(it) != words[c - 1].charAt(it)) {
                graph.get(words[c - 1].charAt(it)).add(words[c].charAt(it));
                break;
            }
            it++;
        }
    }
    return graph;
  }
  private String topologicalSorting(Map<Character, Set<Character>> graph) {
    //calculate in-degree based on graph
    Map<Character, Integer> inDegree = new HashMap<>();
    for (Character node : graph.keySet()){//initialize indegree to be 0
        inDegree.put(node, 0);
    }
    for (Character node : graph.keySet()) {//calculate indegree of each character shown
        for (Character child : graph.get(node)) inDegree.put(child, inDegree.get(child) + 1);
    }
    //put those with indegree == 0 into queue
    StringBuilder sb = new StringBuilder();//used to concate char into string
    PriorityQueue<Character> queue = new PriorityQueue<>();//to maintain lexicographical order
    for (Character ch : inDegree.keySet()) {
        if (inDegree.get(ch) == 0) queue.offer(ch);
    } 
    while(!queue.isEmpty()) {
        Character parent = queue.poll();
        sb.append(parent);
        Set<Character> children = graph.get(parent);
        for (Character child : children) {
            inDegree.put(child, inDegree.get(child) - 1);
            if (inDegree.get(child) == 0) queue.offer(child);
        }
    }
    String res = sb.toString();
    if (res.length() != graph.size()) return "";
    return res;
  }
}
