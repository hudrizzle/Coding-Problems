/*this is an implementation of capacity limited minheap containing only int values
with the capability to do update and poll at a specific index
public methods: size(), isEmpty(), isFull(), peek(), poll(), offer(), update(int index, int value)
extension: make capacity unbounded by allocating a new larger array
*/
public class Minheap {
	private int[] array;
	//note size indicates how many elements inside heap instead of capacity
	private int size;

	public Minheap(int[] array) {
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException("input array invalid");
		}
		this.array = array;
		this.size = array.length;
		heapify();
	}

	private void heapify() {
		for (int i = size / 2 - 1; i >= 0; i--) {
			percolateDown(i);
		}
	}

	public Minheap(int cap) {
		if (cap <= 0) throw new IllegalArgumentException("input array invalid");
		this.array = new int[cap];
		size = 0;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == array.length;
	}

	private void percolateUp(int index) {
		while (index > 0) {
			int p = (index - 1) / 2;
			if (array[p] > array[index]) {
				swap(p, index);
			}else {
				break;
			}
			index = p;
		}
	}

	//note: for each parent node, leftidx must exist but there's not necessarily a valid rightidx!!
	private void percolateDown(int index) {
		//only when index is a parent index can percolate down
		while (index <= size / 2 - 1) {
			int leftidx = index * 2 + 1;
			int rightidx = index * 2 + 2;
			int swapidx = leftidx; 
			//check if rightidx is valid, also update swap candidate if necessary
			if (rightidx < size && array[rightidx] < array[leftidx]) swapidx = rightidx;
			if (array[swapidx] < array[index]) {
				swap(swapidx, index);
			}else {
				break;
			}
			index = swapidx;
		}
	}

	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public int peek() {
		if (size == 0) throw NoSuchElementException("heap is empty");
		return array[0];
	}

	public int poll() {
		if (size == 0) throw NoSuchElementException("heap is empty");
		int res = array[0];
		array[0] = array[size - 1];
		size--;
		percolateDown(0);
		return res;
	}
	//insert from bottom, then percolateUp
	public void offer(int ele) {
		//if array is full, automatically double the length
		if (size == array.length) {
			int[] newarray = new int[array.length * 2];
			for (int i = 0; i < array; i++) {
				newarray[i] = array[i];
			}
			array = newarray;
		}
		array[size++] = ele;
		percolateUp(size - 1);
	}
	//update element at index to be ele, return the old value
	public int update(int index, int ele) {
		if (index < 0 || index > size - 1) {
			throw new ArrayIndexOutOfBoundsException("invalid index");
		}
		int res = array[index];
		array[index] = ele;
		if (res > ele) {// new value is smaller, percolate up
			percolateUp(index);
		}else {
			percolateDown(index);
		}
		return res;
	}
}
