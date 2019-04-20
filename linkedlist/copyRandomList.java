//time:O(n), space:O(n)
//deep copy: new each node in the linkedlist
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if( head == null ) return null;
        
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while ( cur != null ){
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        
        cur = head;
        
        while ( cur != null ){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        
        return map.get(head);
    }
}
//improved space to O(1)
//1 - 2 - 3  (random pointer: 1 -> 2)
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if( head == null ) return null;
        //step1. 1-1'-2-2'-3-3' (random pointer: 1 -> 2)
        RandomListNode p = head;
        while (p != null) {
            RandomListNode node = new RandomListNode(p.label);
            node.next = p.next;
            p.next = node;
            p = p.next.next;
        }
        //step2. copy random pointer 1-1'-2-2'-3-3' (random pointer: 1 -> 2, 1' -> 2')
        p = head;
        while (p != null) {
            if (p.random != null) p.next.random = p.random.next; 
            p = p.next.next;
        }
        //step3. split original and copied list apart
        RandomListNode dummy = new RandomListNode(0), tail = dummy;
        p = head;
        while (p != null) {
            RandomListNode copyNode = p.next;
            p.next = copyNode.next;
            tail.next = copyNode;
            tail = copyNode;
            p = p.next;
        }
        
        return dummy.next;
    }
}
