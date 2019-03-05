public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
     //bfs
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict.isEmpty()) return 0;
        dict.add(end);
        dict.add(start);
        //used to dedup since children may contain lots of visited elements
        Set<String> visited = new HashSet<>();
        visited.add(start);
        
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            level++;
            for (int i = 0; i < size; i++) {
                String root = q.poll();
                if (root.equals(end)){
                    return level;
                }
                List<String> children = findNext(root, dict);
                for (String child : children) {
                    if (visited.contains(child)){
                        continue;
                    }
                    q.offer(child);
                    visited.add(child);
                }
            }
        }
        return 0;
    }
    private List<String> findNext(String root, Set<String> dict) {
        List<String> children = new ArrayList<String>();
        for (int i = 0; i < root.length(); i++) {
            char cur = root.charAt(i);
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == cur) {
                    continue;
                }
                String candidate = replace(root, i, ch);
                if (dict.contains(candidate)){
                    children.add(candidate);
                }
            }
        }
        return children;
    }
    private String replace(String root, int index, char ch) {
        char[] arr = root.toCharArray();
        arr[index] = ch;
        return new String(arr);
    }
}

//2nd bfs method: a little improvement since use different startsets and endsets,exchange them if needed 
//time: O(m*n*26)  space:O(n)
public class Solution {
    /*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
     //bfs
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict.isEmpty()) return 0;
        if (start.equals(end)) return 1;
        dict.add(end);
        dict.add(start);
        //used to dedup since children may contain lots of visited elements
        Set<String> visited = new HashSet<>();
        visited.add(start);
        visited.add(end);
        Set<String> startset = new HashSet<>();
        startset.add(start);
        Set<String> endset = new HashSet<>();
        endset.add(end);
        int level = 1;
        
        while (!startset.isEmpty()) {
            Set<String> next = new HashSet<>();
            level++;
            for (String root : startset) {
                List<String> children = findNext(root, dict);
                for (String child : children) {
                    if (endset.contains(child)) {
                        return level;
                    }
                    if (!visited.contains(child) && dict.contains(child)){
                        next.add(child);
                    }
                }
            }
            startset = next;
            //little improve: if endset.size() < startset.size(), exchange
            if (endset.size() < startset.size()) {
                Set<String> temp = endset;
                endset = startset;
                startset = temp;
            }
        }
        return 0;
    }
    private List<String> findNext(String root, Set<String> dict) {
        List<String> children = new ArrayList<String>();
        for (int i = 0; i < root.length(); i++) {
            char cur = root.charAt(i);
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (ch == cur) {
                    continue;
                }
                String candidate = replace(root, i, ch);
                if (dict.contains(candidate)){
                    children.add(candidate);
                }
            }
        }
        return children;
    }
    private String replace(String root, int index, char ch) {
        char[] arr = root.toCharArray();
        arr[index] = ch;
        return new String(arr);
    }
}
