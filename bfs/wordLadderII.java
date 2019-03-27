/* find all the shortest distance from start to end*/
public class Solution {
//first use bfs to record the shortest path, record the prev node 
//run dfs on the prev relationship
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        Map<String, Integer> level = new HashMap<>();//record the shortest path
        Map<String, List<String>> prevMap = new HashMap<>(); // prev relation
    
        bfs(start, end, dict, level, prevMap);
        dfs(start, end, prevMap, path, res);
        return res;
    }
    private void bfs(String start, String end, Set<String> dict,
    Map<String, Integer> level, Map<String, List<String>> prevMap) {
        if (start == null || end == null || dict.isEmpty()) return;
        dict.add(end);
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(start);
        visited.add(start);
        level.put(start, 0);//set level of start to be 0
        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (str.equals(end)) return;
            List<String> neighbors = findnext(str, dict);
            for (String nei : neighbors) {
                if (!visited.contains(nei)){
                    level.put(nei, level.get(str) + 1);
                    queue.offer(nei);
                    visited.add(nei);
                    addPrev(str, nei, prevMap);
                }else {//*****cannot ignore else... part for reason 1 in note******
                    if (level.get(nei) == level.get(str) + 1) addPrev(str, nei, prevMap);
                }
            }
        }
    }
    private void addPrev(String src, String cur, Map<String, List<String>> prevMap){
        if (!prevMap.containsKey(cur)){
            prevMap.put(cur, new ArrayList<String>());
        }
        prevMap.get(cur).add(src);
    }
    private List<String> findnext(String root, Set<String> dict) {
        List<String> children = new ArrayList<String>();
        char[] input = root.toCharArray();
        for (int i = 0; i < input.length; i++) {
            char origin = input[i];
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == origin) {
                    continue;
                }
                input[i] = ch;
                String next = new String(input);
                if (dict.contains(next)) children.add(next);
            }
            input[i] = origin;
        }
        return children;
    }
    public void dfs(String start, String end, Map<String, List<String>> prevMap, List<String> path, List<List<String>> res) {
        //base case: **** cannot igore check get(end) = null since end might be unreachable 
        if (prevMap.get(end) == null || prevMap.get(end).size() == 0){
            if (end.equals(start)){
                path.add(end);
                Collections.reverse(path);
                res.add(new ArrayList<String>(path));
                Collections.reverse(path);
                path.remove(path.size() - 1);
                return;
            }
            
        }
        
        path.add(end);
        for (String str : prevMap.get(end)) {
            dfs(start, str, prevMap, path, res);
        }
        path.remove(path.size() - 1);
    }
}
