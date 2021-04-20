package LeetCodingChallenge2021.Jan;

import org.junit.Test;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

// 过了，注意把import语句也放到答案里提交。
// 时间空间都很菜，
// 可能优化在不用String和BigInteger直接手算吧，存个进位的缓存之类。
// 或者先给俩链建索引，再倒着处理索引。
public class AddTwoNumbers {
    @Test
    public void t1() {
        ListNode l1 = ListNode.buildNodeChain(2, 4, 3);
        ListNode l2 = ListNode.buildNodeChain(5, 6, 4);
        ListNode actual = Solution7.addTwoNumbers(l1, l2);
        ListNode expected = ListNode.buildNodeChain(7, 0, 8);
        assertThat("actual must equal to expected", actual, comparesEqualTo(expected));
    }

    @Test
    public void t2() {
        ListNode l1 = ListNode.buildNodeChain(0);
        ListNode l2 = ListNode.buildNodeChain(0);
        ListNode actual = Solution7.addTwoNumbers(l1, l2);
        ListNode expected = ListNode.buildNodeChain(0);
        assertThat("actual must equal to expected", actual, comparesEqualTo(expected));
    }

    @Test
    public void t3() {
        ListNode l1 = ListNode.buildNodeChain(9, 9, 9, 9, 9, 9, 9);
        ListNode l2 = ListNode.buildNodeChain(9, 9, 9, 9);
        ListNode actual = Solution7.addTwoNumbers(l1, l2);
        ListNode expected = ListNode.buildNodeChain(8, 9, 9, 9, 0, 0, 0, 1);
        assertThat("actual must equal to expected", actual, comparesEqualTo(expected));
    }
}

class Solution7 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger num1 = getNodeChainRepresentsBitInteger(l1);
        BigInteger num2 = getNodeChainRepresentsBitInteger(l2);
        BigInteger sumResult = num1.add(num2);
        return buildResultNodeChain(sumResult);
    }

    private static BigInteger getNodeChainRepresentsBitInteger(ListNode chain) {
        return new BigInteger(getNodeChainRepresentsNumberString(chain));
    }

    private static String getNodeChainRepresentsNumberString(ListNode chain) {
        StringBuilder builder = buildNumberStringFrom(chain);
        return builder.reverse().toString();
    }

    private static StringBuilder buildNumberStringFrom(ListNode chain) {
        StringBuilder builder = new StringBuilder();
        while (chain != null) {
            builder.append(chain.val);
            chain = chain.next;
        }
        return builder;
    }

    private static ListNode buildResultNodeChain(BigInteger number) {
        BigInteger[] resultAndRemainder;
        ListNode head = new ListNode();
        ListNode tail = head;

        do {
            // origin: "1234" -> resultAndRemainder[0]: "123" [1]: "4"
            resultAndRemainder = number.divideAndRemainder(BigInteger.TEN);
            tail = boxAndAppendNodeToTail(tail, Math.abs(resultAndRemainder[1].intValue()));
            number = resultAndRemainder[0];
        } while (number.compareTo(BigInteger.ZERO) != 0);

        return head.next;
    }

    private static ListNode boxAndAppendNodeToTail(ListNode head, int number) {
        head.next = new ListNode(number);
        return head.next;
    }
}