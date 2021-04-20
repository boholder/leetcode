package LeetCodingChallenge2021.Jan;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
// 过了
class Solution2 {
    public static boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer, int[]> firstElementToPiece =
                Arrays.stream(pieces).collect(
                        Collectors.toMap(e -> e[0], e -> e)
                );

        if (isNotMatch(arr, firstElementToPiece)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isNotMatch(int[] arr, Map<Integer, int[]> firstElementToPiece) {
        for (int i = 0; i < arr.length; ) {
            int[] piece = getCorrespondingPiece(firstElementToPiece, arr[i]);
            if (piece == null) {
                return true;
            }
            if (onePieceIsNotMatchOrderInArr(arr, i, piece)) {
                return true;
            }
            i += piece.length;
        }
        return false;
    }

    private static int[] getCorrespondingPiece(Map<Integer, int[]> firstElementToPiece, int arrNum) {
        if (!firstElementToPiece.containsKey(arrNum)) {
            return null;
        } else {
            int[] piece = firstElementToPiece.get(arrNum);
            return piece;
        }
    }

    private static boolean onePieceIsNotMatchOrderInArr(int[] arr, int i, int[] piece) {
        for (int j = 0; j < piece.length; j++) {
            if (arr[i + j] != piece[j]) {
                return true;
            }
        }
        return false;
    }
}


public class CheckArrayFormationThroughConcatenation {
    @Test
    public void simple() {
        int[] arr = {85};
        int[][] pieces = {{85}};
        assertThat(Solution2.canFormArray(arr, pieces), is(true));
    }

    @Test
    public void reverse() {
        int[] arr = {15, 85};
        int[][] pieces = {{85}, {15}};
        assertThat(Solution2.canFormArray(arr, pieces), is(true));
    }

    @Test
    public void wrongPiece() {
        int[] arr = {49, 18, 16};
        int[][] pieces = {{18, 49, 16}};
        assertThat(Solution2.canFormArray(arr, pieces), is(false));
    }

    @Test
    public void wrongNumber() {
        int[] arr = {1, 3, 5, 7};
        int[][] pieces = {{2, 4, 6, 8}};
        assertThat(Solution2.canFormArray(arr, pieces), is(false));
    }

    @Test
    public void finalOne() {
        int[] arr = {91, 4, 64, 78};
        int[][] pieces = {{4, 64}, {78}, {91}};
        assertThat(Solution2.canFormArray(arr, pieces), is(true));
    }
}
