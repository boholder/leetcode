package tryjava;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionTry {
    public static void main(String[] args) {


        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
        stack.push(12321);

        List<Integer> list = new ArrayList<>(10);
        // addAll这个方法在做测试写测试集合时很有用。
        // [123, 123, 123]
        Collections.addAll(list,1,2,3,4,2,2,5,6,7);
        Collections.rotate(list,2);
        int frequency = Collections.frequency(list,2);
        System.out.println(frequency);
    }
}
