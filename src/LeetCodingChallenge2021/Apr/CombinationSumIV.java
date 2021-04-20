package LeetCodingChallenge2021.Apr;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CombinationSumIV {
    @Test
    public void v1() {
        Solution solution = new Solution();
        assertThat(solution.combinationSum4(new int[]{1, 2, 3}, 4), is(7));
    }

    @Test
    public void v2() {
        Solution solution = new Solution();
        assertThat(solution.combinationSum4(new int[]{1, 2}, 10), is(89));
    }

    @Test
    public void v3() {
        Solution solution = new Solution();
        assertThat(solution.combinationSum4(new int[]{9}, 3), is(0));
    }
}

// 虽然过了但都很差，差得不在排名里。
// 还是反向思考用DP，从0加元素加到目标数。
class Solution {
    int counter = 0;

    public int combinationSum4(int[] nums, int target) {
        Map<Integer, Integer> remains = new HashMap<>();
        remains.put(target, 1);
        while (!remains.isEmpty()) {
            remains = iterateGetNextSet(remains, nums);
        }
        return this.counter;
    }

    private Map<Integer, Integer> iterateGetNextSet(Map<Integer, Integer> remains, int[] minus) {
        Map<Integer, Integer> result = new HashMap<>();
        remains.forEach((remain, counter) -> {
            for (int j : minus) {
                int newRemain = remain - j;
                addRemainToResultOrUpdateCounter(result, counter, newRemain);
            }
        });
        return result;
    }

    private void addRemainToResultOrUpdateCounter(Map<Integer, Integer> result, Integer value, int newRemain) {
        if (newRemain > 0) {
            if (result.containsKey(newRemain)) {
                // counter += this calculation's counter  (counter prev + counter this)
                result.put(newRemain, result.get(newRemain) + value);
            } else {
                // new, set value to pass counter to new state.
                result.put(newRemain, value);
            }
        } else if (newRemain == 0) {
            this.counter += value;
        }
    }
}