/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
//advance the listnode which has the smallest value
//try use a minheap to pop smallest node each time
//o(NLOGK)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //corner case
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> minheap = new PriorityQueue<>(lists.length, (a,b) -> a.val - b.val);  
        for (ListNode node : lists) {
            if (node != null) minheap.offer(node);
        }
        //dummy head, append
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!minheap.isEmpty()) {
            ListNode cur = minheap.poll();
            tail.next = cur;
            if (cur.next != null) minheap.offer(cur.next);
            tail = tail.next;
        }
        return head.next;
    }
}
