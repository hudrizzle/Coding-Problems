public class Vector2D implements Iterator<Integer> {
    Queue<Iterator<Integer>> iterators;
    public Vector2D(List<List<Integer>> vec2d) {
        iterators = new ArrayDeque<>();
        for (List<Integer> list : vec2d) {
            Iterator<Integer> it = list.iterator();
            if (it.hasNext()) iterators.offer(it);
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Iterator<Integer> it = iterators.peek();
        Integer next = it.next();
        if (!it.hasNext()) iterators.poll();
        return next;
    }

    @Override
    public boolean hasNext() {
        return !iterators.isEmpty();
    }

    @Override
    public void remove() {}
}

//method2. use row, col, next to keep track of index
public class Vector2D implements Iterator<Integer> {
    Integer next;
    int row;
    int col;
    List<List<Integer>> vec;
    
    public Vector2D(List<List<Integer>> vec2d) {
        this.vec = vec2d;
        this.row = 0;
        this.col = -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();
        Integer ans = next;
        next = null;
        return ans;
    }

    @Override
    public boolean hasNext() {
        if (next != null) return true;
        //adjust next to the next valid element 
        if (updateIndex()){//return true means updating success
            next = vec.get(row).get(col);
            return true;
        }
        return false;
    }
    //used to help hasNext() function to move row, col pointers
    private boolean updateIndex() {
        while (row < vec.size()){
        //must while since updated col to be -1. or use recursion- call updateIndex again
            if (col + 1 < vec.get(row).size()){//col + 1 means next upcoming column
                col++;
                return true;
            }            
            row++;//row might become vec.size(), check later in while loop
            col = -1;
        }
        return false;
    }

    @Override
    public void remove() {}
}
