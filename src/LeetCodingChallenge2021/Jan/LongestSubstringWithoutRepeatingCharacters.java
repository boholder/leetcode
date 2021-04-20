package LeetCodingChallenge2021.Jan;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

// AC
public class LongestSubstringWithoutRepeatingCharacters {
    @Test
    public void t1() {
        String s = "abcabcbb";
        assertThat(Solution5.lengthOfLongestSubstring(s), Matchers.is(3));
    }

    @Test
    public void t2() {
        String s = "pwwkew";
        assertThat(Solution5.lengthOfLongestSubstring(s), Matchers.is(3));
    }

    @Test
    public void t3() {
        String s = "bbbbb";
        assertThat(Solution5.lengthOfLongestSubstring(s), Matchers.is(1));
    }

    @Test
    public void t4() {
        String s = "";
        assertThat(Solution5.lengthOfLongestSubstring(s), Matchers.is(0));
    }

    @Test
    public void t5() {
        String s = "a";
        assertThat(Solution5.lengthOfLongestSubstring(s), Matchers.is(1));
    }

    @Test
    public void t6() {
        String s = "abba";
        assertThat(Solution5.lengthOfLongestSubstring(s), Matchers.is(2));
    }
}

class Solution5 {
    public static int lengthOfLongestSubstring(String s) {
        int maxSubStringLength = 0;
        Map<Character, Integer> cache = new HashMap();
        char[] array = s.toCharArray();
        int headPointer = 0;
        // 还好都是ASCII范围
        for (int tailPointer = 0; tailPointer < s.length(); tailPointer++) {
            Character character = array[tailPointer];
            if (cache.containsKey(character)) {
                // calculate length
                int currentSubStringLength = tailPointer - headPointer;
                maxSubStringLength = Math.max(maxSubStringLength, currentSubStringLength);
                // update pointer and cache
                // set head to old repeated index
                headPointer = Math.max(cache.get(character) + 1, headPointer);
                cache.put(character, tailPointer);
            } else {
                cache.put(character, tailPointer);
            }
        }
        // to array end, then calculate once.
        maxSubStringLength = Math.max(maxSubStringLength, s.length() - headPointer);
        return maxSubStringLength;
    }
}