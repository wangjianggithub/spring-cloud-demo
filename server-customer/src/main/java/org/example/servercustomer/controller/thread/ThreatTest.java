package org.example.servercustomer.controller.thread;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@RestController
public class ThreatTest {

    public static void main(String[] args) throws InterruptedException {
//        threadReentrant();
//        stampedLockWriteThread(10);
//        stampedLockReadThread();
    }




    private static int reenCounter = 0;
    private static final Lock reenLock = new ReentrantLock();

    /**
     * ReentrantLock可重入锁
     */
    public static void threadReentrant() {
        Runnable waiter = () -> {
            for (int i = 0; i < 10; i++) {
                reenLock.lock();
                try {
                    reenCounter++;
                } finally {

                    reenLock.unlock();
                }
            }
        };
        Thread thread1 = new Thread(waiter);
        Thread thread2 = new Thread(waiter);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Final counter value: " + reenCounter);
    }

    private static int counterWriter;
    private static final StampedLock sl = new StampedLock();

    /**
     * stampedlock 写锁 独占锁
     */
    public static void stampedLockWriteThread(double deltaX) {
        Runnable writeThread = () -> {
            Long stamp = sl.writeLock();
            try {
                counterWriter += deltaX;
            } finally {
                sl.unlockWrite(stamp);
            }
        };
        Thread thread = new Thread(writeThread);
        Thread thread2 = new Thread(writeThread);
        thread.start();
        thread2.start();

        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Final counterWriter value: " + counterWriter);
    }

    private static int sReadCounter = 0;

    /**
     * stampedlock 读锁 共享锁
     */
    public static void stampedLockReadThread() {
        Runnable readThread = () -> {
            for (int i = 0; i < 10; i++){
                Long readLock = sl.readLock();
                try {
                    sReadCounter++;
                } finally {
                    sl.unlockRead(readLock);
                }
            }
        };
        Thread thread = new Thread(readThread);
        Thread thread2 = new Thread(readThread);

        thread.start();
        thread2.start();

        try {
            thread.join();
            thread2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
