package LeetCodingChallenge2021.Feb;

import org.junit.Test;


public class Numberof1Bits {
    @Test
    public void t1() {
        int result = Solution.hammingWeight(-3);
        System.out.println(result);
    }
}

class Solution {
    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

}

