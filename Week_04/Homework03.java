import java.util.Random;
import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * <p>
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03 {
    public static int num = 0;

    public static void main(String[] args) throws Exception {

//        model();
//        fun_01();
//        fun_02();
//        fun_03();
//        fun_04();
        fun_05();
    }

    private static void fun_06() throws Exception {
        System.out.println("fun_06 --> num : " + num);
    }


    private static void fun_05() throws Exception {
        System.out.println("fun_05 --> num : " + num);
    }


    private static void fun_04() throws Exception {

        FutureTask<Integer> task = new FutureTask<>(Homework03::sum);
        new Thread(task).start();

        System.out.println("--------1 = " + "--------1");

//        Integer value = task.get();
        // 设置超时时间，此处会等待
        Integer value = task.get(1, TimeUnit.SECONDS);
        System.out.println("--------2 = " + "--------2");

        System.out.println("fun_04 --> num : " + value);

    }

    private static void fun_03() throws Exception {

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
        // 此处会等待,设置超时时间
        executorService.shutdown();

        int i = sum.get(1, TimeUnit.SECONDS);
        System.out.println("fun_03 --> num : " + i);

    }

    private static void fun_02() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> num = Homework03.sum1());

//        Thread.sleep(1000);
        // 停止接收新任务，已有的任务继续执行
//        executorService.shutdown();
        // 停止接收新任务，停止执行已有的任务，正在执行的线程会抛出 InterruptedException 异常。
        executorService.shutdownNow();
        // shutdown 之后，sleep，还有执行结果
        Thread.sleep(1000);
        System.out.println("fun_02 --> num : " + num);

    }

    private static void fun_01() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            num = Homework03.sum();
        }, "t1");

        t1.start();
        Thread.sleep(1000 * 2);
        System.out.println("fun_01 --> num : " + num);

    }


    private static void model() {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        int result = sum(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum1() {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fibo(36);
    }

    private static int sum() {
        return fibo(36);
    }


    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}
