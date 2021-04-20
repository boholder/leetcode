package tryjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Comparators {
    public static void main(String[] args) {
        List<En> list = new ArrayList<>(5);
        list.add(new En().setName("bac").setNum(1));
        list.add(new En().setName("ab").setNum(0));
        System.out.println(list);
        Collections.sort(list, Comparator.comparing(
                En::getName, Comparator.naturalOrder()));
        System.out.println(list.get(0).getName());
    }
}

class En {
    private String name;

    public String getName() {
        return name;
    }

    public En setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public En setNum(Integer num) {
        this.num = num;
        return this;
    }

    private Integer num;

    public En(String name, Integer num) {
        this.name = name;
        this.num = num;
    }

    public En() {
    }
}
