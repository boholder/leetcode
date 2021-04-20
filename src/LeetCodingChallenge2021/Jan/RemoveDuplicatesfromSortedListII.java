package LeetCodingChallenge2021.Jan;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
// 过了
public class RemoveDuplicatesfromSortedListII {
    @Test
    public void t1() {
        ListNode test = ListNode.buildNodeChain(1, 2, 3, 3, 4, 4, 5);
        ListNode expected = ListNode.buildNodeChain(1, 2, 5);
        ListNode actual = Solution3.deleteDuplicates(test);
        assertThat(actual.next.next.next, nullValue());
    }

    @Test
    public void t2() {
        ListNode test = ListNode.buildNodeChain(1, 1, 1, 2, 3);
        ListNode expected = ListNode.buildNodeChain(2, 3);
        ListNode actual = Solution3.deleteDuplicates(test);
        assertThat(actual.next.next, nullValue());
    }

    @Test
    public void t3() {
        ListNode test = ListNode.buildNodeChain(1, 2, 2);
        ListNode expected = ListNode.buildNodeChain(1);
        ListNode actual = Solution3.deleteDuplicates(test);
        assertThat(actual.next, nullValue());
    }
}

class Solution3 {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode pointer = head;
        ListNode result = new ListNode();
        ListNode resultTail = result;
        while (1 == 1) {
            if (isAheadTwoNodeNotNull(pointer)) {
                // 开始判前两个的重来确定是否执行删除计划。
                if (isAheadTwoNodeAreDuplicate(pointer)) {
                    // 前两个重复，把指针移到下一个不重复的节点
                    pointer = movePointerToNextValueNode(pointer);
                    if (null == pointer) {
                        // 因为在接result时没剪除多余的next部分，
                        // 要处理一下resultTail，变成null
                        resultTail.next = null;
                        return result.next;
                    }
                } else {
                    // 前两个不重复，把头元素挂到结果链上。
                    resultTail.next = pointer;
                    resultTail = resultTail.next;
                    // 指针也前进一步到下一个原链节点上。
                    pointer = pointer.next;
                }
            } else {
                // 退出口：当前位置下不足两个元素（剩1个），不用判重，但需要把剩下那个挂载上。
                resultTail.next = pointer;
                return result.next;
            }
        }
    }

    private static boolean isAheadTwoNodeNotNull(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isAheadTwoNodeAreDuplicate(ListNode head) {
        if (head.val == head.next.val) {
            return true;
        } else {
            return false;
        }
    }

    private static ListNode movePointerToNextValueNode(ListNode pointer) {
        // 前两个重复，把指针移到下一个不重复的节点
        int duplicateValue = pointer.val;
        while (duplicateValue == pointer.val) {
            // 尝试下一个节点
            if (null == pointer.next) {
                // 下一个节点是null
                return null;
            } else {
                // 移动指针到下一个节点
                pointer = pointer.next;
            }
        }
        // 这时pointer所指的为新的不重复节点，但不知道它和下一个节点是否重复。
        return pointer;
    }
}