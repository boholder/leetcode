package LeetCodingChallenge2021.Aug;
//Given an array of integers nums and an integer target, return indices of the t
//wo numbers such that they add up to target. 
//
// You may assume that each input would have exactly one solution, and you may n
//ot use the same element twice. 
//
// You can return the answer in any order. 
//
// 
// Example 1: 
//
// 
//Input: nums = [2,7,11,15], target = 9
//Output: [0,1]
//Output: Because nums[0] + nums[1] == 9, we return [0, 1].
// 
//
// Example 2: 
//
// 
//Input: nums = [3,2,4], target = 6
//Output: [1,2]
// 
//
// Example 3: 
//
// 
//Input: nums = [3,3], target = 6
//Output: [0,1]
// 
//
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// Only one valid answer exists. 
// 
//
// 
//Follow-up: Can you come up with an algorithm that is less than O(n2) time comp
//lexity? Related Topics Array Hash Table 
// 👍 23131 👎 776

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

//leetcode submit region begin(Prohibit modification and deletion)

public class TwoSum {
    @Test
    public void t1() {
        int[] nums = new int[]{2, 7, 11, 15};
        assertThat(Solution.twoSum(nums, 9), is(new int[]{0, 1}));
    }
}

class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> remains = new HashMap();
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            if (remains.containsKey(num)) {
                return new int[]{remains.get(num), i};
            } else {
                remains.put(target - num, i);
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
