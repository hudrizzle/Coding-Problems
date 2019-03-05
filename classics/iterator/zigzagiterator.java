//lintcode 540
public class ZigzagIterator {
    /*
    * @param v1: A 1d vector
    * @param v2: A 1d vector
    */
    private Iterator<Integer> iter1;
    private Iterator<Integer> iter2;
    private int turn;//initial set to 0: next return is v1 when turn is even number
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.iter1 = v1.iterator();
        this.iter2 = v2.iterator();
    }

    /*
     * @return: An integer
     */
    public int next() {
        if (!hasNext()) throw new NoSuchElementException();
        turn++;
        if (turn % 2 == 1 && iter1.hasNext() || !iter2.hasNext()) {
            //return v1's next value
            return iter1.next();
        }else if (turn % 2 == 0 && iter2.hasNext() || !iter1.hasNext()) {
            return iter2.next();
        }
	//actually will not reach -1 since if has no next, directly throw exception;
        return -1;
    }

    /*
     * @return: True if has next
     */
    public boolean hasNext() {
        return (iter1.hasNext() || iter2.hasNext());
    }
}

//q3: zigzag iterator- given a list of iterators, return according to column of iterators
//method inspired by BFS: print level by level
public class ZigzagIterator implements Iterator<Integer> {
  private Queue<Iterator<Integer>> iterators;
  //when initializing, put iterators that has next into queue
  public ZigzagIterator(List<Iterator<Integer>> itlist) {
    this.iterators = new ArrayDeque<Integer>();
    for (Iterator<Integer> it : itlist) {
      if (it.hasNext()) this.iterator.add(it);//add to the end of deque
    }
  }
  @Override
  public boolean hasNext() {
    return !iterators.isEmpty();
  }
  @Override
  public Integer next() {
    if (!hasNext()) throw new NoSuchElementException();
    Iterator<Integer> cur = iterators.poll();
    Integer next = cur.next();
    if (cur.hasNext()) iterators.add(cur);
    return next;
  }
}
