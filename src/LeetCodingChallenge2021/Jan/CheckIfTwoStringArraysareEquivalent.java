package LeetCodingChallenge2021.Jan;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

// AC 结果不好，好结果是全指针不用对象,直接用流结果更差。
// 不用对象就不用复制俩数组进去，肯定省空间时间。
public class CheckIfTwoStringArraysareEquivalent {
    @Test
    public void t1() {
        String[] word1 = {"ab", "c"}, word2 = {"a", "bc"};
        assertThat(Solution6.arrayStringsAreEqual(word1, word2), Matchers.is(true));
    }

    @Test
    public void t2() {
        String[] word1 = {"a", "cb"}, word2 = {"a", "bc"};
        assertThat(Solution6.arrayStringsAreEqual(word1, word2), Matchers.is(false));
    }

    @Test
    public void t3() {
        String[] word1 = {"abc", "d", "defg"}, word2 = {"abcddefg"};
        assertThat(Solution6.arrayStringsAreEqual(word1, word2), Matchers.is(true));
    }
}

class Solution6 {
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringChain chain1 = new StringChain(word1);
        StringChain chain2 = new StringChain(word2);
        while (1 == 1) {
            char char1 = chain1.getNextCharacter();
            char char2 = chain2.getNextCharacter();
            if (char1 == char2) {
                if (char1 == '?') {
                    return true;
                } else {
                    continue;
                }
            } else {
                return false;
            }
        }
    }

    static class StringChain {
        String[] data;
        int dataIndex;
        char[] piece;
        int pieceIndex;

        public StringChain(String[] data) {
            this.data = data;
            this.dataIndex = 0;
            this.pieceIndex = 0;
            this.piece = data[0].toCharArray();
        }

        public char getNextCharacter() {
            if (pieceIndex < piece.length) {
                char result = piece[pieceIndex];
                pieceIndex += 1;
                return result;
            } else {
                if (dataIndex + 1 < data.length) {
                    dataIndex += 1;
                    piece = data[dataIndex].toCharArray();
                    pieceIndex = 1;
                    return piece[0];
                } else {
                    return '?';
                }
            }
        }
    }
}