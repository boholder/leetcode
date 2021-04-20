package LeetCodingChallenge2021.Jan;

public class ListNode implements Comparable<ListNode> {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildNodeChain(int... array) {
        ListNode head = new ListNode(array[0]);
        ListNode pointer = head;
        for (int index = 1; index < array.length; index++) {
            pointer.next = new ListNode(array[index]);
            pointer = pointer.next;
        }
        return head;
    }

    @Override
    public int compareTo(ListNode another) {
        ListNode tail = this;
        boolean bothTwoNodeIsNotNull = null != tail && null != another;

        while (bothTwoNodeIsNotNull) {
            if (tail.val > another.val) {
                return 1;
            } else if (tail.val < another.val) {
                return -1;
            }
            tail = tail.next;
            another = another.next;
            bothTwoNodeIsNotNull = null != tail && null != another;
        }
        return checkWhichChainReachEnd(another, tail);
    }

    private int checkWhichChainReachEnd(ListNode another, ListNode tail) {
        if (null == tail && null == another) {
            return 0;
        } else if (null == tail) {
            return -1;
        } else {
            return 1;
        }
    }
}
