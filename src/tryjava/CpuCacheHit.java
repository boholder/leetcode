package tryjava;

import sun.misc.Contended;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CpuCacheHit {
    public static void main(String[] args) {
        iterBasedOnRowOrCol();
    }

    private static void iterBasedOnRowOrCol() {
        int row = 2048;
        int col = 2048;
        int[][] matrix = new int[row][col];
        //逐行遍历
        int sum_row = 0;
        long start = System.nanoTime();
        for (int _r = 0; _r < row; _r++) {
            for (int _c = 0; _c < col; _c++) {
                sum_row += matrix[_r][_c];
            }
        }
        System.out.println(MessageFormat.format("row:{0}", System.nanoTime() - start));
        //逐列遍历
        int sum_col = 0;
        start = System.nanoTime();
        for (int _c = 0; _c < col; _c++) {
            for (int _r = 0; _r < row; _r++) {
                sum_col += matrix[_r][_c];
            }
        }
        System.out.println(MessageFormat.format("col:{0}", System.nanoTime() - start));
    }

    private static void cpuCache() {
        List<Integer> steps = Arrays.asList(1, 16, 512, 1024);
        List<String> results = new ArrayList<>(18);
        System.out.println("step | 1 | 16 | 512 | 1024");
        IntStream.range(1, 18).forEachOrdered(count -> {
            System.out.print(count + " | ");
            steps.stream().forEachOrdered(step -> {
                int size = step * count;
                System.out.print(runAndCount(size, step, new int[size]) + " | ");
            });
            System.out.print("\n");
        });
    }

    private static long runAndCount(int size, int increment, int[] memory) {
        int times = 10000000;
        List<Long> runTimes = new ArrayList<>(times);
        for (int i = 0; i < times; i++) {
            long start = System.nanoTime();
            for (int j = 0; j < size; j += increment) {
                memory[j] += j;
            }
            long end = System.nanoTime();
            runTimes.add(end - start);
        }
        return runTimes.stream().mapToLong(Long::longValue).sum();
    }
}
