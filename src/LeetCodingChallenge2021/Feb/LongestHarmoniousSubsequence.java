package LeetCodingChallenge2021.Feb;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// AC, 俩指标都很菜，肯定那个boxed()少不了份。
// 想加速就用传统方法溜一遍计数吧。至少没有包装和解包。
public class LongestHarmoniousSubsequence {
    @Test
    public void t1() {
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        int actual = Solution2.findLHS(nums);
        assertThat("", actual, is(5));
    }

    @Test
    public void t2() {
        int[] nums = {1, 2, 3, 4};
        int actual = Solution2.findLHS(nums);
        assertThat("", actual, is(2));
    }

    @Test
    public void t3() {
        int[] nums = {1, 1, 1, 1};
        int actual = Solution2.findLHS(nums);
        assertThat("", actual, is(0));
    }
}

class Solution2 {
    public static int findLHS(int[] nums) {
        Map<Integer, Long> valueToCountMap = getValueToCountMap(nums);
        if (valueToCountMap.size() < 2) {
            return 0;
        }
        return calcMaxHSLength(valueToCountMap);
    }

    private static Map<Integer, Long> getValueToCountMap(int[] nums) {
        return Arrays.stream(nums).boxed().collect(
                        Collectors.groupingBy(
                                value -> value,
                                Collectors.counting())
                );
    }

    private static int calcMaxHSLength(Map<Integer, Long> valueToCountMap) {
        Set<Map.Entry<Integer, Long>> entrySet = valueToCountMap.entrySet();
        int maxHSLength = 0;
        for (Map.Entry<Integer, Long> entry : entrySet) {
            int neighbor = entry.getKey() + 1;
            if (valueToCountMap.containsKey(neighbor)) {
                long newHS = entry.getValue() + valueToCountMap.get(neighbor);
                maxHSLength = maxHSLength < newHS ? (int) newHS : maxHSLength;
            }
        }
        return maxHSLength;
    }
}