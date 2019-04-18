public class Solution {
	static class Node{
		Node prev;
		Node next;
		Character ch;
		Node(Character ch) {
			this.ch = ch;
		}
  	}
	//only put characters which occur once in doubly linkedlist
	private Node head;
	private Node tail;
	//for each existing character 2 situations:
	//1.only occured once, put in map
	//2.occur more than once, remove from map and put into repeated set
	private Map<Character, Node> singleOccur;
	private Set<Character> repeated;

	public Solution() {
		//use sentinel node to eliminate some corner cases
		tail = new Node(null);
		tail.next = tail.prev = tail;//?????
		head = tail;
		singleOccur = new HashMap<>();
		repeated = new HashSet<>();
	}

	public void read(char ch) {
		if (repeated.contains(ch)) return;
		Node node = singleOccur.get(ch);
		if (node == null) {//first-time occur
			node = new Node(ch);
			append(node);
		}else {//repeated
			remove(node);
		}
	}
	//append the new node to the tail of LL, add to map
  //let head keeps to be a dummy node, head.next to be the first valid node
	private void append(Node node) {
		singleOccur.put(node.ch, node);
		node.prev = tail;
		//node.next = head????????why???? 
		tail.next = node;
		tail = node;
	}
	//node repeated, remove from map and LL
	private void remove(Node node) {
		if (node.prev != null) node.prev.next = node.next;
		if (node.next != null) node.next.prev = node.prev;
		if (node == tail) tail = node.prev;
		node.prev = node.next = null; // is it necessary?
		repeated.add(node.ch);
		singleOccur.remove(node.ch);
	}
	public Character firstNonRepeating() {
    if (head == tail) return null;
		return head.next.ch;
	}
}
