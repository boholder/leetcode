package tryjava;

public class temp {
    public static void main(String[] args) {
        try {
            throw new Exception("info",new Exception("inner"));
        } catch (Throwable e) {
            System.out.println(e.getCause());
        }
    }
}
