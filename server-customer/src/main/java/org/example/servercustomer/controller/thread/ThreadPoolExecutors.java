package org.example.servercustomer.controller.thread;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor 线程池
 */
public class ThreadPoolExecutors {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,3,2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 0; i < 5; i++) {
            executor.execute(Runnable());
        }
        executor.shutdown();
    }

    public static Runnable Runnable(){
        Runnable runnable = ()->{
          System.out.println(Thread.currentThread().getName() + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
          try {
              Thread.sleep(3000);
          }catch (Exception e){
              e.printStackTrace();
          }
        };
        return runnable;
    }

}
