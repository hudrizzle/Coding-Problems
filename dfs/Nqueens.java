//note!Do deep copy when you add list to result list in recursion
//O(1) on checkValid since extra 3 boolean arrays are used to records the used col, diagonal, revdiagonal
//better use row to conveniently access array index 
//time: O(n^n) -> O(n!)
public class Solution {
  public List<List<Integer>> nqueens(int n) {
    List<List<Integer>> result = new ArrayList<>();
    if (n <= 0) {
      return result;
    }
    List<Integer> arr = new ArrayList<>();
    boolean[] usedCol = new boolean[n];
    boolean[] usedDiagonal = new boolean[2 * n - 1];
    boolean[] usedRevDiagonal = new boolean[2 * n - 1];
    traverseLevel(n, 0, arr, result, usedCol, usedDiagonal, usedRevDiagonal);
    return result;
  }
  
  private void traverseLevel(int n, int row, List<Integer> arr, List<List<Integer>> result,
                            boolean[] usedCol, boolean[] usedDiagonal, boolean[] usedRevDiagonal){
  //base: arr collected all n index -> can put into result
    if (arr.size() == n) {
      result.add(new ArrayList<Integer>(arr));
      return;
    }
    
   //recursion rule: for each col, check if can put
    for (int i = 0; i < n; i++) {
      if (passCheck(n, row, i, usedCol, usedDiagonal, usedRevDiagonal)){
        mark(n, row, i, usedCol, usedDiagonal, usedRevDiagonal);
        arr.add(i);
        traverseLevel(n, row + 1, arr, result, usedCol, usedDiagonal, usedRevDiagonal);
        unmark(n, row, i, usedCol, usedDiagonal, usedRevDiagonal);
        arr.remove(arr.size() - 1);
      }
    }
  }
  private boolean passCheck(int n, int row, int col, boolean[] usedCol, 
                         boolean[] usedDiagonal, boolean[] usedRevDiagonal) {
    return !usedCol[col] && !usedDiagonal[row + col] && !usedRevDiagonal[col - row + n - 1];
  }
  private void mark(int n, int row, int col, boolean[] usedCol, 
                    boolean[] usedDiagonal, boolean[] usedRevDiagonal){
    usedCol[col] = true;
    usedDiagonal[col + row] = true;
    usedRevDiagonal[col - row + n - 1] = true;
  }
  private void unmark(int n, int row, int col, boolean[] usedCol, 
                    boolean[] usedDiagonal, boolean[] usedRevDiagonal){
    usedCol[col] = false;
    usedDiagonal[col + row] = false;
    usedRevDiagonal[col - row + n - 1] = false;
  }
}


//another way to checkpass - but will take O(n)
  private boolean passCheck(int curRow, int tryCol, 
                            List<Integer> occupied){
    //check existing queens to see if tryCol conflicts with them
    for (int i = 0; i < occupied.size(); i++) {
      //difference between current row with existing queen row
      //curCol means current column of the existing queen
      int diff = curRow - i;
      int curCol = occupied.get(i);
      if (tryCol == curCol || tryCol == curCol - diff || tryCol == curCol + diff) {
        return false;
      }
    }
    return true;
  }
