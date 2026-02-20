package org.example.servercustomer.controller.thread;

/**
 * volatile 关键字
 */
public class VolatileThread {

    private volatile Boolean flag = true;

    public void stop() {
        flag = false;
    }

    public void run() {
        while (flag) {
            // 执行任务
            System.out.println("Working...");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Stopping...");
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileThread volatileThread = new VolatileThread();
        Thread thread = new Thread(volatileThread::run);
        thread.start();
        Thread.sleep(3000);
//        volatileThread.stop();
    }

}
