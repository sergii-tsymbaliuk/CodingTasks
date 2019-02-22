package RemoveNthNodeFromEndOfList;

import static org.junit.Assert.*;

import org.junit.Test;

public class SolutionTest {
  Solution s = new Solution();

  @Test
  public void removeNthFromEnd() {
    assertEquals(
      "[1,2,3,5]",
      s.removeNthFromEnd(ListNode.fromString("1,2,3,4,5"), 2).toString());
    assertEquals(
      "[1]",
      s.removeNthFromEnd(ListNode.fromString("1,2"), 1).toString());
    assertEquals(
      "[1,2]",
      s.removeNthFromEnd(ListNode.fromString("1,2"), 0).toString());

    assertEquals(
      "[2]",
      s.removeNthFromEnd(ListNode.fromString("1,2"), 2).toString());
    assertNull(s.removeNthFromEnd(ListNode.fromString(""), 1));
    assertNull(s.removeNthFromEnd(ListNode.fromString("1"), 1));
  }

  @Test
  public void fromStringTest() {
    assertEquals("[1,2,3]", ListNode.fromArray(new int[]{1,2,3}).toString());
  }
}