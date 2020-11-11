import java.util.concurrent.*;

public class Homework03 {
    public static int num = 0;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
//        fun01();
//        fun02();
//        fun03();
//        fun04();
//        fun05();
//        fun06();
//        fun07();
        long time = System.currentTimeMillis() - start;
        System.out.println("程序总耗时：" + time);
    }


    /**
     * 方法七
     * CyclicBarrier 的回调函数是在所有线程到达集合点后再执行,
     * 该示例中的因主线程直接退出故其打印num为0，在calback中num有值
     */
    private static void fun07() throws Exception {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1,
                () -> System.out.println("num value--> " + num)
        );

        Thread thread = new Thread(() -> {
            try {
                num = Homework03.sum();
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        // 主线程需要等待，才能有返回值
//        Thread.sleep(1000);
        System.out.println("fun07 --> num : " + num);
    }


    /**
     * 方法六
     * Thread#start() : 启动一个新线程并异步执行其中的任务(真正创建了一个物理线程)。
     * Thread#run() : 在当前线程执行，和调用其他对象的普通方法没什么区别。
     * CountDownLatch: (闭锁)可以看作一个只能做减法的计数器，可以让一个或多个线程等待执行。
     */
    private static void fun06() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            num = Homework03.sum();
            countDownLatch.countDown();
        });
        thread.start();
        countDownLatch.await();
        System.out.println("fun06 --> num : " + num);
    }


    /**
     * 方法五
     * 使用FutureTask, 在线程执行完后，可以通过Future 的特性，
     *
     * @see FutureTask
     * 拿到返回值
     */
    private static void fun05() throws Exception {

        FutureTask<Integer> task = new FutureTask<>(Homework03::sum);
        new Thread(task).start();

        System.out.println("--------1 = " + "--------1");

//        Integer value = task.get();
        // 设置超时时间，此处会等待
        Integer value = task.get(1, TimeUnit.SECONDS);
        System.out.println("--------2 = " + "--------2");

        System.out.println("fun05 --> num : " + value);

    }

    /**
     * 方法四
     * 利用单线程池来执行任务
     * submit 方法: 有Future封装的返回值，执行中如果抛出异常，等待的方法中可以 catch 到。
     * execute 方法: 无返回值，执行任务是捕捉不到异常的。
     * 此处调用submit ， 利用Future 获取task的返回值
     * 另外有使用lambda的简写方式
     */
    private static void fun04() throws Exception {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // lambda 表达式
        Future<Integer> sum = executorService.submit(Homework03::sum);

        // 常规写法
//        Future<Integer> sum = executorService.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return Homework03.sum();
//            }
//        });

//        Thread.sleep(1000);
        // 此处会等待，
//        int i = sum.get();
        executorService.shutdown();
        // 可以设置等待时间，也可以不设置，在future获取相关值的时候，可以设置超时时间
//        executorService.awaitTermination(1, TimeUnit.SECONDS);

        int i = sum.get(1, TimeUnit.SECONDS);
        System.out.println("fun04 --> num : " + i);

    }

    /**
     * 方法三
     * 利用单线程池来执行任务
     * shutdown() : 停止接收新任务，已有的任务继续执行。
     * shutdownNow() : 停止接收新任务，停止执行已有的任务，正在执行的线程会抛出 InterruptedException 异常。
     * awaitTermination(long timeOut, TimeUnit unit) : 当前线程阻塞，等待终止。
     * 这里也需要调用awaitTermination 等待任务线程执行结束,
     * 注意shutdown 、shutdownNow 的区别.
     * 在测试shutdownNow 时，需要将任务的执行时间放大，此处需要调用sum1
     */
    private static void fun03() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        在测试shutdownNow,需要打开此处
//        executorService.execute(() -> num = Homework03.sum1());
        executorService.execute(() -> num = Homework03.sum());

//        Thread.sleep(1000);
//        executorService.shutdown();
        executorService.shutdownNow();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("fun_02 --> num : " + num);

    }

    /**
     * 方法二
     * 利用简单的额外线程来执行任务
     * 外围改用join ,让主线程等待本任务线程的执行
     * 待执行接收后，主线程继续执行
     */
    private static void fun02() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            num = Homework03.sum();
        }, "t1");

        t1.start();
        t1.join();
        System.out.println("fun02 --> num : " + num);
    }

    /**
     * 方法一
     * 利用简单的额外线程来执行任务
     * 外围sleep 一个预估时间，等待任务线程执行完
     * 预估时间一般大于，任务具体的执行时间
     */
    private static void fun01() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            num = Homework03.sum();
        }, "t1");

        t1.start();
        Thread.sleep(1000 * 2);
        System.out.println("fun01 --> num : " + num);

    }

    /**
     * 增加任务的消耗时间
     */
    private static int sum1() {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sum();
    }

    private static int sum() {
        long start = System.currentTimeMillis();

        int result = fibo(36);
        long time = System.currentTimeMillis() - start;
        System.out.println("任务总耗时为：  = " + time);

        return result;
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
