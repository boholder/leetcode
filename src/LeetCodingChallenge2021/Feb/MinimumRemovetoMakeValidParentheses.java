package LeetCodingChallenge2021.Feb;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

// AC,两指标都是70%，挺好的
public class MinimumRemovetoMakeValidParentheses {
    @Test
    public void t1() {
        String actual = Solution3.removeGivenIndexCharacters("12345", new Integer[]{0, 4});
        assertThat(actual, is("234"));
    }

    @Test
    public void t2() {
        Integer[] actual = Solution3.scanForExtraParenthesesIndex(")(()");
        assertThat(actual, is(new Integer[]{0, 1}));
    }

    @Test
    public void shouldRemoveAll() {
        String test = "))((";
        String expected = "";
        String actual = Solution3.minRemoveToMakeValid(test);
        assertThat(actual, is(expected));
    }

    @Test
    public void noBracket() {
        String test = "abc";
        String expected = "abc";
        String actual = Solution3.minRemoveToMakeValid(test);
        assertThat(actual, is(expected));
    }
}

class Solution3 {
    public static String minRemoveToMakeValid(String s) {
        Integer[] indexOfBracketsWhichNeedsToBeRemoved = scanForExtraParenthesesIndex(s);
        return removeGivenIndexCharacters(s, indexOfBracketsWhichNeedsToBeRemoved);
    }

    public static Integer[] scanForExtraParenthesesIndex(String str) {
        ArrayDeque<Integer> unmatchedFrontBrackets = new ArrayDeque<>();
        ArrayDeque<Integer> extraBackBrackets = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            // 在ascii范围内
            char character = str.charAt(i);
            if (character == '(') {
                unmatchedFrontBrackets.push(i);
            } else if (character == ')') {
                if (!unmatchedFrontBrackets.isEmpty()) {
                    unmatchedFrontBrackets.pop();
                } else {
                    extraBackBrackets.push(i);
                }
            }
        }
        return Stream.concat(
                Arrays.stream(extraBackBrackets.toArray(new Integer[0])),
                Arrays.stream(unmatchedFrontBrackets.toArray(new Integer[0])))
                .sorted()
                .toArray(Integer[]::new);
    }

    public static String removeGivenIndexCharacters(String str, Integer[] indexArray) {
        if (indexArray.length == 0) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        int prevIndex = -1;
        for (int index : indexArray) {
            sb.append(str, prevIndex + 1, index);
            prevIndex = index;
        }
        int lastIndex = indexArray[indexArray.length - 1];
        sb.append(str.substring(lastIndex + 1));
        return sb.toString();
    }
}