package tryjava;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class temp {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("(?<=:)[0-9]+");
        Matcher matcher = p.matcher("21.33.214.144:12345");
        System.out.println(matcher);
    }
}
