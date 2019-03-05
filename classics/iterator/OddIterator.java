//q1 given Iterator<Integer>, design an iterator that only shows odd number
//this method will consider 'null' as invalid element, therefore not suitable for those containers containing null
public class IteratorOdd implements Iterator<Integer> {
  private Integer next;//points to the result to be returned
  private Iterator<Integer> it;//given iterator
  private boolean hasNext;
  /*
    use hasNext to avoid the situation that next() returns null
    flag: becomes true after calling hasNext() and find the next ready
    becomes false after calling next() and have not yet call hasNext()
    since users may call hasNext() for multiple times but not call next()
  */

  //constructor
  public IteratorOdd(Iterator<Integer> it) {
    this.it = it;
  }

  //chache valid element and save it into next.
  @Override
  public boolean hasNext() {
    if (hasNext) return true;
    while(it.hasNext()) {
      int n = it.next();
      if (n % 2 == 1) {//valid return value
        next = n;
        hasNext = true;
        return true;
      }
    }
    return false;
  }

  //always call hasNext() first in next()
  //clear hasNext and next value
  @Override
  public Integer next() {
    if (!hasNext()) throw new NoSuchElementException();
    hasNext = false;
    return next;
  }
}
