//3-dimension water trapped:same idea with 2-d, moves whichever is smaller. min(all borders heights) decides how much water trapped by neighboring histogram. time: O(mnlog(mn)) space:O(mn)
public class Solution {
    public static class Cell{
        private int x;
        private int y;
        private int value;
        Cell(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    public int maxTrapped(int[][] matrix) {
        if(matrix == null || matrix.length < 3 || matrix[0].length < 3) return 0;
        int waterTrapped = 0;
        PriorityQueue<Cell> minheap = new PriorityQueue<>(new Comparator<Cell>(){
            @Override
            public int compare(Cell a, Cell b) {
                return a.value - b.value;
            }
        });
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        //first, put all border points into minheap
        offerborder(minheap, matrix, visited);
        //keep poll out smallest cell, then generate its neighbours, then adjust height of nei
        while (!minheap.isEmpty()) {
            //min height of all border values, decides how much water trapped in neighbors
            Cell cur = minheap.poll();
            List<Cell> neighbors = generateNei(cur, matrix);
            for (Cell nei : neighbors) {
                if (visited[nei.x][nei.y]) continue;
                visited[nei.x][nei.y] = true;
                int neiWater = cur.value - nei.value;
                waterTrapped += (neiWater > 0 ? neiWater : 0);
                //note adjusting nei height to be larger one of (cur, nei) as maxHeight
                minheap.offer(new Cell(nei.x, nei.y, Math.max(cur.value, nei.value)));
            }
        }
        return waterTrapped;
    }
    private List<Cell> generateNei(Cell cur, int[][] matrix) {
        List<Cell> neighbors = new ArrayList<>();
        if (cur.x - 1 >= 0) neighbors.add(new Cell(cur.x - 1, cur.y, matrix[cur.x - 1][cur.y]));
        if (cur.x + 1 < matrix.length) neighbors.add(new Cell(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y]));
        if (cur.y - 1 >= 0) neighbors.add(new Cell(cur.x, cur.y - 1, matrix[cur.x][cur.y - 1]));
        if (cur.y + 1 < matrix[0].length) neighbors.add(new Cell(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1]));
        return neighbors;
    }
    private void offerborder(PriorityQueue<Cell> minheap, 
                            int[][] matrix, boolean[][] visited) {
        for (int row = 0; row < matrix.length; row++) {
            visited[row][0] = visited[row][matrix[0].length - 1] = true;
            minheap.offer(new Cell(row, 0, matrix[row][0]));
            minheap.offer(new Cell(row, matrix[0].length - 1, matrix[row][matrix[0].length - 1]));
        }
        for (int col = 1; col < matrix[0].length - 1; col++) {
            visited[0][col] = visited[matrix.length - 1][col] = true;
            minheap.offer(new Cell(0, col, matrix[0][col]));
            minheap.offer(new Cell(matrix.length - 1, col, matrix[matrix.length - 1][col]));
        }
    }   
}
