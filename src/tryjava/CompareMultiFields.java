package tryjava;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CompareMultiFields {
    public static void main(String[] args) {
        List<A> originObjects = testDataA();
        List<B> newValues = testDataB();
        List<A> result = process(originObjects, newValues);
        System.out.println(result);
        assertThat(result.get(0).getValue(), is(newValues.get(0).getValue()));
        assertThat(result.get(1).getValue(), is(newValues.get(1).getValue()));
    }

    private static List<A> process(List<A> objects, List<B> newValues) {
        return objects.parallelStream()
                .map(dto -> changeValue(newValues, dto))
                .collect(Collectors.toList());
    }

    private static A changeValue(List<B> newValues, final A target) {
        newValues.parallelStream()
                // 这里比较过程可优化
                .filter(b -> b.getKey1().equals(target.getKey1())
                        && b.getKey2().equals(target.getKey2()))
                // stream看见这个开始执行
                .findFirst()
                // 直接改了对象，若想原对象不变应该复制一个出来。
                .ifPresent(b -> target.setValue(b.getValue()));
        return target;
    }

    private static List<A> testDataA() {
        return Arrays.asList(
                new A("a", "a", "0", "old"),
                new A("b", "b", "0", "old"),
                new A("c", "c", "0", "old"),
                new A("d", "d", "0", "old"));
    }

    private static List<B> testDataB() {
        return Arrays.asList(
                new B("a", "a", "new"),
                new B("b", "b", "new")
        );
    }
}

@Data
@AllArgsConstructor
class A {
    private String key1;
    private String key2;
    private String trivial;
    private String value;
}

@Data
@AllArgsConstructor
class B {
    private String key1;
    private String key2;
    private String value;
}