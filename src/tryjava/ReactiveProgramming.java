package tryjava;

import java.util.concurrent.*;

public class ReactiveProgramming {
    public final void load() {
        long startTime = System.currentTimeMillis(); // 开始时间
        doLoad(); // 具体执行
        long costTime = System.currentTimeMillis() - startTime; // 消耗时间
        System.out.println("load() 总耗时：" + costTime + " 毫秒");
    }

    protected void doLoad() { // 串行计算
        loadConfigurations();    //  耗时 1s
        loadUsers();                  //  耗时 2s
        loadOrders();                // 耗时 3s
    } // 总耗时 1s + 2s  + 3s  = 6s

    protected final void loadConfigurations() {
        loadMock("loadConfigurations()", 1);
    }

    protected final void loadUsers() {
        loadMock("loadUsers()", 2);
    }

    protected final void loadOrders() {
        loadMock("loadOrders()", 3);
    }

    private void loadMock(String source, int seconds) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(milliseconds);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程 : %s] %s 耗时 :  %d 毫秒\n",
                    Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ReactiveProgramming().load();
    }

}

class ParallelDataLoader extends ReactiveProgramming {

    protected void doLoad() {  // 并行计算
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 创建线程池
        CompletionService completionService = new ExecutorCompletionService(executorService);
        completionService.submit(super::loadConfigurations, null);      //  耗时 >= 1s
        completionService.submit(super::loadUsers, null);               //  耗时 >= 2s
        completionService.submit(super::loadOrders, null);              //  耗时 >= 3s

        int count = 0;
        while (count < 3) { // 等待三个任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
    }  // 总耗时 max(1s, 2s, 3s)  >= 3s

    public static void main(String[] args) {
        new ParallelDataLoader().load();
    }

}