package LeetCodingChallenge2021.Feb;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

//AC, runtime 30% memory 70%
public class LetterCasePermutation {
    @Test
    public void t1() {
        String s = "a1b2";
        Solution5 s5 = new Solution5();
        assertThat(s5.letterCasePermutation(s), is(Arrays.asList("a1b2", "a1B2", "A1b2", "A1B2")));
    }

    @Test
    public void t2() {
        String s = "0";
        Solution5 s5 = new Solution5();
        assertThat(s5.letterCasePermutation(s), is(Arrays.asList("0")));
    }
}

class Solution5 {
    List<String> results = new ArrayList<>();

    public List<String> letterCasePermutation(String S) {
        recurseBuildString("", S);
        return this.results;
    }

    private void recurseBuildString(String prefix, String remain) {
        boolean hasLetterFlag = false;
        for (int i = 0; i < remain.length(); i++) {
            char character = remain.charAt(i);
            if (Character.isDigit(character)) {
                continue;
            } else {
                hasLetterFlag = true;
                beginNextRecursion(i, character, prefix, remain);
                break;
            }
        }
        if (!hasLetterFlag) {
            this.results.add(prefix + remain);
        }
    }

    private void beginNextRecursion(int i, char character, String prefix, String remain) {
        String newPrefix = prefix + remain.substring(0, i);
        String newRemain = remain.substring(i + 1);
        recurseBuildString(
                newPrefix + Character.toLowerCase(character),
                newRemain);
        recurseBuildString(
                newPrefix + Character.toUpperCase(character),
                newRemain);
    }
}