//this script shows the basic breadth-first-search method when traversing a graph starting from node root
void bfs(Node root) {
  Deque<Node> queue = new ArrayDeque<>();
  Set<Node> visited = new HashSet<>();
  queue.offer(root);
  visited.add(root);
  while (!queue.isEmpty()) {
    Node cur = queue.poll();
    for (Node nei : cur.neighbors) {
      if (visited.add(nei)){//!visited.contains(nei)
     	queue.offer(nei);
      }
    }
  }
}


//bfs level-order traverse on a tree: don't need visited to dedup since there's only 1 path to each treenode
void bfs(Node root) {
  Deque<Node> queue = new ArrayDeque<>();
  Set<Node> visited = new HashSet<>();
  queue.offer(root);
  int level = 0;
 //visited.add(root);
  while (!queue.isEmpty()) {
    int size = queue.size();
    for (int i = 0; i < size; i++) {
      Node cur = queue.poll();
      for (Node nei : cur.neighbors) {
        //if (visited.add(nei)){//!visited.contains(nei)
          queue.offer(nei);
      }
    }
    level++;
  }
}
