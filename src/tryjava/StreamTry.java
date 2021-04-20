package tryjava;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTry {
    // 试试用流实现把卡名称加入卡对象的那个操作？

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(10);
        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7);
        long counter = list.stream().filter(num -> num > 4).count();
        Stream<String> stringStream = Stream.iterate(0, num -> num += 1)
                .limit(10).map(Integer::toHexString);

        List<E> eList = new ArrayList<>();
        Collections.addAll(eList,
                new E().setName("a").setNum(1),
                new E().setName("a").setNum(2),
                new E().setName("b").setNum(2));

        StreamTry obj = new StreamTry();

//        obj.partition(eList);
//        obj.grouping(eList);
//        obj.buildMap(eList);
        obj.modify(eList);

    }

    private void modify(List<E> eList){
        List<E> modifiedList = eList.stream().
                peek(e->{e.setNum(5);e.setName("1111");})
                .collect(Collectors.toList());
        System.out.println(modifiedList);
    }

    // 分成true和false两个键，值为列表
    private void partition(List<E> eList) {
        Map<Boolean, List<E>> nameToListByIsEqualToA = eList.stream().collect(
                Collectors.partitioningBy(
                        e -> e.getName().equals("a"))
        );
        System.out.println(nameToListByIsEqualToA);
    }

    private void grouping(List<E> eList){
        Map<String, List<E>> nameToList = eList.stream().collect(
                Collectors.groupingBy(
                        e->e.getName(),
                        Collectors.toList()
                )
        );
        System.out.println(nameToList);
    }

    // 一个示例，把一个对象数组的对象的某值映射为包含该对象的上层对象的 Map
    // 只有它支持对值的自定义。
    private void buildMap(List<E> eList) {
        Map<String, EList> nameToListMap = eList.stream().collect(
                Collectors.toMap(
                        E::getName,//key is tryjava.E.name (key mapper)
                        // Value mapper
                        // first new value, make this one value to collection (as Value)
                        // or not make collection, trying to merge it with other same key values.
                        e -> mapENameToList(e),
                        (existingList, anotherList) -> {
                            // meet same key, put it into same key value collection
                            // (merge same key values)
                            existingList.getData().addAll(anotherList.getData());
                            return existingList;
                        }
                )
        );
//        // toConcurrentMap() 结果是 ConcurrentHashMap，最好转换回来。
//        // 没必要在小数据量上用Concurrent。
//        Map<String, tryjava.EList> nameToList = new HashMap<>(nameToListMap);
        System.out.println(nameToListMap);
    }

    public EList mapENameToList(E e) {
        return new EList().setData(new ArrayList<>(Collections.singletonList(e)));
    }

    // try Optional
    public static Optional<Double> inverse(Double x) {
        // 不抛异常的话，
        // Optional.ofNullable(obj) 更好
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

}

class E {
    private String name;

    public String getName() {
        return name;
    }

    public E setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public E setNum(Integer num) {
        this.num = num;
        return this;
    }

    private Integer num;

    public E(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public E() {
    }
}

class EList {
    private List<E> data;

    public List<E> getData() {
        return data;
    }

    public EList setData(List<E> data) {
        this.data = data;
        return this;
    }
}