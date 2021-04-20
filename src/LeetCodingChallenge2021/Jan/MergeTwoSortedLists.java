package LeetCodingChallenge2021.Jan;

import org.junit.Test;
// 过了
public class MergeTwoSortedLists {
    @Test
    public void mergeTwoFullList() {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode result = Solution1.mergeTwoLists(l1, l2);
        System.out.println("123");
    }

    @Test
    public void mergeOneNullListWithOne() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = null;
        ListNode result = Solution1.mergeTwoLists(l1, l2);
        System.out.println("123");
    }

    @Test
    public void timeExceeded() {
        ListNode l1 = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(2);
        l2.next.next = new ListNode(4);

        ListNode result = Solution1.mergeTwoLists(l1, l2);
        System.out.println("123");
    }
}

class Solution1 {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode tail = head;

        doMerge(l1, l2, tail);
        return head.next;
    }

    private static void doMerge(ListNode l1, ListNode l2, ListNode tail) {
        while (1 == 1) {
            if (null == l1 && null == l2) {
                break;
            } else if (null == l1) {
                tail.next = l2;
                break;
            } else if (null == l2) {
                tail.next = l1;
                break;
            } else {
                if (l1.val <= l2.val) {
                    tail.next = l1;
                    tail = tail.next;
                    l1 = movePointerToNextIfPointerIsNotNull(l1);
                    continue;
                } else {
                    tail.next = l2;
                    tail = tail.next;
                    l2 = movePointerToNextIfPointerIsNotNull(l2);
                    continue;
                }
            }
        }
    }

    private static ListNode movePointerToNextIfPointerIsNotNull(ListNode l1) {
        if (null != l1) {
            l1 = l1.next;
        }
        return l1;
    }
}