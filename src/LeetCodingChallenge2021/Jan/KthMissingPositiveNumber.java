package LeetCodingChallenge2021.Jan;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

// 过了
public class KthMissingPositiveNumber {
    @Test
    public void t1() {
        int[] arr = {2, 3, 4, 7, 11};
        int k = 5;
        assertThat(Solution4.findKthPositive(arr, k), Matchers.is(9));
    }

    @Test
    public void t2() {
        int[] arr = {1, 2, 3, 4};
        int k = 2;
        assertThat(Solution4.findKthPositive(arr, k), Matchers.is(6));
    }
}


class Solution4 {
    // 过了，0ms
    public static int findKthPositive(int[] arr, int k) {
        int counter = 0;
        int expectedNumber = 1;
        for (int index = 0; index < arr.length; ) {
            boolean numberNotInArr = arr[index] != expectedNumber;
            if (numberNotInArr) {
                counter += 1;
                if (counter == k) {
                    return expectedNumber;
                }
            } else {
                index += 1;
            }
            expectedNumber += 1;
        }
        int biggestNumberInArr = arr[arr.length - 1];
        return k - counter + biggestNumberInArr;
    }

// 过了，但包装器确实很慢跑了78ms
//    public static int findKthPositive(int[] arr, int k) {
//        List<Integer> arrList = Arrays.stream(arr)
//                .boxed()
//                .collect(Collectors.toList());
//        int biggestNumber = arr[arr.length - 1] + k;
//        int[] missingArray = IntStream.rangeClosed(1, biggestNumber)
//                .filter(
//                        num -> !arrList.contains(num)
//                ).toArray();
//        return missingArray[k - 1];
//    }
}