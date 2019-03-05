//array iterator: int[]{1,2,3}
class ArrayIterator implements Iterator<Integer>{
  int[] array;
  int index;
  public ArrayIterator(int[] array) {
    this.array = array;
    this.index = 0;
  }
  public boolean hasNext() {
    return index < array.length;
  }
  public int next() {
    if (!hasNext()) throw new NoSuchElementException();
    return array[index++];
  }
}
