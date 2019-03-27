int bfs(Node root, Node target) {
  Queue<Node> queue = new ArrayDeque<>();
  Set<Node> visited = new HashSet<>();
  int level = 0;
  queue.offer(root);
  visited.add(root);
  while (!queue.isEmpty()) {
    int size = queue.size();
    for (int i = 0; i < size; i++) {
      Node cur = queue.poll();
      //check option 1: check if it's target when expanding
      //if (cur == target) return level;
      for (Node nei : cur.neighbors){
        if (nei == target) return level + 1;//option 2. check if it's target when first visiting it, preferred
        if (visited.add(nei)) queue.offer(nei);
      }
    }
    level++;
  }
  return -1; // can't find target
}

