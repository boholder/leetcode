package tryjava;

public class AnonymClass {
    public static void main(String[] args) {
        String str = "didi!";
        Integer i = 1;
        Runnable r = new Runnable() {
            int j = 0;

            @Override
            public void run() {
                try {
                    while (1 == 1) {
                        System.out.println("s" + j++);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        };
        Thread t = new Thread(r);
        Thread t2 = new Thread(r);
        t.start();
        t2.start();
    }
}
