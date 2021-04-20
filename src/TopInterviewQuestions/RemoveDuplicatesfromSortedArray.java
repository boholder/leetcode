package TopInterviewQuestions;

import java.util.Arrays;

// 没过，我觉得题目改变了输入，不合理，不想做
public class RemoveDuplicatesfromSortedArray {
    public static void main(String[] args) {
        int[] a = {1,1,2};
        Solution.removeDuplicates(a);
        System.out.println(Arrays.toString(a));
    }
}

class Solution {
    public static int removeDuplicates(int[] nums) {
        int[] result = Arrays.stream(nums).distinct().toArray();
        nums = result.clone();
        return result.length;
    }
}