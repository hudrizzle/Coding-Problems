/*doubly linkedlist + hashtable 
*use cases: 1. K exists, return V and adjust to head(newest);
*2. cache is full, remove node at the tail of LinkedList
*3. insert a new node<K,V> to the newest position
*when adjusting to newest and remove oldest(tail), both require O1 time ->doubly linked list since single LL can't track previous node
*wanna randomly access node in O1 -> hashtable(key, reference to node(key,value))
*/
public class Solution<K, V> {
  //Node of linkedlist must be double directions for O1 adjusting
  public static class Node<K,V> {
    private K key;
    private V value;
    private Node<K,V> prev;
    private Node<K,V> next;
    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
  //since limit is pre-defined, cannot be changed
  private final int limit;
  private Node<K,V> head;
  private Node<K,V> tail;
  private Map<K, Node<K,V>> map;
  // limit is the max capacity of the cache
  public Solution(int limit) {
    this.limit = limit;
    this.map = new HashMap<K, Node<K,V>>();
  }
  
  //assume the newst node is placed in the head of LinkedList
  public void set(K key, V value) {
    Node<K,V> cur = map.get(key);
    if (cur != null) { // key exists in map, just update its value and adjust its position
      cur.value = value;
      //package the remove operation
      remove(cur);
    }else {//key not in map, check size of cache, add this new node
      if (map.size() == limit) {//remove the node at the tail
        remove(tail);
        cur = new Node<K,V>(key,value);
      }else {//directly append this new node
        cur = new Node<K,V>(key,value);
      }
    }
    append(cur);
  }
  //not to sync with map when remove or append node from LL
  //remove the node from LL: first remove edges pointing to node, then update head and tail
  private Node<K,V> remove(Node<K,V> node){
    map.remove(node.key);
    if (node.prev != null) node.prev.next = node.next;
    if (node.next != null) node.next.prev = node.prev;
    if (head == node) head = node.next;
    if (tail == node) tail = node.prev;
    return node;
  }
  //append the node to the newest position- head of LL
  private Node<K,V> append(Node<K,V> node){
    map.put(node.key, node);
    if (head == null){//LL is empty
      head = tail = node;
    }else {
      node.next = head;
      head.prev = node;
      head = node;
    }
    return node;
  }
  
  public V get(K key) {
    if (map.containsKey(key)){
      Node<K,V> cur = map.get(key);
      remove(cur);
      append(cur);
      return cur.value;
    }else {
      return null;
    }
  }
}


/*
用LinkedHashMap模拟Queue记录每个entry被用的先后顺序：
(1) 任何被get或被set的元素都会被移到LinkedHashMap的最后；
(2) 若新插入元素后，LinkedHashMap超出capacity，则移除首位元素。
*/

public class LRUCache {
    Map<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    
    public void set(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
            return;
        }
        
        map.put(key, value);
        
        if (map.size() > capacity) map.remove(map.entrySet().iterator().next().getKey());
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
            
        int val = map.remove(key);
        map.put(key, val);
        return val;
    }
}



/** 1.10 second time. self study
 **/

public class LRUCache<K, V> {
    public static class ListNode<K, V> {
        K key;
        V value;
        ListNode<K, V> next;
        ListNode<K, V> prev;

        public ListNode(K key, V value) {
            this.key = key;
            this.value = value;
            // this.next = null;
            // this.prev = null;
        }
    }

    private ListNode<K, V> head;
    private ListNode<K, V> tail;
    private Map<K, ListNode<K, V>> map;
    private final int limit;

    public LRUCache(int limit) {
        map = new HashMap<>();
        this.limit = limit;
    }

    // a new <Q,A> comes in
    public void set(K key, V value) {
        ListNode<K, V> node = null;
        // if contained in Map: update position in LL,
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;// update its value
            remove(node);
        }
        // if not in Map: append to tail of LL, add to Map; then remove the oldest from LL and map
        else {
            node = new ListNode<K, V>(key, value);
        }
        append(node);
        if (map.size() > limit)
            remove(head);
    }

    // remove both from map and ll -> then return the node itself
    private ListNode<K, V> remove(ListNode<K, V> node) {
        map.remove(node.key);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.prev;
        }
        node.next = node.prev = null;
        return node;
    }

    // append the node to map and LL
    private ListNode<K, V> append(ListNode<K, V> node) {
        map.put(node.key, node);
        // corner case: no elements
        if (head == null)
            head = tail = node;
        else {
            tail.next = node;
            node.prev = tail;
            // node.next = null;//unnecessary
            tail = node;
        }
        return node;
    }

    public V get(K key) {
        if (map.containsKey(key)) {
            ListNode<K, V> node = map.get(key);
            remove(node);
            append(node);
            return node.value;
        }
        else
            return null;// not in cache
    }
}