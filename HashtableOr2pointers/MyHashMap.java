public class MyHashMap<K,V> {
	//Node is a static class of myhashmap since:
	//it's closely bonded to MyHashMap class
	//probably need to access this class from outside of MyHashMap class
	public static class Node<K,V> {
		final K key;//once determined, cannot be changed
		V value;
		Node<K,V> next;
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
	}
	//static final variable are global constants
	public static final int DEFAULT_CAPACITY = 16;
	public static final float DEFAULT_LOAD_FACTOR = 0.75;

	private Node<K,V>[] array;
	private int size;//how may nodes are actually stored in the hashmap
	//private float loadFactor;//determine when to rehash

	/*
	public MyHashMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
	}
	public MyHashMap(int cap, float loadFactor) {
		if (cap <= 0) {
			throw new IllegalArgumentException("capacity cannot be <= 0");
		}
		this.array = (Node<K,V>[])(new Node[cap]);
		this.size = 0;
		this.loadFactor = loadFactor;
	}*/
	public MyHashMap() {
		array = (Node<K,V>[])(new Node[DEFAULT_CAPACITY]);
		size = 0;
	}
	public int size() {
		return size;
	}
	public boolean isEmpty() {
		return size == 0;
	}
	public void clear() {
		Arrays.fill(this.array, null);
		size = 0;
	}
	public V get(K key) {
		//always firstly find the head of the single linkedlist
		Node<K,V> node = array[index(key)];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) return node.getValue();
			node = node.next();
		}
		return null;
	}
	public boolean containsKey(K key) {
		Node<K,V> node = array[index(key)];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) return true;
			node = node.next;
		}
		return false;
	}
	public V put(K key, V value) {
		//hash through its key, get the index of bucket
		int i = index(key);
		Node<K,V> node = array[i];
		//node is already in the map, need to be updated
		while (node != null) {
			if (equalsKey(node.getKey(), key)){
				V oldValue = node.getValue();
				node.setValue(value);
				return oldValue;//return the last old value after updating
			}
			node = node.next;
		}
		//node is first time introduced into map
		Node<K,V> newEntry = new Node<>(key,value);
		newEntry.next = array[i];//insert the new node to the head of the linkedlist
		array[i] = newEntry;
		//check and update those values of necessary
		size++;//update size
		if (needRehashing()) rehash();

		return null; 
	}
	public V remove(K key) {
		int i = index(key);
		Node<K,V> prev = null;
		Node<K,V> curr = array[i];
		while (curr != null) {
			if (equalsKey(curr.getKey(), key)) {
				if (prev == null) array[i] = curr.next;//remove the head of LL
				else prev.next = curr.next;//remove the node in between the LL
				size--;
				return curr.getValue();
			}
			prev = curr;
			curr = curr.next;
		}
		return null;//cannot find the target to be rmd
	}
	private int index(K key) {
		return hash(key) % array.length;
	}
	private int hash(K key) {
		if (key == null) return 0;
		int code = key.hashCode();
		return code & 0x7fffffff;//? bit manipulation for what?
	}
   	private boolean equalsKey(K a, K b) {
        	return a == b || a != null && a.equals(b);
    	}
	private boolean needRehashing() {
		return size > DEFAULT_LOAD_FACTOR * array.length;
	}
	private void rehash() {
		Node<K,V>[] old = array;
		array = (Node<K,V>[]) new Node[old.length * 2];
		for (Node<K,V> e : old) {
			while (e != null) {
				Node<K,V> next = e.next;
				int i = index(e.getKey());
				//i is the new index of e, insert e into the head of ith LL
				e.next = array[i];
				array[i] = e;

				e = next;
			}
		}
	}
}
