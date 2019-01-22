package RemoveNthNodeFromEndOfList;

class ListNode {
  int val;
  ListNode next;

  ListNode(int x) {
    val = x;
  }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode right = head;
    ListNode left = head;
    int r_pos = 0;
    int l_pos = 0;

    while (right != null) {
      right = right.next;
      r_pos++;
      if (r_pos >= n) {
        left = left.next;
        l_pos++;
      }
    }
    if (r_pos > n) {
      left.val = left.next.val;
    }
    return head;
  }
}
