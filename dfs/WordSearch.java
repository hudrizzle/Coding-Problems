//time: O(m * n * 4^length) space: O(mn)
public class Solution {
  public boolean isWord(char[][] board, String word) {
    //Corner case
    if (word == null || word.length() == 0 || board == null || board.length == 0 || board[0].length == 0) return false;
    int m = board.length, n = board[0].length;
    boolean[][] visited = new boolean[m][n];
    char[] input = word.toCharArray();
    boolean res = false;
    //try each char in board as starting of dfs
    for (int i = 0; i < m; i++) {
    	for (int j = 0; j < n; j++) {
    		if (wordExist(visited, input, 0, board, i, j)) res = true;
    	}
    }
    return res;
  }
  //match input[level] with board[row][col]. if matched, continue recursion until base case
  private boolean wordExist(boolean[][] visited, char[] input, int level, char[][] board, int row, int col) {
  	//base case: reached the end of string(must goes first since might out of bound, will be considered as corner case)
  	if (level == input.length) return true;
  	
  	//corner cases: 1. visited char; 2. not matching 3.index out of IndexOutOfBoundsException
  	if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col] || board[row][col] != input[level]) return false;
  	//recursion rule: decide if continue recursion in current level: matched, go 4 directions: any of them returns true, then return true
  	visited[row][col] = true;
  	if (wordExist(visited, input, level + 1, board, row + 1, col)) return true;
  	if (wordExist(visited, input, level + 1, board, row, col + 1)) return true;
  	if (wordExist(visited, input, level + 1, board, row - 1, col)) return true;
  	if (wordExist(visited, input, level + 1, board, row, col - 1)) return true;
  	visited[row][col] = false;//backtracking
    return false;
  }
}
