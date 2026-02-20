package org.example.servercustomer.controller.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * future 执行异步线程多个任务
 */
public class FutureThread {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                int index = 1;
                Future<Integer> future = executor.submit(() -> {
                    Thread.sleep(2000);
                    return index * 10;
                });
                futures.add(future);
            }
            for (Future<Integer> future : futures) {
                Integer result = future.get();
                System.out.println("任务结果：" + result);
            }
            System.out.print("结束------------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
