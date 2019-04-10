public class Solution {
    /**
     * @param forest: a list of integers
     * @return: return a integer
     */
    //start from (0,0) and cut trees from shortest to tallest. can go 0
    public int cutOffTree(List<List<Integer>> forest) {
    	if(forest==null || forest.size()<1 || forest.get(0)==null|| forest.get(0).size()<1) return -1;
    	
    	List<int[]> pool = new ArrayList<>();//int[]{x,y,height}
    	//build a list containing all trees info. sort in ascending order
    	for(int i = 0; i<forest.size(); i++){
    		for(int j = 0; j<forest.get(i).size();j++){
    			int height = forest.get(i).get(j);
    			if(height>1) pool.add(new int[]{i, j, height});
    		}
    	}
    	Collections.sort(pool, (a,b) -> a[2] - b[2]);
    	
    	int res = 0;
    	//run shortestDistance from starting to ending positions
    	int[] start = new int[]{0,0};
    	for (int[] end : pool) {
    	    int dist = shortestDistance(forest, start, end);
    	    if (dist < 0) return -1;//failed
    	    else {//succesfully run from start to end, update
    	        res += dist;
    	        start[0] = end[0];
    	        start[1] = end[1];
    	    }
    	}
    	return res;
    }
    private int shortestDistance(List<List<Integer>> forest, int[] start, int[] end) {
        //a queue of coordinate
        int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        q.offer(start);
        visited[start[0]][start[1]] = true;
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                if (cur[0] == end[0] && cur[1] == end[1]){
                    return level;
                }
                for (int[] dir : dirs) {
                    int nx = cur[0] + dir[0];
                    int ny = cur[1] + dir[1];
                    //exclude invalid situations
                    if (nx < 0 || nx >= forest.size() || ny < 0 || ny >= forest.get(0).size() || forest.get(nx).get(ny) == 0 || visited[nx][ny]) continue;
                    q.offer(new int[]{nx,ny});
                    visited[nx][ny] = true;
                }
            }
            level++;
        }
        return -1;//can't reach target coordinate
    }
}
