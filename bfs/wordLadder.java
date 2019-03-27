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

//1 direction && used char array to avoid swap function.
//check if == target when generaing-> first time visiting node
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start == null || end == null || dict.isEmpty()) return 0;
        //used to dedup since children may contain lots of visited elements
        dict.add(end);
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(start);
        q.offer(start);
        int level = 1;
        if (start.equals(end)) return level;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                List<String> children = findnext(cur, dict);
                for (String child : children) {
                    if (child.equals(end)) return level + 1;//check when first visited
                    if (visited.add(child)){
                        q.offer(child);
                    }
                }
            }
            level++;
        }
        return 0;//can't find target
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
}

//2nd bfs method(from 2 directions): a little improvement since use different startsets and endsets,exchange them if needed 
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
