package net.tsymbaliuk.leetcode.RemoveNthNodeFromEndOfList;

import java.util.Arrays;

class ListNode {
  int val;
  ListNode next;

  public ListNode(int x) {
    val = x;
  }

  @Override
  public String toString() {
    ListNode head = this;
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(head.val);
    head = head.next;
    while (head != null) {
      sb.append(",");
      sb.append(head.val);
      head = head.next;
    }
    sb.append("]");
    return sb.toString();
  }

  public static ListNode fromArray(int[] ar) {
    ListNode head = null;
    for (int i = ar.length - 1; i >= 0; i--) {
      ListNode node = new ListNode(ar[i]);
      node.next = head;
      head = node;
    }
    return head;
  }

  public static ListNode fromString(String str) {
    if (str == null || str.isEmpty()) {
      return null;
    }
    int[] vals = Arrays.stream(str.split(","))
      .mapToInt(Integer::parseInt)
      .toArray();
    return fromArray(vals);
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
    if (head == null || n == 0) return head;
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode right = head;
    ListNode left = dummyHead;
    int length = 0;

    while (right != null && length < n) {
      length++;
      right = right.next;
    }

    while (right != null) {
      length++;
      right = right.next;
      left = left.next;
    }

    left.next = left.next.next;

    return dummyHead.next;
  }
}
