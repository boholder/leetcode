package LeetCodingChallenge2021.Feb;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

//AC,runtime 10%，memory 50%。
//换了计算soldier的方法后runtime 15%, memory 48%，
// 算了,我还是觉得是自动包装的问题。
public class TheKWeakestRowsinaMatrix {

    @Test
    public void t1() {
        int[][] mat = {{1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}};
        int k = 3;
        int[] expected = {2, 0, 3};
        assertThat(Solution4.kWeakestRows(mat, k), is(expected));
    }

    @Test
    public void t2() {
        int[][] mat = {{1, 0, 0, 0},
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 0}};
        int k = 2;
        int[] expected = {0, 2};
        assertThat(Solution4.kWeakestRows(mat, k), is(expected));
    }
}

class Solution4 {
    public static int[] kWeakestRows(int[][] mat, int k) {
        List<Row> rows = prepareRowInstances(mat);
        return rows.stream()
                .sorted()
                .limit(k)
                .map(Row::getIndex)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static List<Row> prepareRowInstances(int[][] mat) {
        List<Row> rows = new ArrayList<>();
        int index = 0;
        for (int[] row : mat) {
            rows.add(new Row(index, row));
            index += 1;
        }
        return rows;
    }

    static class Row implements Comparable<Row> {
        Integer index;
        Integer soldier;
        Integer length;

        Row(int index, int[] array) {
            this.index = index;
            this.soldier = this.calculateSoldier(array);
            this.length = array.length;
        }

        public static Integer calculateSoldier(int[] array) {
//            return Arrays.stream(array).sum();
            int sum = 0;
            for (int number : array) {
                if (number > 0) {
                    sum += 1;
                } else {
                    break;
                }
            }
            return sum;
        }

        public Integer getIndex() {
            return index;
        }

        @Override
        public int compareTo(Row o) {
            if (this.soldier < o.soldier) {
                return -1;
            } else if (this.soldier == o.soldier) {
                if (this.length > o.length) {
                    return -1;
                } else if (this.length == o.length) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }
}