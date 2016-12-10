/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */


class ListNode {
    public int val = 0;
    public ListNode next = null;
    ListNode(int x) { this.val = x; }

    public String toString(){
        if ( next == null )
            return String.valueOf(this.val);
        else
            return String.valueOf(this.val) + " -> " + this.next.toString();
    }
}

public class AddTwoNumbers {
    private static ListNode IntToListNode(int value){
        ListNode result = new ListNode(value % 10);
        if (value > 9)
            result.next = IntToListNode(value / 10);
        return result;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if ( l1 == null )
            l1 = new ListNode(0);
        if ( l2 == null )
            l2 = new ListNode(0);

        ListNode result = new ListNode(l1.val + l2.val);

        if ( result.val > 9 ) {
            if (l1.next == null) {
                l1.next = new ListNode(0);
            }
            result.val = result.val % 10;
            l1.next.val += 1;
        }

        result.next = addTwoNumbers(l1.next, l2.next);

        return result;
    }

    public static void main(String [] args){
        for (int i = 0; i < 5; i++){
            ListNode l1 = IntToListNode((int)(900*Math.random()));
            ListNode l2 = IntToListNode((int)(900*Math.random()));
            System.out.printf("(%d):\n%s\n+\n%s\n", i, l1, l2);
            System.out.printf("=\n%s\n", addTwoNumbers(l1, l2) );
        }

    }
}
