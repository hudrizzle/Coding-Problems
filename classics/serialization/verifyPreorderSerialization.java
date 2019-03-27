//use stack to simulate recursion process: from bottom to top, check if each subtree is valid
//wanna find digit-#-# which is a valid leaf node. once found 2nd #, pop twice and insert another # representing a checked valid subtree
public class Solution {
  public boolean isValidSerialization(String preorder) {
    //corner cases
    if (preorder == null || preorder.length() == 0) return false;
    String[] input = preorder.split(",");
    Deque<String> stack = new ArrayDeque<>();
    //traverse the input array
    for (int i = 0; i < input.length; i++) {
      String str = input[i];
      if (str.equals("#")){
        while (!stack.isEmpty() && stack.peek().equals("#")){
          //2nd # and subtree has a root, may have nested valid subtrees, use while
          stack.pop();//pop 1st #
          if (!stack.isEmpty()){//stack.peek() is a digit
            stack.pop();
          }else {//stack.peek() is null -> root is null, invalid situation
            return false;
          }
        }
        stack.push(str);//push a "#" indicating this subtree is valid
      }else{//directly push digit into stack
        stack.push(str);
      }
    }
    //a valid ending should be: only 1 "#" left in the stack, indicating the whole tree is valid
    return stack.size() == 1 && stack.peek().equals("#");
  }
}

//method2. using in-degree, out-degree. diff = out-in. make sure its always >= 0, and final diff should be 0
//note: if works because it's preorder serialization. if it's inorder serialization, will encouter children before root, diff will < 0
public class Solution {
  public boolean isValidSerialization(String preorder) {
    //corner cases
    if (preorder == null || preorder.length() == 0) return false;
    String[] input = preorder.split(",");
    int diff = 1;
    for (String str : input) {
      if (--diff < 0) return false;//each node should have 1 indegree
      if (!str.equals("#")) diff += 2;//nodes which are not null should have 2 out degree
    }
    return diff == 0;
  }
}
