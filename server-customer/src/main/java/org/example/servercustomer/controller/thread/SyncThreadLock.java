package org.example.servercustomer.controller.thread;

/**
 * 同步线程锁机制 synchronize
 */
public class SyncThreadLock {

    private static int counter = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + ":" + counter);
                    try {
                        counter++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("final counter : " + counter);
    }

}
