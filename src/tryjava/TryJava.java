package tryjava;

import joptsimple.internal.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TryJava {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", null);
        map.put("b", "1");
        map.put("c", null);
        map.values().removeIf(v -> Strings.isNullOrEmpty(v));
        System.out.println(map);
    }
}
