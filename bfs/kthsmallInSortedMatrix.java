public class Solution {
  public static class Cell{
    int row;
    int column;
    int value;
    Cell(int row, int column, int value){
      this.row = row;
      this.column = column;
      this.value = value;
    }   
  }
  
  public int kthSmallest(int[][] matrix, int k) {
    int mrow = matrix.length;
    int col = matrix[0].length;
    PriorityQueue<Cell> minheap = new PriorityQueue<Cell>(k, new Comparator<Cell>(){
      @Override
      public int compare(Cell c1, Cell c2) {
        if (c1.value == c2.value) {
          return 0;
        }
        return c1.value < c2.value? -1:1;
      }
    });
    
    //record generated elements, dont need to be regenerate
    boolean[][] generated = new boolean[mrow][col];
    minheap.offer(new Cell(0,0,matrix[0][0]));
    generated[0][0] = true;
    
    //poll first k - 1 smallest elements from minheap, then peek() will return kth smallest
    for (int i = 0; i < k - 1; i++) {
      Cell cur = minheap.poll();
      //expand cur cell, and generate its neighbors: when in bound && not generated
      if (cur.row + 1 < mrow && !generated[cur.row + 1][cur.column]) {
        minheap.offer(new Cell(cur.row + 1, cur.column, matrix[cur.row + 1][cur.column]));
        generated[cur.row + 1][cur.column] = true;
      }
      if (cur.column + 1 < col && !generated[cur.row][cur.column + 1]) {
        minheap.offer(new Cell(cur.row, cur.column + 1, matrix[cur.row][cur.column + 1]));
        generated[cur.row][cur.column + 1] = true;
      }
    }
    return minheap.peek().value;
  }
}

//simialr- given 2 sorted array, find kth smallest sum
//time: O(k*logk) - for k times: each includes 1 pop + 2 offer operations: 3*logk  -> 3k*logk
//space:O(k + m*n) - a heap size k and a m*n matrix
public int kthSmall(int[] A, int[] B, int k) {
	//assume they are not null
	if (A.length == 0 || B.length == 0) {
		return Integer.MAX_VALUE;
	}
	PriorityQueue<Cell> minheap = new PriorityQueue<>(k, new Comparator<Cell>(){
		@Override
		public int compare(Cell i, Cell j){
			if (i.val == j.val) {
				return 0;
			}
			return i.val < j.val ? -1:1;
		}
	});

	//m rows, n colomn 
	int m = A.length;
	int n = B.length;
	boolean[][] generated = new boolean[m][n];

	minheap.offer(new Cell(0, 0, A[0] + B[0]));
	generated[0][0] = true;
	int popIndex = 0;
	//also need to record its position: row, col long with its value to check if valid-wrap them together 
	//poll smallest k - 1 nodes from heap, heap.peek() will give you kth smallest
	while (popIndex < k - 1) {
		Cell cur = minheap.poll();
		popIndex++;
		//expand the polled node, generating right and bottom nodes and put into maxheap
		if (cur.row + 1 < m && !generated[cur.row + 1][cur.col]) {
			minheap.offer(new Cell(cur.row + 1, cur.col, A[cur.row + 1] + B[cur.col]));
			generated[cur.row + 1][cur.col] = true;
		}
		if (cur.col + 1 < n && !generated[cur.row][cur.col + 1]) {
			minheap.offer(new Cell(cur.row, cur.col + 1, A[cur.row] + B[cur.col + 1]));
			generated[cur.row][cur.col + 1] = true;
		}
	}
	return minheap.peek().val;
}

public static class Cell{
    int row;
    int column;
    int value;
    Cell(int row, int column, int value){
      this.row = row;
      this.column = column;
      this.value = value;
    }   
}
