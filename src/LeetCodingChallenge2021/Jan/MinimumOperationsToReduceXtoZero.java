package LeetCodingChallenge2021.Jan;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

// t6 时间太长了，提示说贪婪但我不会做。
// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/discuss/935935/Java-Detailed-Explanation-O(N)-Prefix-SumMap-Longest-Target-Sub-Array
// 单向循环，但换了思路，寻找当前位置的从0到此sum中，有没有一小段前面的prefixSum + 中间段 target = sum，
// 如果有，计算是否使target段长度最大。这样走一遍就能找到最大值。
public class MinimumOperationsToReduceXtoZero {
    @Test
    public void t1() {
        int[] nums = {1, 1, 4, 2, 3};
        int x = 5;
        Solution9 s = new Solution9();
        assertThat(s.minOperations(nums, x), Matchers.is(2));
    }

    @Test
    public void t2() {
        int[] nums = {5, 6, 7, 8, 9};
        int x = 4;
        Solution9 s = new Solution9();
        assertThat(s.minOperations(nums, x), Matchers.is(-1));
    }

    @Test
    public void t3() {
        int[] nums = {3, 2, 20, 1, 1, 3};
        int x = 10;
        Solution9 s = new Solution9();
        assertThat(s.minOperations(nums, x), Matchers.is(5));
    }

    @Test
    public void t4() {
        int[] nums = {8828, 9581, 49, 9818, 9974, 9869, 9991, 10000, 10000, 10000, 9999, 9993, 9904, 8819, 1231, 6309};
        int x = 134365;
        Solution9 s = new Solution9();
        assertThat(s.minOperations(nums, x), Matchers.is(16));
    }

    @Test
    public void t5() {
        int[] nums = {6016, 5483, 541, 4325, 8149, 3515, 7865, 2209, 9623, 9763, 4052, 6540, 2123, 2074, 765, 7520, 4941, 5290, 5868, 6150, 6006, 6077, 2856, 7826, 9119};
        int x = 31841;
        Solution9 s = new Solution9();
        assertThat(s.minOperations(nums, x), Matchers.is(6));
    }

    @Test
    public void t6() {
        int[] nums = {1241, 8769, 9151, 3211, 2314, 8007, 3713, 5835, 2176, 8227, 5251, 9229, 904, 1899, 5513, 7878, 8663, 3804, 2685, 3501, 1204, 9742, 2578, 8849, 1120, 4687, 5902, 9929, 6769, 8171, 5150, 1343, 9619, 3973, 3273, 6427, 47, 8701, 2741, 7402, 1412, 2223, 8152, 805, 6726, 9128, 2794, 7137, 6725, 4279, 7200, 5582, 9583, 7443, 6573, 7221, 1423, 4859, 2608, 3772, 7437, 2581, 975, 3893, 9172, 3, 3113, 2978, 9300, 6029, 4958, 229, 4630, 653, 1421, 5512, 5392, 7287, 8643, 4495, 2640, 8047, 7268, 3878, 6010, 8070, 7560, 8931, 76, 6502, 5952, 4871, 5986, 4935, 3015, 8263, 7497, 8153, 384, 1136};
        int x = 894887480;
        Solution9 s = new Solution9();
        assertThat(s.minOperations(nums, x), Matchers.is(6));
    }
}

class Solution9 {
    int[] nums;
    int minStep = Integer.MAX_VALUE;

    public int minOperations(int[] nums, int x) {
        this.nums = nums;
        calcSum(0, x, 0, nums.length - 1);
        return this.minStep == Integer.MAX_VALUE ? -1 : this.minStep;
    }

    public void calcSum(int stepCounter, int vacancy, int leftPointer, int rightPointer) {
        if (isTwoPointerHasMeet(leftPointer, rightPointer)
                || isTwoEndBothBiggerThanVacany(vacancy, leftPointer, rightPointer)) {
            return;
        } else {
            takeOneStepOnLeftOrRight(stepCounter, vacancy, leftPointer, rightPointer);
        }
    }

    private void takeOneStepOnLeftOrRight(int stepCounter, int vacancy, int leftPointer, int rightPointer) {
        int numLeft = nums[leftPointer];
        int numRight = nums[rightPointer];
        stepCounter += 1;
        if (numLeft == vacancy || numRight == vacancy) {
            updateMinStep(stepCounter);
            return;
        }
        if (numLeft <= vacancy) {
            int newVacany = vacancy - numLeft;
            calcSum(stepCounter, newVacany, leftPointer + 1, rightPointer);
        }
        if (numRight <= vacancy) {
            int newVacany = vacancy - numRight;
            calcSum(stepCounter, newVacany, leftPointer, rightPointer - 1);
        }
    }

    private static boolean isTwoPointerHasMeet(int leftPointer, int rightPointer) {
        return leftPointer > rightPointer;
    }

    private boolean isTwoEndBothBiggerThanVacany(int vacany, int leftPointer, int rightPointer) {
        boolean leftEndBigger = nums[leftPointer] > vacany;
        boolean rightENdBigger = nums[rightPointer] > vacany;
        return leftEndBigger && rightENdBigger;
    }

    private void updateMinStep(int stepCounter) {
        if (stepCounter < minStep) {
            minStep = stepCounter;
        }
    }
}