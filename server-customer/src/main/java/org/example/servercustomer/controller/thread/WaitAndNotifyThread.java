package org.example.servercustomer.controller.thread;

/**
 * 等待和消息线程
 */
public class WaitAndNotifyThread {

    private static final Boolean flag = false;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        Thread waitThread = new Thread(()-> {
            synchronized (lock) {
                try {
                    System.out.println("Waiting for thread");
                    if (!flag){
                        lock.wait();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("Waiting for thread end");
        });
        Thread notifyThread = new Thread(()-> {
            System.out.println("Notify for thread");
            synchronized (lock) {
                if (!false){
                    lock.notify();
                }
            }
            System.out.println("Notifying thread end");
        });
        waitThread.start();
        notifyThread.start();
    }

}
