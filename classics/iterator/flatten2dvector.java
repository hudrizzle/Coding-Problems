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
