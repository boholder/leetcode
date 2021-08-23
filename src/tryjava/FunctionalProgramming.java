package tryjava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FunctionalProgramming {
    public static void main(String[] args) {
        List<String> friends =
                Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        Out out = new Out();
        friends.forEach(System.out::println);
        final List<String> upperNames = friends.stream().map(String::toUpperCase).collect(Collectors.toList());
        // 原来能调实例方法，牛了
        upperNames.forEach(out::print);
    }

    static class Out {
        public void print(String s) {
            System.out.println(s);
        }
    }

}
