//starting from each Equipment: find all shortest distances from this E to all other points
//add all shortest distances together from each E, look for smallest position
//time: O(m^2 * n^2)
public class Solution {
    private static final char EQUIP = 'E';
    private static final char OB = 'O';
    public List<Integer> putChair(char[][] gym) {
        int M = gym.length;
        int N = gym[0].length;
        int[][] cost = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (gym[i][j] == EQUIP) {
                    if (!addCost(cost, gym, i, j)) {
                        return Arrays.asList(-1, -1);//can't reach all E, no solution
                    }
                }
            }
        }
        List<Integer> res = null;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (gym[i][j] != EQUIP && OB != gym[i][j]) {
                    if (res == null) res = Arrays.asList(i, j);
                    else if (cost[i][j] < cost[res.get(0)][res.get(1)]){
                        res.set(0, i);
                        res.set(1, j);
                    }
                }
            }
        }
        return res == null ? Arrays.asList(-1, -1) : res;
    }
    //starting from(i,j) find shortest distances to all other points.
    //each point enqueue,dequeue once. -> O(mn)
    private boolean addCost(int[][] cost, char[][] gym, int i, int j) {
        boolean[][] visited = new boolean[gym.length][gym[0].length];
        int pathCost = 1;
        Queue<Pair> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.offer(new Pair(i, j));
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int l = 0; l < size; l++) {
                Pair cur = queue.poll();
                List<Pair> neis = getNeis(cur, gym);
                for (Pair nei : neis) {
                    if (!visited[nei.i][nei.j]) {
                        visited[nei.i][nei.j] = true;
                        cost[nei.i][nei.j] += pathCost;
                        queue.offer(nei); 
                    }
                }
            }
            pathCost++;
        }
        //if there's 1+ Equipment cannot be reached by (i,j)
        for (int l = 0; l < gym.length; l++) {
            for (int m = 0; m < gym[0].length; m++) {
                if (!visited[l][m] && EQUIP == gym[l][m]) {
                    return false;
                }
            }
        }
        return true;
    }
    private List<Pair> getNeis(Pair cur, char[][] gym) {
        int x = cur.i;
        int y = cur.j;
        int M = gym.length;
        int N = gym[0].length;
        List<Pair> neis = new ArrayList<>();
        if (x + 1 < M && OB != gym[x + 1][y]) neis.add(new Pair(x + 1, y)); 
        if (y + 1 < N && OB != gym[x][y + 1]) neis.add(new Pair(x, y + 1));
        if (x - 1 >= 0 && OB != gym[x - 1][y]) neis.add(new Pair(x - 1, y));
        if (y - 1 >= 0 && OB != gym[x][y - 1]) neis.add(new Pair(x, y - 1));
        return neis;
    }
    static class Pair{
        int i;
        int j;
        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
