/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 *
 *use stack- key point: once encounter a List<NestedInteger>，for loop to traverse its elements from tail to head and put into stack (because you wanna print them out from head to tail, and stack will output in a LIFO order -> 负负得正); once encounter an Integer, just print it out as next.
*/

import java.util.Iterator;

public class NestedIterator implements Iterator<Integer> {
    Deque<NestedInteger> stack;
    Integer next;

    public NestedIterator(List<NestedInteger> nestedList) {
        this.stack = new ArrayDeque<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer res = next;
        next = null;
        return res;
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        if (next != null) return true;
        //next == null, update it to next valid element
        if (stack.isEmpty()) return false;
        NestedInteger cur = stack.pop();
        if (cur.isInteger()) {
            next = cur.getInteger();
            return true;
        }else {//cur- element on the top of stack is a List<NestedInteger>, look into it
            List<NestedInteger> list = cur.getList();
            for (int i = list.size() - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
            return hasNext();
        }
    }

    @Override
    public void remove() {}
}
