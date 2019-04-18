/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
//move the smaller one
public class Solution {
  public ListNode merge(ListNode one, ListNode two) {
    ListNode dummy = new ListNode(0);
    //node, one, two are used to traverse the res, one, two
    ListNode node = dummy;
    while (one != null && two != null) {
      if (one.value <= two.value) {
        node.next = one;
        one = one.next;
      }else {
        node.next = two;
        two = two.next;
      }
      node = node.next;
    }
    //one of them is null, the other may still remain values
    if (one != null) {
      node.next = one;
    }else {
      node.next = two;
    }
    return dummy.next;
  }
}
